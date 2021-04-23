// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.sshlogin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.egladil.output_telegram_pipe.config.PipeAppConfig;
import de.egladil.output_telegram_pipe.config.PipeAppConfigReader;
import de.egladil.web.commons_validation.payload.MessagePayload;

/**
 * ShortPinkyOutputSSHLoginInfoMapperTest
 */
public class ShortPinkyOutputSSHLoginInfoMapperTest {

	private ShortPinkyOutputSSHLoginInfoMapper mapper;

	@BeforeEach
	void setUp() {

		PipeAppConfig appConfig = new PipeAppConfigReader().readConfiguration();

		mapper = new ShortPinkyOutputSSHLoginInfoMapper(appConfig);
	}

	@Test
	void should_mapperReturnError_when_only4TokensPresent() {

		// Arrange
		String outcome = "sudo-user  2021-04-18 08:22 192.168.10.176";

		// Act
		SSHLoginInfo loginInfo = mapper.apply(outcome);

		// Assert
		assertTrue(loginInfo.isError());
		assertEquals(null, loginInfo.wer());
		assertEquals(null, loginInfo.wann());
		assertEquals(null, loginInfo.vonWo());

		MessagePayload mp = loginInfo.getMessagePayload();
		assertEquals("ERROR", mp.getLevel());
		assertEquals("pinky command gibt weniger als 5 token zurueck!!!", mp.getMessage());

	}

	@Test
	void should_mapperReturnWarn_when_allTokensPresentAndMapsToAllowedUser() {

		// Arrange
		String outcome = "sudo-user   pts/2    01:03  2021-04-18 08:22 192.168.10.176";

		// Act
		SSHLoginInfo loginInfo = mapper.apply(outcome);

		// Assert
		assertFalse(loginInfo.isError());
		assertEquals("SUDO_USER", loginInfo.wer());
		assertNull(loginInfo.wann());
		assertEquals("192.168.10.176", loginInfo.vonWo());

		MessagePayload mp = loginInfo.getMessagePayload();
		assertEquals("WARN", mp.getLevel());
		assertEquals("===  erfolgreiches Login eines erlaubten users  ===", mp.getMessage());

	}

	@Test
	void should_mapperReturnWarn_when_inactiveNotPresentAndMapsToAllowedUser() {

		// Arrange
		String outcome = "ansible-user   pts/1           2021-04-18 08:24 192.168.10.176";

		// Act
		SSHLoginInfo loginInfo = mapper.apply(outcome);

		// Assert
		assertFalse(loginInfo.isError());
		assertEquals("ANSIBLE_USER", loginInfo.wer());
		assertNull(loginInfo.wann());
		assertEquals("192.168.10.176", loginInfo.vonWo());

		MessagePayload mp = loginInfo.getMessagePayload();
		assertEquals("WARN", mp.getLevel());
		assertEquals("===  erfolgreiches Login eines erlaubten users  ===", mp.getMessage());

	}

	@Test
	void should_mapperReturnWarnAndTheUserName_when_allTokensPresentAndDowsNotMapToAllowedUser() {

		// Arrange
		String outcome = "boesewicht   pts/2    01:03  2021-04-18 08:22 192.168.10.176";

		// Act
		SSHLoginInfo loginInfo = mapper.apply(outcome);

		// Assert
		assertFalse(loginInfo.isError());
		assertEquals("boesewicht", loginInfo.wer());
		assertNull(loginInfo.wann());
		assertEquals("192.168.10.176", loginInfo.vonWo());

		MessagePayload mp = loginInfo.getMessagePayload();
		assertEquals("WARN", mp.getLevel());
		assertEquals("!!!!  erfolgreiches Login eines unbekannten users  !!!", mp.getMessage());

	}

	@Test
	void should_mapperReturnWarnAndTheUserName_when_inactiveNotPresentAndDowsNotMapToAllowedUser() {

		// Arrange
		String outcome = "boesewicht   pts/1           2021-04-18 08:24 192.168.10.176";

		// Act
		SSHLoginInfo loginInfo = mapper.apply(outcome);

		// Assert
		assertFalse(loginInfo.isError());
		assertEquals("boesewicht", loginInfo.wer());
		assertNull(loginInfo.wann());
		assertEquals("192.168.10.176", loginInfo.vonWo());

		MessagePayload mp = loginInfo.getMessagePayload();
		assertEquals("WARN", mp.getLevel());
		assertEquals("!!!!  erfolgreiches Login eines unbekannten users  !!!", mp.getMessage());

	}

}
