// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MonitoringConfig hat folgende Parameter:
 * <ul>
 * <li><strong>urls: </strong>Die URLs die nacheinander abgefragt werden sollen.</li>
 * <li><strong>emails: </strong>Die emails, an die im Fehlerfall gesendet werden soll.</li>
 * <li><strong>pollIntervallMinutes: </strong>Länge eines Poll-Intervalls in Minuten. Bei 0 findet kein pollen statt.
 * Dann werden alle Tests genau einmal ausgeführt.</li>
 * <li><strong>randomIntervallSeconds: </strong>Wenn es Abfragen in einer Schleife geben soll, wird das Intervall
 * zufällig um maximal diese Anzahl Sekunden verlängert.</li>
 * </ul>
 */
public class MonitoringConfig {

	public static final String CONFIG_FILE = "monitoring.configurationFile";

	@JsonProperty
	private String env;

	@JsonProperty
	private String pathTruststoreFile;

	@JsonProperty
	private List<String> urls;

	@JsonProperty
	private List<String> emails;

	@JsonProperty
	private int pollIntervallMinutes;

	@JsonProperty
	private int randomIntervallSeconds;

	@JsonProperty
	private int readTimeoutMilliSeconds;

	@JsonProperty
	private boolean mailActivated;

	@JsonProperty
	private String mailhost;

	@JsonProperty
	private int mailport;

	@JsonProperty
	private String mailuser;

	@JsonProperty
	private String mailpwd;

	@JsonProperty
	private String telegramSecret;

	@JsonProperty
	private String telegramChatId;

	public List<String> getUrls() {

		return urls;
	}

	public void setUrls(final List<String> urls) {

		this.urls = urls;
	}

	public List<String> getEmails() {

		return emails;
	}

	public void setEmails(final List<String> emails) {

		this.emails = emails;
	}

	public int getPollIntervallMinutes() {

		return pollIntervallMinutes;
	}

	public void setPollIntervallMinutes(final int pollIntervallSeconds) {

		this.pollIntervallMinutes = pollIntervallSeconds;
	}

	public String getMailhost() {

		return mailhost;
	}

	public void setMailhost(final String mailhost) {

		this.mailhost = mailhost;
	}

	public int getMailport() {

		return mailport;
	}

	public void setMailport(final int mailport) {

		this.mailport = mailport;
	}

	public String getMailuser() {

		return mailuser;
	}

	public void setMailuser(final String mailuser) {

		this.mailuser = mailuser;
	}

	public String getMailpwd() {

		return mailpwd;
	}

	public void setMailpwd(final String mailpassword) {

		this.mailpwd = mailpassword;
	}

	public String getEnv() {

		return env;
	}

	public void setEnv(final String env) {

		this.env = env;
	}

	public int getRandomIntervallSeconds() {

		return randomIntervallSeconds;
	}

	public void setRandomIntervallSeconds(final int randomIntervallSeconds) {

		this.randomIntervallSeconds = randomIntervallSeconds;
	}

	public int getReadTimeoutMilliSeconds() {

		return readTimeoutMilliSeconds;
	}

	public void setReadTimeoutMilliSeconds(final int readTimeoutMilliSeconds) {

		this.readTimeoutMilliSeconds = readTimeoutMilliSeconds;
	}

	public String getPathTruststoreFile() {

		return pathTruststoreFile;
	}

	public void setPathTruststoreFile(final String pathTruststoreFile) {

		this.pathTruststoreFile = pathTruststoreFile;
	}

	public String getTelegramSecret() {

		return telegramSecret;
	}

	public void setTelegramSecret(final String telegramSecret) {

		this.telegramSecret = telegramSecret;
	}

	public String getTelegramChatId() {

		return telegramChatId;
	}

	public void setTelegramChatId(final String telegramClientId) {

		this.telegramChatId = telegramClientId;
	}

	public boolean isMailActivated() {

		return mailActivated;
	}

	public void setMailActivated(final boolean mailActivated) {

		this.mailActivated = mailActivated;
	}
}
