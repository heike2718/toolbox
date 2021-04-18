// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.sshlogin;

import de.egladil.web.commons_validation.payload.MessagePayload;

/**
 * SSHLoginInfo
 */
public class SSHLoginInfo {

	private MessagePayload messagePayload = MessagePayload.ok();

	private String user;

	private String datum;

	private String uhrzeit;

	private String ipAddress;

	@Override
	public String toString() {

		return "SSHLoginInfo [user=" + user + ", datum=" + datum + ", uhrzeit=" + uhrzeit + ", ipAddress=" + ipAddress + "]";
	}

	/**
	 * @return the user
	 */
	public String wer() {

		return user;
	}

	/**
	 * @param user
	 *             the user to set
	 */
	public SSHLoginInfo withUser(final String user) {

		this.user = user;
		return this;
	}

	/**
	 * @return the datum
	 */
	public String wann() {

		return datum == null ? null : datum + " " + this.uhrzeit;
	}

	/**
	 * @param datum
	 *              the datum to set
	 */
	public SSHLoginInfo withDatum(final String datum) {

		this.datum = datum;
		return this;
	}

	/**
	 * @param uhrzeit
	 *                the uhrzeit to set
	 */
	public SSHLoginInfo withUhrzeit(final String uhrzeit) {

		this.uhrzeit = uhrzeit;
		return this;
	}

	/**
	 * @return the ipAddress
	 */
	public String vonWo() {

		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *                  the ipAddress to set
	 */
	public SSHLoginInfo withIpAddress(final String ipAddress) {

		this.ipAddress = ipAddress;
		return this;
	}

	/**
	 * @return the messagePayload
	 */
	public MessagePayload getMessagePayload() {

		return messagePayload;
	}

	/**
	 * @param messagePayload
	 *                       the messagePayload to set
	 */
	public SSHLoginInfo withMessagePayload(final MessagePayload messagePayload) {

		this.messagePayload = messagePayload;
		return this;
	}

	public boolean isError() {

		return "ERROR".equalsIgnoreCase(messagePayload.getLevel());
	}
}
