// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp.impl;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.egladil.monitoringapp.MonitoringConfig;
import de.egladil.web.commons_validation.payload.ResponsePayload;

/**
 * MonitoringTaskTest
 */
public class MonitoringTaskTest {

	private static final int READ_TIMEOUT = 3000;

	@BeforeEach
	public void setUp() {

		System.setProperty(MonitoringConfig.CONFIG_FILE, "/home/heike/git/monitoringapp/src/test/resources/monitoring.json");
	}

	@Test
	void checkGoogle() throws Exception {

		// Arrange
		String requestUrl = "https://google.de";
		MonitoringTask check = new MonitoringTask(requestUrl, READ_TIMEOUT);

		// Act
		ResponsePayload result = check.call();

		// Assert
		assertEquals("INFO", result.getMessage().getLevel());
		assertEquals("OK: https://google.de", result.getMessage().getMessage());
	}

	@Test
	void checkWikipediaWithZufallsseite() throws Exception {

		// Arrange
		String requestUrl = "https://de.wikipedia.org/wiki/Spezial:Zuf%C3%A4llige_Seite";

		MonitoringTask check = new MonitoringTask(requestUrl, READ_TIMEOUT);

		// Act
		ResponsePayload result = check.call();

		// Assert
		assertEquals("INFO", result.getMessage().getLevel());
		assertEquals("OK: https://de.wikipedia.org/wiki/Spezial:Zuf%C3%A4llige_Seite", result.getMessage().getMessage());
	}

	@Test
	void checkChecklistenApi() throws Exception {

		// Arrange
		String requestUrl = "https://opa-wetterwachs.de/authprovider/heartbeats?heartbeatId=heartbeat";

		MonitoringTask check = new MonitoringTask(requestUrl, READ_TIMEOUT);

		// Act
		ResponsePayload result = check.call();

		// Assert
		assertEquals("ERROR", result.getMessage().getLevel());
		assertEquals(
			"ERROR: https://opa-wetterwachs.de/authprovider/heartbeats?heartbeatId=heartbeat - Server returned HTTP response code: 401 for URL: https://opa-wetterwachs.de/authprovider/heartbeats?heartbeatId=heartbeat",
			result.getMessage().getMessage());
	}

	@Test
	void malformedUrl() throws Exception {

		// Arrange
		String requestUrl = "protokoll://eine-seite.de";

		MonitoringTask check = new MonitoringTask(requestUrl, READ_TIMEOUT);

		// Act
		ResponsePayload result = check.call();

		// Assert
		assertEquals("ERROR", result.getMessage().getLevel());
		assertEquals("ERROR: protokoll://eine-seite.de - Konfigurationsfehler: unknown protocol: protokoll",
			result.getMessage().getMessage());
	}
}
