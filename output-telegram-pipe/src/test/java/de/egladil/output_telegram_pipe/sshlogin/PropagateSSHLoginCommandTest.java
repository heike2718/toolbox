// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.sshlogin;

import org.junit.jupiter.api.Test;

import de.egladil.output_telegram_pipe.CLIArgs;
import de.egladil.output_telegram_pipe.system.impl.SystemCommandExecutor;

/**
 * PropagateSSHLoginCommandTest
 */
public class PropagateSSHLoginCommandTest {

	@Test
	void should_executeWork() {

		// Arrange
		CLIArgs cliArgs = new CLIArgs().withConfigDir("/home/heike/git/konfigurationen/output-telegram-pipe");
		PropagateSSHLoginCommand command = new PropagateSSHLoginCommand(new SystemCommandExecutor());

		// Act
		command.execute(cliArgs);

	}

}
