// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp.impl;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import de.egladil.monitoringapp.MonitoringConfig;
import de.egladil.web.commons_net.time.CommonTimeUtils;

/**
 * MailDelegateTest
 */
public class MailDelegateTest {

	@Test
	void checkSubject() throws ParseException {

		// Arrange
		Date jetzt = new SimpleDateFormat(CommonTimeUtils.DEFAULT_DATE_TIME_FORMAT).parse("22.04.2019 16:20:34");
		MonitoringConfig conf = new MonitoringConfig();
		conf.setEnv("TEST");
		String expected = "22.04.2019 16:20 TEST Monitoring - Fehler";

		// Act
		String actual = new MailDelegate().getSubject(conf, jetzt);

		// Assert
		assertEquals(expected, actual);

	}

}
