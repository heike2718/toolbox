// =====================================================
// Project: monitoringapp
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.monitoringapp.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.monitoringapp.MonitoringConfig;

/**
 * TelegramDelegateTest
 */
public class TelegramDelegateTest {

	@Test
	void should_sendMessageWork() throws Exception {

		// Arrange
		MonitoringConfig config = this.loadConfig();

		// Act
		boolean result = new TelegramDelegate().sendMessage("Message aus dem  TelegramDelegateTest", config);

		// Assert
		assertTrue(result);
	}

	private MonitoringConfig loadConfig() throws Exception {

		try (InputStream in = getClass().getResourceAsStream("/.monitoring.json")) {

			final MonitoringConfig config = new ObjectMapper().readValue(in, MonitoringConfig.class);

			return config;
		}
	}
}
