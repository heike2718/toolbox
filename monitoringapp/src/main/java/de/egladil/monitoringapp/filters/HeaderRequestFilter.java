// =====================================================
// Project: monitoringapp
// (c) Heike Winkelvoß
// =====================================================
package de.egladil.monitoringapp.filters;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

/**
 * HeaderRequestFilter
 */
public class HeaderRequestFilter implements ClientRequestFilter {

	private static final String USER_AGENT_HEADER_VALUE = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36";

	private static final String USER_AGENT_HEADER_NAME = "User-Agent";

	@Override
	public void filter(final ClientRequestContext requestContext) throws IOException {

		requestContext.getHeaders().add(USER_AGENT_HEADER_NAME,
			USER_AGENT_HEADER_VALUE);
	}

}
