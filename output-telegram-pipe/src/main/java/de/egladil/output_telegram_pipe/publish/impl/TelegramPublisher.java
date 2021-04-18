// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.publish.impl;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.output_telegram_pipe.config.PipeAppConfig;
import de.egladil.output_telegram_pipe.publish.MessagePublisher;
import de.egladil.web.commons_messager.Messager;
import de.egladil.web.commons_messager.MessagerType;
import de.egladil.web.commons_messager.TelegramConfigKeys;
import de.egladil.web.commons_messager.telegram.TelegramConfig;
import de.egladil.web.commons_validation.SecUtils;

/**
 * TelegramDelegate
 */
public class TelegramPublisher implements MessagePublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(TelegramPublisher.class);

	@Override
	public void publishMessage(final String subject, final String messageBody, final PipeAppConfig config) {

		Messager messager = Messager.createMessageSenderOfType(MessagerType.TELEGRAM);
		TelegramConfig telegramConfig = config.getTelegramConfig();
		String secret = null;
		String chatId = null;

		try {

			chatId = telegramConfig.chatId();
			secret = telegramConfig.secret();

			Properties secretProps = new Properties();
			secretProps.put(TelegramConfigKeys.QUERY_PARAM_CHAT_ID, chatId);
			secretProps.put(TelegramConfigKeys.SECRET, secret);

			Map<String, String> configuration = messager.buildConfiguration(secretProps);

			messager.sendMessage(messageBody, configuration);

		} catch (Exception e) {

			String configDescription = messager.getConfigurationDescription().print();
			LOGGER.error("Message konnte nicht versendet werden: {} - TelegramConfiguration pruefen: {}", e.getMessage(),
				configDescription, e);

		} finally {

			SecUtils.wipe(secret);
			SecUtils.wipe(chatId);
		}

	}

}
