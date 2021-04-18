// =====================================================
// Project: output-telegram-pipe
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.output_telegram_pipe;

import org.apache.commons.lang3.StringUtils;

import com.beust.jcommander.Parameter;

/**
 * CLIArgs
 */
public class CLIArgs {

	@Parameter(
		names = { "-c", "--config" }, description = "absolute path to directory containing application.properties",
		required = false)
	private String configDir;

	@Parameter(
		names = { "-t", "--pipetype" },
		description = "(required when not help)) type of the pipe according to enum PipeType. Is case insensitive. Application will map it to uppper case",
		required = false)
	private String pipeType;

	@Parameter(names = { "-h", "--help" }, description = "print available pipe types", help = true)
	private boolean help;

	public String printHelp() {

		StringBuffer sb = new StringBuffer();
		sb.append("Folgende pipetypes existieren: ");
		sb.append(StringUtils.join(PipeType.values(), ","));
		return sb.toString();
	}

	@Override
	public String toString() {

		return "CLIArgs [pipeType=" + pipeType + ", configDir=" + configDir + ", help=" + help + "]";
	}

	public String getConfigDir() {

		return configDir;
	}

	public PipeType getPipeType() {

		return StringUtils.isBlank(pipeType) ? null : PipeType.valueOf(pipeType.toUpperCase());
	}

	CLIArgs withPipeType(final String pipeType) {

		this.pipeType = pipeType;
		return this;
	}

	/**
	 * @return the help
	 */
	public boolean help() {

		return help;
	}

	public CLIArgs withConfigDir(final String configDir) {

		this.configDir = configDir;
		return this;
	}
}
