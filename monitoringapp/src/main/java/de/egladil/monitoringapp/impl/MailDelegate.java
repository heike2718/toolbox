// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.egladil.monitoringapp.MessageDelegate;
import de.egladil.monitoringapp.MonitoringConfig;
import de.egladil.web.commons_mailer.CommonEmailService;
import de.egladil.web.commons_mailer.DefaultEmailDaten;
import de.egladil.web.commons_mailer.EmailServiceCredentials;
import de.egladil.web.commons_mailer.impl.CommonEmailServiceImpl;
import de.egladil.web.commons_net.time.CommonTimeUtils;

/**
 * MailDelegate
 */
public class MailDelegate implements MessageDelegate {

	private final CommonEmailService commonMailService;

	/**
	 * Erzeugt eine Instanz von MailDelegate
	 */
	public MailDelegate() {

		this.commonMailService = new CommonEmailServiceImpl();
	}

	@Override
	public void sendMessage(final String messageBody, final MonitoringConfig config) {

		final List<String> emails = config.getEmails();

		DefaultEmailDaten maildaten = new DefaultEmailDaten();
		maildaten.setEmpfaenger(getEmpfaenger(emails));
		maildaten.setBetreff(getSubject(config, new Date()));
		maildaten.setText(messageBody);

		EmailServiceCredentials credentials = EmailServiceCredentials.createInstance(config.getMailhost(), config.getMailport(),
			config.getMailuser(), config.getMailpwd().toCharArray(), config.getMailuser());

		this.commonMailService.sendMail(maildaten, credentials);
	}

	String getEmpfaenger(final List<String> emails) {

		int max = emails.size();
		int count = 0;

		StringBuffer sb = new StringBuffer();

		for (String email : emails) {

			sb.append(email);

			if (count <= max - 2) {

				sb.append(",");
			}
			count++;
		}

		return sb.toString();
	}

	String getSubject(final MonitoringConfig config, final Date jetzt) {

		SimpleDateFormat sdf = new SimpleDateFormat(CommonTimeUtils.DEFAULT_DATE_MINUTES_FORMAT);
		return sdf.format(jetzt) + " " + config.getEnv() + " Monitoring - Fehler";
	}

}
