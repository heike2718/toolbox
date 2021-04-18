// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe;

import de.egladil.output_telegram_pipe.sshlogin.PropagateSSHLoginCommand;
import de.egladil.output_telegram_pipe.system.impl.SystemCommandExecutor;

/**
 * PipeCommand
 */
public interface PipeCommand {

	/**
	 * Führt das gewünschte Command aus.
	 *
	 * @param args
	 */
	void execute(CLIArgs args);

	PipeType getType();

	static PipeCommand createCommandOfType(final PipeType type) {

		switch (type) {

		case SSHLOGIN:

			return new PropagateSSHLoginCommand(new SystemCommandExecutor());

		default:
			break;
		}

		throw new IllegalArgumentException("keine Implementierung fuer PipeType " + type + " vorhanden");
	}

}
