// =====================================================
// Project: monitoringapp
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.monitoringapp.impl;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.monitoringapp.MessageDelegate;
import de.egladil.monitoringapp.MonitoringConfig;
import de.egladil.web.commons_messager.Messager;
import de.egladil.web.commons_messager.MessagerType;
import de.egladil.web.commons_messager.TelegramConfigKeys;

/**
 * TelegramDelegate
 */
public class TelegramDelegate implements MessageDelegate {

	private static final Logger LOG = LoggerFactory.getLogger(TelegramDelegate.class);

	@Override
	public boolean sendMessage(final String messageText, final MonitoringConfig configuration) {

		return doSendWithCommonsMessager(messageText, configuration);
	}

	/**
	 * @param  messageBody
	 * @param  config
	 * @return
	 */
	boolean doSendWithCommonsMessager(final String messageBody, final MonitoringConfig config) {

		Messager messager = Messager.createMessageSenderOfType(MessagerType.TELEGRAM);

		try {

			Properties secretProps = new Properties();
			secretProps.put(TelegramConfigKeys.QUERY_PARAM_CHAT_ID, config.getTelegramChatId());
			secretProps.put(TelegramConfigKeys.SECRET, config.getTelegramSecret());

			Map<String, String> configuration = messager.buildConfiguration(secretProps);

			messager.sendMessage(messageBody, configuration);

			return true;

		} catch (Exception e) {

			String configDescription = messager.getConfigurationDescription().print();
			LOG.error("Message konnte nicht versendet werden: {} - TelegramConfiguration pruefen: {}", e.getMessage(),
				configDescription, e);

			return false;

		}
	}

}
