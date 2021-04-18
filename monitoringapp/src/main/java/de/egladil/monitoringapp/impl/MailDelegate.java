// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

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

		String empfaenger = StringUtils.join(config.getEmails(), ",");

		DefaultEmailDaten maildaten = new DefaultEmailDaten();
		maildaten.setEmpfaenger(empfaenger);
		maildaten.setBetreff(getSubject(config, new Date()));
		maildaten.setText(messageBody);

		EmailServiceCredentials credentials = EmailServiceCredentials.createInstance(config.getMailhost(), config.getMailport(),
			config.getMailuser(), config.getMailpwd().toCharArray(), config.getMailuser());

		this.commonMailService.sendMail(maildaten, credentials);
	}

	String getSubject(final MonitoringConfig config, final Date jetzt) {

		SimpleDateFormat sdf = new SimpleDateFormat(CommonTimeUtils.DEFAULT_DATE_MINUTES_FORMAT);
		return sdf.format(jetzt) + " " + config.getEnv() + " Monitoring - Fehler";
	}

}
