// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * MonitoringConfigTest
 */
public class MonitoringConfigTest {

	@Test
	void serialize() throws JsonProcessingException {

		// Arrange
		MonitoringConfig config = new MonitoringConfig();

		MailConfig mailConfig = new MailConfig().withMailActivated(true);
		config.setTelegramConfig(new TelegramConfig());

		config.setUrls(
			Arrays.asList(new String[] { "https://opa-wetterwachs.de/authprovider/heartbeats?heartbeatId=felligegurke",
				"https://opa-wetterwachs.de/checklisten-api/heartbeats?heartbeatId=felligegurke" }));
		config.setEmails(Arrays.asList(new String[] { "heikewinkelvoss@web.de", "info@egladil.de" }));
		config.setPollIntervallMinutes(30);
		config.setReadTimeoutMilliSeconds(2000);
		config.setEnv("DEV");
		config.setRandomIntervallSeconds(180);
		config.setMailConfig(mailConfig);

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
			assertEquals(1, config.getEmails().size());
			assertEquals(2, config.getUrls().size());
			assertTrue(config.getEmails().contains("info@egladil.de"));
			assertTrue(config.getUrls().contains("https://google.de"));
			assertTrue(config.getUrls().contains("https://de.wikipedia.org/wiki/Spezial:Zuf%C3%A4llige_Seite"));
			assertEquals(180, config.getRandomIntervallSeconds());
			assertEquals("smtp.provider.com", config.getMailhost());
			assertEquals(123, config.getMailport());
			assertEquals("mail@provider.com", config.getMailuser());
			assertEquals("g3he1m", config.getMailpwd());
			assertEquals(5000, config.getReadTimeoutMilliSeconds());
			assertEquals("11111", config.getTelegramChatId());
			assertEquals("g3he1m~woahahaha", config.getTelegramSecret());
			assertFalse(config.isMailActivated());
		}
	}

	@Test
	void deserializeConfigV2() throws Exception {

		try (InputStream in = new FileInputStream(new File("/home/heike/git/konfigurationen/monitoringapp/monitoring-v2.json"))) {

			// Act
			MonitoringConfig config = new ObjectMapper().readValue(in, MonitoringConfig.class);

			// Assert
			assertEquals(2, config.getEmails().size());
			assertTrue(config.getEmails().contains("info@egladil.de"));
			assertTrue(config.getEmails().contains("public828@gmail.com"));

			List<String> urls = config.getUrls();
			assertEquals(3, urls.size());
			assertTrue(urls.stream().filter(s -> s.startsWith("https://opa-wetterwachs.de/authprovider")).findFirst().isPresent());
			assertTrue(
				urls.stream().filter(s -> s.startsWith("https://opa-wetterwachs.de/checklisten-api")).findFirst().isPresent());
			assertTrue(urls.stream().filter(s -> s.startsWith("https://mathe-jung-alt.de/mk-gateway")).findFirst().isPresent());

			assertEquals(60, config.getRandomIntervallSeconds());
			assertEquals(0, config.getPollIntervallMinutes());
			assertEquals(5000, config.getReadTimeoutMilliSeconds());
			assertTrue(config.isMailActivated());

			assertNotNull(config.getTelegramChatId());
			assertNotNull(config.getTelegramSecret());
		}
	}
}
