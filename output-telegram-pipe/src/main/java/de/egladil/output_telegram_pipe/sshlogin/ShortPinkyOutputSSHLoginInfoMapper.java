// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.sshlogin;

import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.output_telegram_pipe.config.PipeAppConfig;
import de.egladil.web.commons_validation.payload.MessagePayload;

/**
 * ShortPinkyOutputSSHLoginInfoMapper
 */
public class ShortPinkyOutputSSHLoginInfoMapper implements Function<String, SSHLoginInfo> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShortPinkyOutputSSHLoginInfoMapper.class);

	private final PipeAppConfig appConfig;

	/**
	 * @param appConfig
	 */
	public ShortPinkyOutputSSHLoginInfoMapper(final PipeAppConfig appConfig) {

		super();
		this.appConfig = appConfig;
	}

	@Override
	public SSHLoginInfo apply(final String outcome) {

		String[] tokens = StringUtils.split(outcome, " ");

		if (tokens.length < 5) {

			String message = "pinky command gibt weniger als 5 token zurueck!!!";
			LOGGER.error(message + ": outcome=" + outcome);

			return new SSHLoginInfo().withMessagePayload(MessagePayload.error(message));
		}

		String user = tokens[0];

		Optional<UserType> optMappedUserType = new PinkyUserOutputUserInfoMapper(appConfig).apply(user);

		if (optMappedUserType.isEmpty()) {

			String message = "!!!!  erfolgreiches Login eines unbekannten users  !!!";
			LOGGER.warn(message + " username=" + user);

			return new SSHLoginInfo().withMessagePayload(MessagePayload.warn(message)).withSshUser(user)
				.withIpAddress(tokens[tokens.length - 1]);
		}

		String message = "===  erfolgreiches Login eines erlaubten users  ===";
		LOGGER.info(message + " username=" + user);

		return new SSHLoginInfo().withMessagePayload(MessagePayload.warn(message)).withSshUser(optMappedUserType.get().name())
			.withIpAddress(tokens[tokens.length - 1]);
	}

}
