// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.monitoringapp.MonitoringConfig;
import de.egladil.monitoringapp.MonitoringRunner;
import de.egladil.web.commons_validation.payload.MessagePayload;
import de.egladil.web.commons_validation.payload.ResponsePayload;

/**
 * OneTimeMonitoringRunner
 */
public class OneTimeMonitoringRunner implements MonitoringRunner {

	private static final Logger LOG = LoggerFactory.getLogger(OneTimeMonitoringRunner.class);

	private final MonitoringConfig config;

	private final String lineSeperator;

	/**
	 * Erzeugt eine Instanz von OneTimeMonitoringRunner
	 */
	public OneTimeMonitoringRunner(final MonitoringConfig config) {

		super();
		this.config = config;
		this.lineSeperator = SystemPropertyUtils.getOptionalSystemProperty("line.separator", "\n");
		LOG.info("Anzahl Abfragen: {}, Anzahl Mailempfänger: {}", config.getUrls().size(), config.getEmails().size());
	}

	@Override
	public void execute() {

		// becauseof OptimisticLockExceptions: PROD1 will update heartbeat definitely before PROD2
		sleep();

		List<ResponsePayload> payloads = new ArrayList<>();

		for (String requestUrl : config.getUrls()) {

			System.out.println("check " + StringUtils.abbreviate(requestUrl, 42));

			try {

				ResponsePayload result = new MonitoringTask(requestUrl, config).call();
				System.out.println(result.getMessage());
				payloads.add(result);
			} catch (Exception e) {

				System.err
					.println(e.getClass().getName() + " bei " + StringUtils.abbreviate(requestUrl, 42) + "-" + e.getMessage());
				LOG.error("{} bei request {}: {}", e.getClass().getName(), StringUtils.abbreviate(requestUrl, 42), e.getMessage(),
					e);
				payloads.add(ResponsePayload.messageOnly(MessagePayload.error("Unerwartete Exception: " + e.getMessage())));
			}
		}

		String result = new MessagePrinter(lineSeperator).print(payloads);

		StringBuffer sb = new StringBuffer(lineSeperator);
		sb.append("=== Ergebnis Start ===");
		sb.append(lineSeperator);
		sb.append(result);
		sb.append(lineSeperator);
		sb.append("=== Ergebnis Ende ===");

		System.out.println(sb.toString());
		LOG.info("{}", sb.toString());

		List<ResponsePayload> errorPayloads = payloads.stream().filter(p -> "ERROR".equals(p.getMessage().getLevel()))
			.collect(Collectors.toList());

		if (!errorPayloads.isEmpty()) {

			String text = new MessagePrinter().print(errorPayloads);

			new TelegramDelegate().sendMessage(text, config);

			if (config.isMailActivated()) {

				new MailDelegate().sendMessage(text, config);

			}
		}
	}

	private void sleep() {

		int randomIntervallSeconds = config.getRandomIntervallSeconds();
		int seconds = new Random().nextInt(randomIntervallSeconds);

		if (config.getEnv().startsWith("PROD")) {

			seconds += randomIntervallSeconds;
		}

		try {

			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {

			// interessiert nicht.
		}
	}

	@Override
	public void stop() {

		// interessiert uns nicht
	}
}
