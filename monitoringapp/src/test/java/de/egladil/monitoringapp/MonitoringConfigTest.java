// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.web.commons_mailer.MailConfig;
import de.egladil.web.commons_messager.telegram.TelegramConfig;

/**
 * MonitoringConfigTest
 */
public class MonitoringConfigTest {

	@Test
	void serialize() throws JsonProcessingException {

		// Arrange
		MonitoringConfig config = new MonitoringConfig();
		config.setUrls(
			Arrays.asList(new String[] { "https://opa-wetterwachs.de/authprovider/heartbeats?heartbeatId=felligegurke",
				"https://opa-wetterwachs.de/checklisten-api/heartbeats?heartbeatId=felligegurke" }));
		config.setEmails(Arrays.asList(new String[] { "heikewinkelvoss@web.de", "info@egladil.de" }));
		config.setPollIntervallMinutes(30);
		config.setReadTimeoutMilliSeconds(2000);
		config.setEnv("DEV");
		config.setRandomIntervallSeconds(180);
		config.setPathTruststoreFile("/usr/lib/jvm/jdk-11/lib/security");

		MailConfig mailConfig = new MailConfig().withHost("smtp.provider.com").withMailActivated(true)
			.withMailuser("mail@provider.com")
			.withPort(232).withPwd("g3he1m!");

		config.setMailConfig(mailConfig);

		TelegramConfig telegramConfig = new TelegramConfig().withChatId("11111").withSecret("g3he1m~woahahaha");

		config.setTelegramConfig(telegramConfig);

		// Act
		String str = new ObjectMapper().writeValueAsString(config);
		System.out.println(str);

		// Assert
		// {"env":"DEV","urls":["https://google.de","https://de.wikipedia.org/wiki/Spezial:Zuf%C3%A4llige_Seite"],"emails":["heikewinkelvoss@web.de","info@egladil.de"],"pollIntervallMinutes":30,"randomIntervallSeconds":180,"readTimeoutMilliSeconds":2000,"mailhost":null,"mailport":0,"mailuser":null,"mailpwd":null}
		// assertEquals(
		// "{\"env\":\"DEV\",\"urls\":[\"https://google.de\",\"https://de.wikipedia.org/wiki/Spezial:Zuf%C3%A4llige_Seite\"],\"emails\":[\"heikewinkelvoss@web.de\",\"info@egladil.de\"],\"pollIntervallMinutes\":30,\"randomIntervallSeconds\":180,\"readTimeoutMilliSeconds\":2000,\"mailhost\":null,\"mailport\":0,\"mailuser\":null,\"mailpwd\":null}",
		// str);

	}

	@Test
	void deserialize() throws IOException {

		try (InputStream in = getClass().getResourceAsStream("/monitoring.json")) {

			// Act
			MonitoringConfig config = new ObjectMapper().readValue(in, MonitoringConfig.class);

			// Assert
			assertEquals(30, config.getPollIntervallMinutes());
			assertEquals(2, config.getEmails().size());
			assertEquals(2, config.getUrls().size());
			assertTrue(config.getEmails().contains("heikewinkelvoss@web.de"));
			assertTrue(config.getEmails().contains("info@egladil.de"));
			assertTrue(config.getUrls().contains("https://google.de"));
			assertTrue(config.getUrls().contains("https://de.wikipedia.org/wiki/Spezial:Zuf%C3%A4llige_Seite"));
			assertEquals(180, config.getRandomIntervallSeconds());
			assertTrue(config.isMailActivated());
			assertEquals("smtp.provider.com", config.getMailhost());
			assertEquals(123, config.getMailport());
			assertEquals("mail@provider.com", config.getMailuser());
			assertEquals("g3he1m!", config.getMailpwd());
			assertEquals(5000, config.getReadTimeoutMilliSeconds());
			assertEquals("11111", config.getTelegramChatId());
			assertEquals("g3he1m~woahahaha", config.getTelegramSecret());
		}
	}

	@Test
	void deserializeConfigV2() throws Exception {

		try (InputStream in = new FileInputStream(new File("/home/heike/git/konfigurationen/monitoringapp/monitoring.json"))) {

			// Act
			MonitoringConfig config = new ObjectMapper().readValue(in, MonitoringConfig.class);

			// Assert
			assertEquals(1, config.getEmails().size());
			assertTrue(config.getEmails().contains("info@egladil.de"));

			List<String> urls = config.getUrls();
			assertEquals(3, urls.size());
			assertTrue(urls.stream().filter(s -> s.startsWith("https://opa-wetterwachs.de/authprovider")).findFirst().isPresent());
			assertTrue(
				urls.stream().filter(s -> s.startsWith("https://opa-wetterwachs.de/checklisten-api")).findFirst().isPresent());
			assertTrue(urls.stream().filter(s -> s.startsWith("https://mathe-jung-alt.de/mk-gateway")).findFirst().isPresent());

			assertEquals(60, config.getRandomIntervallSeconds());
			assertEquals(0, config.getPollIntervallMinutes());
			assertEquals(5000, config.getReadTimeoutMilliSeconds());
			assertFalse(config.isMailActivated());
		}
	}
}
