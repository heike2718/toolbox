// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.config;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.web.commons_mailer.MailConfig;
import de.egladil.web.commons_messager.telegram.TelegramConfig;

/**
 * PipeAppConfigTest
 */
public class PipeAppConfigTest {

	@Test
	void should_deserialize() throws JsonProcessingException {

		// Arrange
		AllowedUsersConfig allowedUsersConfig = new AllowedUsersConfig()
			.withAnsibleUser("ansible-user")
			.withRoot("the-root")
			.withSshUser("unpriveleged-ssh-user")
			.withSudoUser("sudo-user");

		MailConfig mailConfig = new MailConfig().withHost("smtp@provider.com").withMailActivated(true)
			.withMailuser("norbert@provider.com").withPort(234).withPwd("g3he!M~");

		TelegramConfig telegramConfig = new TelegramConfig().withChatId("1111111").withSecret("woahahahaha");

		PipeAppConfig config = new PipeAppConfig();
		config.setAllowedUsersConfig(allowedUsersConfig);
		config.setEnv("DEV");
		config.setMailConfig(mailConfig);
		config.setTelegramConfig(telegramConfig);

		// Act
		String deserialized = new ObjectMapper().writeValueAsString(config);

		System.out.println(deserialized);
	}

}
