package de.egladil.monitoringapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.monitoringapp.exceptions.ConfigurationException;
import de.egladil.monitoringapp.impl.SystemPropertyUtils;

/**
 * MonitoringApp
 */
public class MonitoringApp {

	private static final Logger LOG = LoggerFactory.getLogger(MonitoringApp.class);

	public static void main(final String[] args) {

		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("number cores: " + cores);
		// printSystemProperties();

		try {

			MonitoringConfig config = getAndCheckConfig();

			if (config == null) {

				printUsageAndExit();
			}
			final MonitoringRunner runner = MonitoringRunner.create(config);

			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {

					runner.stop();
				}
			});

			runner.execute();

			System.out.println("Fertig");
		} catch (IOException e) {

			LOG.error(e.getMessage());
			printUsageAndExit();
		} catch (ConfigurationException e) {

			System.err.println(e.getMessage());
			System.exit(2);
		} catch (Exception e) {

			e.printStackTrace();
			System.err.println("Unerwartete Exception " + e.getClass().getName() + ": " + e.getMessage());
			System.exit(3);
		}
	}

	private static void printSystemProperties() {

		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("number cores: " + cores);
		Enumeration<?> enumer = System.getProperties().propertyNames();

		while (enumer.hasMoreElements()) {

			String key = (String) enumer.nextElement();
			System.out.println(key + " - " + System.getProperty(key));
		}

	}

	private static void printUsageAndExit() {

		System.err.println("Konnte Konfiguration nicht lesen: bitte System.property '-D" + MonitoringConfig.CONFIG_FILE
			+ "' auf eine Json-Datei setzen, die zu MonitoringConfig deserialisiert werden kann.");
		System.exit(1);
	}

	private static MonitoringConfig getAndCheckConfig() throws FileNotFoundException, IOException {

		String pathConfig = SystemPropertyUtils.getMandatorySystemProperty(MonitoringConfig.CONFIG_FILE);

		if (pathConfig == null) {

			return null;
		}

		try (InputStream in = new FileInputStream(new File(pathConfig))) {

			final MonitoringConfig config = new ObjectMapper().readValue(in, MonitoringConfig.class);

			if (config.getEmails().isEmpty()) {

				throw new ConfigurationException(pathConfig + ": es muss mindestens eine Mailadresse eingetragen sein.");
			}

			if (config.getUrls().isEmpty()) {

				throw new ConfigurationException(pathConfig + ": es muss mindestens eine URL eingetragen sein.");
			}

			return config;
		}
	}
}
