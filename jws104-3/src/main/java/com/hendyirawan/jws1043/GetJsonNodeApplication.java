package com.hendyirawan.jws1043;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;

public class GetJsonNodeApplication {

	private static final Logger LOG = LoggerFactory.getLogger(GetJsonNodeApplication.class);

	public static void main(String[] args)
			throws IOException, URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		JsonNode bodyNode = restTemplate.getForObject(
				"http://api.geonames.org/searchJSON?q={q}&username={username}",
			JsonNode.class,
				ImmutableMap.of("q", "kabupaten garut", "username", "ceefour"));
		LOG.info("Body (JsonNode): {}", bodyNode);
		for (JsonNode child : bodyNode.get("geonames")) {
			LOG.info("Place: {} ({}, {})",
					child.get("toponymName"), child.get("lat"), child.get("lng"));
		}
	}
}
