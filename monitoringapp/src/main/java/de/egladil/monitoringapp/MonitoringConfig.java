// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import de.egladil.web.commons_mailer.MailConfig;
import de.egladil.web.commons_messager.telegram.TelegramConfig;

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
	private MailConfig mailConfig;

	@JsonProperty
	private TelegramConfig telegramConfig;

	@JsonIgnore
	public List<String> getUrls() {

		return urls;
	}

	public void setUrls(final List<String> urls) {

		this.urls = urls;
	}

	@JsonIgnore
	public List<String> getEmails() {

		return emails;
	}

	public void setEmails(final List<String> emails) {

		this.emails = emails;
	}

	@JsonIgnore
	public int getPollIntervallMinutes() {

		return pollIntervallMinutes;
	}

	public void setPollIntervallMinutes(final int pollIntervallSeconds) {

		this.pollIntervallMinutes = pollIntervallSeconds;
	}

	@JsonIgnore
	public String getMailhost() {

		return this.mailConfig.host();
	}

	@JsonIgnore
	public int getMailport() {

		return this.mailConfig.port();
	}

	@JsonIgnore
	public String getMailuser() {

		return this.mailConfig.user();
	}

	@JsonIgnore
	public String getMailpwd() {

		return this.mailConfig.pwd();
	}

	@JsonIgnore
	public String getTelegramSecret() {

		return this.telegramConfig.secret();
	}

	@JsonIgnore
	public String getTelegramChatId() {

		return this.telegramConfig.chatId();
	}

	@JsonIgnore
	public String getEnv() {

		return env;
	}

	public void setEnv(final String env) {

		this.env = env;
	}

	@JsonIgnore
	public int getRandomIntervallSeconds() {

		return randomIntervallSeconds;
	}

	public void setRandomIntervallSeconds(final int randomIntervallSeconds) {

		this.randomIntervallSeconds = randomIntervallSeconds;
	}

	@JsonIgnore
	public int getReadTimeoutMilliSeconds() {

		return readTimeoutMilliSeconds;
	}

	public void setReadTimeoutMilliSeconds(final int readTimeoutMilliSeconds) {

		this.readTimeoutMilliSeconds = readTimeoutMilliSeconds;
	}

	@JsonIgnore
	public String getPathTruststoreFile() {

		return pathTruststoreFile;
	}

	public void setPathTruststoreFile(final String pathTruststoreFile) {

		this.pathTruststoreFile = pathTruststoreFile;
	}

	@JsonIgnore
	public boolean isMailActivated() {

		return this.mailConfig.isMailActivated();
	}

	public void setTelegramConfig(final TelegramConfig telegramConfig) {

		this.telegramConfig = telegramConfig;
	}

	/**
	 * @param mailConfig
	 *                   the mailConfig to set
	 */
	public void setMailConfig(final MailConfig mailConfig) {

		this.mailConfig = mailConfig;
	}
}
