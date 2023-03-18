// =====================================================
// Project: monitoringapp
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.monitoringapp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TelegramConfig
 */
public class TelegramConfig {

	@JsonProperty(value = "secret")
	private String secret;

	@JsonProperty(value = "chatId")
	private String chatId;

	public String getSecret() {

		return secret;
	}

	public void setSecret(final String telegramSecret) {

		this.secret = telegramSecret;
	}

	public String getChatId() {

		return chatId;
	}

	public void setChatId(final String telegramChatId) {

		this.chatId = telegramChatId;
	}

}
