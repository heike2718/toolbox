// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.sshlogin;

import java.util.Optional;
import java.util.function.Function;

import de.egladil.output_telegram_pipe.config.PipeAppConfig;

/**
 * WhoamiOutputUserAliasMapper
 */
public class WhoamiOutputUserAliasMapper implements Function<String, String> {

	private final PipeAppConfig appConfig;

	/**
	 * @param appConfig
	 */
	public WhoamiOutputUserAliasMapper(final PipeAppConfig appConfig) {

		super();
		this.appConfig = appConfig;
	}

	@Override
	public String apply(final String whoami) {

		String theUser = whoami.replace("\n", "");
		Optional<UserType> optMappedUserType = new PinkyUserOutputUserInfoMapper(appConfig).apply(theUser.trim());

		if (optMappedUserType.isEmpty()) {

			return whoami;
		}

		return optMappedUserType.get().name();
	}

}
