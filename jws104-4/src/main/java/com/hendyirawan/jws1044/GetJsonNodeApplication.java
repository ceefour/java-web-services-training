package com.hendyirawan.jws1044;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;

public class GetJsonNodeApplication {

	private static final Logger LOG = LoggerFactory.getLogger(GetJsonNodeApplication.class);

	public static void main(String[] args)
			throws IOException, URISyntaxException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://api.geonames.org/searchJSON")
				.queryParam("q", "kabupaten garut")
				.queryParam("username", "ceefour");
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		JsonNode bodyNode = invocationBuilder.get(JsonNode.class);
		LOG.info("Body (JsonNode): {}", bodyNode);
		for (JsonNode child : bodyNode.get("geonames")) {
			LOG.info("Place: {} ({}, {})",
					child.get("toponymName"), child.get("lat"), child.get("lng"));
		}
	}
}
