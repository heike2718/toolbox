// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.exceptions;

/**
 * PipeAppConfigurationException
 */
public class PipeAppConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PipeAppConfigurationException(final String message, final Throwable cause) {

		super(message, cause);

	}

	public PipeAppConfigurationException(final String message) {

		super(message);

	}

}
