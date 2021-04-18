// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe.system.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import de.egladil.output_telegram_pipe.exceptions.PipeException;
import de.egladil.output_telegram_pipe.system.CommandExecutor;

/**
 * SystemCommandExecutor
 */
public class SystemCommandExecutor implements CommandExecutor {

	/**
	 * @return
	 */
	public String executeCommand(final List<String> commands) {

		ProcessBuilder processBuilder = new ProcessBuilder().command(commands);
		Process process = null;

		try {

			process = processBuilder.start();
		} catch (IOException e) {

			throw new PipeException("could not start system process: " + e.getMessage(), e);
		}

		try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

			StringBuilder output = new StringBuilder();

			String line;

			while ((line = bufferedReader.readLine()) != null) {

				output.append(line + "\n");
			}

			int exitVal = process.waitFor();

			if (exitVal == 0) {

				return output.toString();
			}

			throw new PipeException("process returned with exitVal=" + exitVal);

		} catch (IOException | InterruptedException e) {

			String theCommands = StringUtils.join(commands, ",");
			throw new PipeException("could not execute commands'" + theCommands + "': " + e.getMessage(), e);
		} finally {

			process.destroy();
		}
	}
}
