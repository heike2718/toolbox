// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.sshlogin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import de.egladil.output_telegram_pipe.config.AllowedUsersConfig;
import de.egladil.output_telegram_pipe.config.PipeAppConfig;

/**
 * PinkyUserOutputUserInfoMapper
 */
public class PinkyUserOutputUserInfoMapper implements Function<String, Optional<UserType>> {

	private Map<String, UserType> userNameMap = new HashMap<>();

	/**
	 * @param appConfig
	 */
	public PinkyUserOutputUserInfoMapper(final PipeAppConfig appConfig) {

		AllowedUsersConfig allowedUsersConfig = appConfig.getAllowedUsersConfig();
		userNameMap.put(allowedUsersConfig.ansibleUser().toLowerCase(),
			UserType.ANSIBLE_USER);
		userNameMap.put(allowedUsersConfig.root().toLowerCase(),
			UserType.ROOT);
		userNameMap.put(allowedUsersConfig.sshUser().toLowerCase(),
			UserType.SSH_USER);
		userNameMap.put(allowedUsersConfig.sudoUser().toLowerCase(),
			UserType.SUDO_USER);
		userNameMap.put(allowedUsersConfig.sudoUser2().toLowerCase(),
			UserType.SUDO_USER2);
	}

	@Override
	public Optional<UserType> apply(final String username) {

		if (username == null) {

			throw new IllegalArgumentException("username null");
		}

		UserType result = userNameMap.get(username.toLowerCase());

		return result == null ? Optional.empty() : Optional.of(result);
	}

}
