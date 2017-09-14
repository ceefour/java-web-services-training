package com.hendyirawan.jws1044;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;

public class GetGeonamesApplication {

	private static final Logger LOG = LoggerFactory.getLogger(GetGeonamesApplication.class);

	public static void main(String[] args)
			throws IOException, URISyntaxException {
		Client client = ClientBuilder.newClient();
		// GET http://api.geonames.org/searchJSON?...
		WebTarget target = client.target("http://api.geonames.org/searchJSON")
				.queryParam("q", "kabupaten garut")
				.queryParam("username", "ceefour");
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		GeonamesSearchResult searchResult = invocationBuilder.get(GeonamesSearchResult.class);
		LOG.info("Body (GeonamesSearchResult): {}", searchResult);
		for (Geoname child : searchResult.getGeonames()) {
			LOG.info("Place: {} ({}, {})",
					child.getToponymName(), child.getLat(), child.getLng());
		}
	}
}
