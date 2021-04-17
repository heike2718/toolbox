// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp.impl;

import java.util.List;

import de.egladil.web.commons_validation.payload.ResponsePayload;

/**
 * MessagePrinter
 */
public class MessagePrinter {

	private final String lineSeperator;

	/**
	 * Erzeugt eine Instanz von MessagePrinter
	 */
	public MessagePrinter() {

		this.lineSeperator = SystemPropertyUtils.getOptionalSystemProperty("line.separator", "\n");
	}

	/**
	 * Erzeugt eine Instanz von MessagePrinter
	 */
	public MessagePrinter(final String lineSeperator) {

		super();
		this.lineSeperator = lineSeperator;
	}

	/**
	 * Bereitet den Inhalt einer Mail oder eines Log-Eintrags auf.
	 *
	 * @param  payloads
	 * @return          String
	 */
	public String print(final List<ResponsePayload> payloads) {

		StringBuffer sb = new StringBuffer();
		int count = 0;
		int max = payloads.size() - 2;

		for (ResponsePayload p : payloads) {

			sb.append(p.getMessage().getMessage());

			if (count <= max) {

				sb.append(lineSeperator);
			}
			count++;

		}
		return sb.toString();
	}
}
