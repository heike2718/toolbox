// =====================================================
// Project: monitoringapp
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.monitoringapp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MailConfig
 */
public class MailConfig {

	@JsonProperty(value = "activated")
	private boolean mailActivated;

	@JsonProperty(value = "host")
	private String mailhost;

	@JsonProperty(value = "port")
	private int mailport;

	@JsonProperty(value = "user")
	private String mailuser;

	@JsonProperty(value = "pwd")
	private String mailpwd;

	public boolean isMailActivated() {

		return this.mailActivated;
	}

	public String getMailhost() {

		return mailhost;
	}

	public int getMailport() {

		return mailport;
	}

	public String getMailuser() {

		return mailuser;
	}

	public String getMailpwd() {

		return mailpwd;
	}

	public MailConfig withMailActivated(final boolean mailActivated) {

		this.mailActivated = mailActivated;
		return this;
	}

	public MailConfig withMailhost(final String mailhost) {

		this.mailhost = mailhost;
		return this;
	}

	public MailConfig withMailport(final int mailport) {

		this.mailport = mailport;
		return this;
	}

	public MailConfig withMailuser(final String mailuser) {

		this.mailuser = mailuser;
		return this;
	}

	public MailConfig withMailpwd(final String mailpwd) {

		this.mailpwd = mailpwd;
		return this;
	}

}
