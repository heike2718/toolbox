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
	private String heartbeatId;

	@JsonProperty
	private TelegramConfig telegramConfig;

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

		return mailConfig.getMailhost();
	}

	public int getMailport() {

		return mailConfig.getMailport();
	}

	public String getMailuser() {

		return mailConfig.getMailuser();
	}

	public String getMailpwd() {

		return mailConfig.getMailpwd();
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

	public String getTelegramSecret() {

		return telegramConfig.getSecret();
	}

	public String getTelegramChatId() {

		return telegramConfig.getChatId();
	}

	public boolean isMailActivated() {

		return mailConfig.isMailActivated();
	}

	public MailConfig getMailConfig() {

		return mailConfig;
	}

	public void setMailConfig(final MailConfig mailConfig) {

		this.mailConfig = mailConfig;
	}

	public TelegramConfig getTelegramConfig() {

		return telegramConfig;
	}

	public void setTelegramConfig(final TelegramConfig telegramConfig) {

		this.telegramConfig = telegramConfig;
	}

	public String getHeartbeatId() {

		return heartbeatId;
	}

	public void setHeartbeatId(final String heartbeatId) {

		this.heartbeatId = heartbeatId;
	}
}
