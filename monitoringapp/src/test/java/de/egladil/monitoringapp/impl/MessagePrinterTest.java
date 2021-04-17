// =====================================================
// Projekt: monitoringapp
// (c) Heike Winkelvoß
// =====================================================

package de.egladil.monitoringapp.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import de.egladil.web.commons_validation.payload.MessagePayload;
import de.egladil.web.commons_validation.payload.ResponsePayload;

/**
 * MessagePrinterTest
 */
public class MessagePrinterTest {

	@Test
	void printZero() {

		// Arrange
		List<ResponsePayload> payloads = createPayloads(0);

		// Act
		String result = new MessagePrinter().print(payloads);
		System.out.println(result);
		System.out.println("=============");

		// Assert
		assertTrue(StringUtils.isBlank(result));

	}

	@Test
	void printOne() {

		// Arrange
		List<ResponsePayload> payloads = createPayloads(1);

		// Act
		String result = new MessagePrinter().print(payloads);
		System.out.println(result);
		System.out.println("=============");

		// Assert
		assertFalse(StringUtils.isBlank(result));

	}

	@Test
	void printTwo() {

		// Arrange
		List<ResponsePayload> payloads = createPayloads(2);

		// Act
		String result = new MessagePrinter().print(payloads);
		System.out.println(result);
		System.out.println("=============");

		// Assert
		assertFalse(StringUtils.isBlank(result));

	}

	private List<ResponsePayload> createPayloads(final int number) {

		List<ResponsePayload> result = new ArrayList<>();

		for (int i = 0; i < number; i++) {

			result.add(ResponsePayload.messageOnly(MessagePayload.info(UUID.randomUUID().toString())));
		}
		return result;
	}

}
