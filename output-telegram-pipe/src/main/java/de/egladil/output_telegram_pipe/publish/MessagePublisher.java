// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.publish;

import de.egladil.output_telegram_pipe.config.PipeAppConfig;

/**
 * MessagePublisher
 */
public interface MessagePublisher {

	/**
	 * Versendet die Message.
	 *
	 * @param subject
	 * @param messageBody
	 * @param config
	 */
	void publishMessage(String subject, String messageBody, PipeAppConfig config);

}
