// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.egladil.monitoringapp.MonitoringConfig;
import de.egladil.web.commons_net.content.ContentReader;
import de.egladil.web.commons_net.content.SimpleContentReader;
import de.egladil.web.commons_validation.payload.MessagePayload;
import de.egladil.web.commons_validation.payload.ResponsePayload;

/**
 * MonitoringTask
 */
public class MonitoringTask implements Callable<ResponsePayload> {

	private static final Logger LOG = LoggerFactory.getLogger(MonitoringTask.class);

	private final String requestUrl;

	private final ContentReader contentReader;

	private final Integer readTimeoutMilliSeconds;

	/**
	 * Erzeugt eine Instanz von MonitoringTask
	 */
	public MonitoringTask(final String requestUrl, final Integer readTimeoutMilliSeconds) {

		this.requestUrl = requestUrl;
		this.contentReader = new SimpleContentReader();
		this.readTimeoutMilliSeconds = readTimeoutMilliSeconds;
	}

	@Override
	public ResponsePayload call() throws Exception {

		String requestUrlAbbr = StringUtils.abbreviate(requestUrl, 42);

		LOG.debug("call {}", requestUrlAbbr);

		try {

			URL url = new URL(requestUrl);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("Accept", "*/*");
			conn.setConnectTimeout(readTimeoutMilliSeconds);
			conn.setReadTimeout(5000);

			byte[] bytes = this.contentReader.getBytes(conn);
			LOG.debug("Anzahl bytes: {}", bytes.length);

			return ResponsePayload.messageOnly(MessagePayload.info("OK: " + requestUrlAbbr));
		} catch (MalformedURLException e) {

			String pathConfig = System.getProperty(MonitoringConfig.CONFIG_FILE);
			LOG.error("Konfigurationsfehler in {}: {}", pathConfig, e.getMessage());
			return ResponsePayload
				.messageOnly(MessagePayload.error("ERROR: " + requestUrlAbbr + " - Konfigurationsfehler: " + e.getMessage()));
		} catch (SSLHandshakeException e) {

			LOG.info(
				"SSLHandshakeException: please import missing root-certificate calling 'keytool -import -alias letsencrypt -keystore  $JAVA_HOME/jre/lib/security/cacerts -file /usr/local/share/ca-certificates/letsencrypt.crt'");
			return ResponsePayload.messageOnly(MessagePayload.error("ERROR: " + requestUrlAbbr + " - " + e.getMessage()));
		} catch (IOException e) {

			LOG.error("IOException bei {}: {}", requestUrlAbbr, e.getMessage());
			return ResponsePayload.messageOnly(MessagePayload.error("ERROR: " + requestUrlAbbr + " - " + e.getMessage()));
		} catch (Exception e) {

			LOG.error("Unerwartete Exception bei {}: {}", requestUrlAbbr, e.getMessage(), e);
			return ResponsePayload.messageOnly(MessagePayload.error("ERROR: " + requestUrlAbbr + " - " + e.getMessage()));
		}
	}
}
