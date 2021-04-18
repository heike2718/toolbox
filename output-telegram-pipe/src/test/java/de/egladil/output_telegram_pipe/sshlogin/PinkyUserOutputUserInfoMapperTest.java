// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.sshlogin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.egladil.output_telegram_pipe.config.PipeAppConfig;
import de.egladil.output_telegram_pipe.config.PipeAppConfigReader;

/**
 * PinkyUserOutputUserInfoMapperTest
 */
public class PinkyUserOutputUserInfoMapperTest {

	private PinkyUserOutputUserInfoMapper mapper;

	@BeforeEach
	void setUp() {

		PipeAppConfig appConfig = new PipeAppConfigReader().readConfiguration();
		mapper = new PinkyUserOutputUserInfoMapper(appConfig);
	}

	@Test
	void should_returnEmptyOptional_when_noMappingFound() {

		// Arrange
		String usrname = "boesewicht";

		// Act
		Optional<UserType> result = mapper.apply(usrname);

		// Assert
		assertTrue(result.isEmpty());
	}

	@Test
	void should_returnRootOptional_when_mapsToRoot() {

		// Arrange
		String usrname = "the-root";

		// Act
		Optional<UserType> result = mapper.apply(usrname);

		// Assert
		assertTrue(result.isPresent());
		assertEquals(UserType.ROOT, result.get());
	}

	@Test
	void should_returnAnsibleOptional_when_mapsToAnsibleUser() {

		// Arrange
		String usrname = "ansible-user";

		// Act
		Optional<UserType> result = mapper.apply(usrname);

		// Assert
		assertTrue(result.isPresent());
		assertEquals(UserType.ANSIBLE_USER, result.get());
	}

	@Test
	void should_returnSudoOptional_when_mapsToSudoUser() {

		// Arrange
		String usrname = "sudo-user";

		// Act
		Optional<UserType> result = mapper.apply(usrname);

		// Assert
		assertTrue(result.isPresent());
		assertEquals(UserType.SUDO_USER, result.get());
	}

	@Test
	void should_returnSSHOptional_when_mapsToSSHUser() {

		// Arrange
		String usrname = "unpriveleged-ssh-user";

		// Act
		Optional<UserType> result = mapper.apply(usrname);

		// Assert
		assertTrue(result.isPresent());
		assertEquals(UserType.SSH_USER, result.get());
	}

}
