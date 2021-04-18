// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.publish.impl;

import org.apache.commons.lang3.StringUtils;

import de.egladil.output_telegram_pipe.config.PipeAppConfig;
import de.egladil.output_telegram_pipe.publish.MessagePublisher;
import de.egladil.web.commons_mailer.CommonEmailService;
import de.egladil.web.commons_mailer.DefaultEmailDaten;
import de.egladil.web.commons_mailer.EmailServiceCredentials;
import de.egladil.web.commons_mailer.MailConfig;
import de.egladil.web.commons_mailer.impl.CommonEmailServiceImpl;
import de.egladil.web.commons_validation.SecUtils;

/**
 * MailPublisher
 */
public class MailPublisher implements MessagePublisher {

	private final CommonEmailService commonMailService = new CommonEmailServiceImpl();

	@Override
	public void publishMessage(final String subject, final String messageBody, final PipeAppConfig config) {

		MailConfig mailConfig = config.getMailConfig();

		if (!mailConfig.isMailActivated()) {

			return;
		}

		char[] password = mailConfig.pwd().toCharArray();

		try {

			String empfaenger = StringUtils.join(config.getEmails(), ",");

			DefaultEmailDaten maildaten = new DefaultEmailDaten();
			maildaten.setEmpfaenger(empfaenger);
			maildaten.setBetreff(subject);
			maildaten.setText(messageBody);

			EmailServiceCredentials credentials = EmailServiceCredentials.createInstance(mailConfig.host(), mailConfig.port(),
				mailConfig.user(), password, mailConfig.user());

			this.commonMailService.sendMail(maildaten, credentials);
		} finally {

			SecUtils.wipe(password);
		}

	}
}
