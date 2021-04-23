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

	private String sshUser;

	private String currentTime;

	private String ipAddress;

	private String currentUser;

	@Override
	public String toString() {

		return "SSHLoginInfo [sshUser=" + sshUser + ", datumUndUhrzeit=" + currentTime + ", ipAddress=" + ipAddress
			+ ", currentUser=" + currentUser + "]";
	}

	public String wer() {

		return sshUser;
	}

	public SSHLoginInfo withSshUser(final String user) {

		this.sshUser = user;
		return this;
	}

	public String wann() {

		return currentTime;
	}

	public SSHLoginInfo withCurrentTime(final String currentTime) {

		this.currentTime = currentTime;
		return this;
	}

	public String vonWo() {

		return ipAddress;
	}

	public SSHLoginInfo withIpAddress(final String ipAddress) {

		this.ipAddress = ipAddress;
		return this;
	}

	public String currentUser() {

		return currentUser;
	}

	public SSHLoginInfo withCurrentUser(final String currentUser) {

		this.currentUser = currentUser;
		return this;
	}

	public MessagePayload getMessagePayload() {

		return messagePayload;
	}

	public SSHLoginInfo withMessagePayload(final MessagePayload messagePayload) {

		this.messagePayload = messagePayload;
		return this;
	}

	public boolean isError() {

		return "ERROR".equalsIgnoreCase(messagePayload.getLevel());
	}

}
