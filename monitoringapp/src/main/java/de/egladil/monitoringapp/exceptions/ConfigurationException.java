// =====================================================
// Project: monitoringapp
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.monitoringapp.exceptions;

/**
 * ConfigurationException
 */
public class ConfigurationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConfigurationException(final String message, final Throwable cause) {

		super(message, cause);

	}

	public ConfigurationException(final String message) {

		super(message);

	}

}
