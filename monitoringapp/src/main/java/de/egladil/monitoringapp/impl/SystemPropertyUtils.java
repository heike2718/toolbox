//=====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
//=====================================================

package de.egladil.monitoringapp.impl;

/**
 * SystemPropertyUtils
 */
public final class SystemPropertyUtils {

	/**
	 * Erzeugt eine Instanz von SystemPropertyUtils
	 */
	private SystemPropertyUtils() {
	}

	public static String getMandatorySystemProperty(final String key) {
		Object val = System.getProperty(key);
		return val == null ? null : (String) val;
	}

	public static String getOptionalSystemProperty(final String key, final String defaultValue) {
		Object val = System.getProperty(key);
		return val != null ? (String) val : defaultValue;
	}
}
