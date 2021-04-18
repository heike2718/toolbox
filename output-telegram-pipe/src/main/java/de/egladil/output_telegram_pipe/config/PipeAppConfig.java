// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.config;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.egladil.web.commons_mailer.MailConfig;
import de.egladil.web.commons_messager.telegram.TelegramConfig;

/**
 * PipeAppConfig
 */
public class PipeAppConfig {

	@JsonProperty
	private String env;

	@JsonProperty
	private AllowedUsersConfig allowedUsersConfig;

	@JsonProperty
	private List<String> emails;

	@JsonProperty
	private MailConfig mailConfig;

	@JsonProperty
	private TelegramConfig telegramConfig;

	public String getEnv() {

		return env;
	}

	public void setEnv(final String env) {

		this.env = env;
	}

	public AllowedUsersConfig getAllowedUsersConfig() {

		return allowedUsersConfig;
	}

	public void setAllowedUsersConfig(final AllowedUsersConfig allowedUsersConfig) {

		this.allowedUsersConfig = allowedUsersConfig;
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

	public List<String> getEmails() {

		return emails;
	}

	public void setEmails(final List<String> emails) {

		this.emails = emails;
	}

}
