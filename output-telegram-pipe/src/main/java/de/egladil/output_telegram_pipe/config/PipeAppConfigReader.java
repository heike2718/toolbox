// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.egladil.output_telegram_pipe.exceptions.PipeAppConfigurationException;

/**
 * PipeAppConfigReader
 */
public class PipeAppConfigReader {

	private static final Logger LOGGER = LoggerFactory.getLogger(PipeAppConfigReader.class);

	public static final String APPLICATION_PROPERTIES_FILENAME = "/output-telegram-pipe.json";

	/**
	 * Liest die Konfiguration aus dem ClassPath.
	 *
	 * @return
	 */
	public PipeAppConfig readConfiguration() {

		try (InputStream in = getClass().getResourceAsStream(APPLICATION_PROPERTIES_FILENAME)) {

			PipeAppConfig result = this.readConfig(in);
			LOGGER.info("application.properties aus classpath gelesen");

			return result;

		} catch (IOException e) {

			String message = "ClassPath: kann application.properties nicht laden";
			LOGGER.error(message + ": " + e.getMessage());
			throw new PipeAppConfigurationException(message);
		}

	}

	/**
	 * Liest die Konfiguration aus dem FileSystem.
	 *
	 * @param pathConfigDir
	 */
	public PipeAppConfig readConfigurationFromFileSystem(final String pathConfigDir) {

		try (InputStream in = new FileInputStream(new File(pathConfigDir + APPLICATION_PROPERTIES_FILENAME))) {

			return this.readConfig(in);

		} catch (IOException e) {

			String message = "FileSystem: kann application.properties nicht laden: path=" + pathConfigDir;
			LOGGER.error(message + ": " + e.getMessage());
			throw new PipeAppConfigurationException(message);
		}

	}

	private PipeAppConfig readConfig(final InputStream in) throws IOException {

		return new ObjectMapper().readValue(in, PipeAppConfig.class);
	}

}
