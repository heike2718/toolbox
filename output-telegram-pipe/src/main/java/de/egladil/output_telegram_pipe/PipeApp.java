// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

/**
 * PipeApp
 */
public class PipeApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipeApp.class);

	public static void main(final String[] args) {

		PipeApp pipeApp = new PipeApp();

		CLIArgs cliArgs = new CLIArgs();

		JCommander jcommander = JCommander.newBuilder().addObject(cliArgs).build();

		try {

			jcommander.parse(args);
			pipeApp.executeCommand(cliArgs);
		} catch (ParameterException e) {

			LOGGER.error("PipeApp exits with error: " + e.getMessage());
			jcommander.usage();
		} catch (Throwable e) {

			LOGGER.error("PipeApp exits with error: " + e.getMessage(), e);
		}

	}

	/**
	 * @param cliArgs
	 */
	private void executeCommand(final CLIArgs cliArgs) {

		if (cliArgs.help()) {

			System.out.println(cliArgs.printHelp());
			return;
		}

		PipeType pipeType = cliArgs.getPipeType();
		PipeCommand command = PipeCommand.createCommandOfType(pipeType);
		command.execute(cliArgs);
	}
}
