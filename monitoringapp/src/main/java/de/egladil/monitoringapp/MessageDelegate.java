// =====================================================
// Project: monitoringapp
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.monitoringapp;

/**
 * MessageDelegate
 */
public interface MessageDelegate {

	/**
	 * @param errorPayloads
	 * @param emails
	 */
	void sendMessage(String messageBody, MonitoringConfig config);

}
