// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.sshlogin;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.output_telegram_pipe.CLIArgs;
import de.egladil.output_telegram_pipe.PipeCommand;
import de.egladil.output_telegram_pipe.PipeType;
import de.egladil.output_telegram_pipe.config.PipeAppConfig;
import de.egladil.output_telegram_pipe.config.PipeAppConfigReader;
import de.egladil.output_telegram_pipe.publish.impl.MailPublisher;
import de.egladil.output_telegram_pipe.publish.impl.TelegramPublisher;
import de.egladil.output_telegram_pipe.system.CommandExecutor;
import de.egladil.web.commons_validation.payload.MessagePayload;

/**
 * PropagateSSHLoginCommand
 */
public class PropagateSSHLoginCommand implements PipeCommand {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropagateSSHLoginCommand.class);

	private final CommandExecutor commandExecutor;

	/**
	 * @param commandExecutor
	 */
	public PropagateSSHLoginCommand(final CommandExecutor commandExecutor) {

		this.commandExecutor = commandExecutor;
	}

	@Override
	public void execute(final CLIArgs args) {

		PipeAppConfig appConfig = null;
		String pathConfigDir = args.getConfigDir();

		if (pathConfigDir == null) {

			appConfig = new PipeAppConfigReader().readConfiguration();
		} else {

			appConfig = new PipeAppConfigReader().readConfigurationFromFileSystem(pathConfigDir);
		}

		String pinkyOutcome = executePinkyCommand();
		String currentUserOutcome = executeWhoamiCommand();
		String currentUser = new WhoamiOutputUserAliasMapper(appConfig).apply(currentUserOutcome);

		SSHLoginInfo loginInfo = new ShortPinkyOutputSSHLoginInfoMapper(appConfig).apply(pinkyOutcome);
		loginInfo.withCurrentUser(currentUser).withCurrentTime(this.executeDateCommand());
		MessagePayload messagePayload = loginInfo.getMessagePayload();

		if (loginInfo.isError()) {

			LOGGER.error("Konnte SSHLoginInfos nicht ermitteln: " + messagePayload.getMessage());

		} else {

			String subject = appConfig.getEnv() + ": " + messagePayload.getMessage();
			String messageBody = loginInfo.toString();

			new TelegramPublisher().publishMessage(subject, appConfig.getEnv() + ": " + messageBody, appConfig);
			new MailPublisher().publishMessage(subject, messageBody, appConfig);
		}

	}

	/**
	 * @return
	 */
	private String executePinkyCommand() {

		List<String> commands = new ArrayList<String>();
		commands.add("bash");
		commands.add("-c");
		commands.add("pinky -wf");

		String outcome = commandExecutor.executeCommand(commands);
		return outcome;
	}

	private String executeWhoamiCommand() {

		List<String> commands = new ArrayList<String>();
		commands.add("bash");
		commands.add("-c");
		commands.add("whoami");

		String outcome = commandExecutor.executeCommand(commands);
		return outcome;
	}

	private String executeDateCommand() {

		List<String> commands = new ArrayList<String>();
		commands.add("bash");
		commands.add("-c");
		commands.add("date");

		String outcome = commandExecutor.executeCommand(commands);
		outcome = outcome.replace("\n", "");
		return outcome;
	}

	@Override
	public PipeType getType() {

		return PipeType.SSHLOGIN;
	}

}
