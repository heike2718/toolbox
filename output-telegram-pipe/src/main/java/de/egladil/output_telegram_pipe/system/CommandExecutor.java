// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.system;

import java.util.List;

/**
 * CommandExecutor
 */
public interface CommandExecutor {

	/**
	 * Führt ein Command aus und gibt den outcome zurück.
	 *
	 * @return String
	 */
	String executeCommand(List<String> commands);

}
