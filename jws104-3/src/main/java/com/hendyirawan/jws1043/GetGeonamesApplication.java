package com.hendyirawan.jws1043;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;

public class GetGeonamesApplication {

	private static final Logger LOG = LoggerFactory.getLogger(GetGeonamesApplication.class);

	public static void main(String[] args)
			throws IOException, URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		GeonamesSearchResult searchResult = restTemplate.getForObject(
				"http://api.geonames.org/searchJSON?q={q}&username={username}",
			GeonamesSearchResult.class,
				ImmutableMap.of("q", "kabupaten garut", "username", "ceefour"));
		LOG.info("Body (GeonamesSearchResult): {}", searchResult);
		for (Geoname child : searchResult.getGeonames()) {
			LOG.info("Place: {} ({}, {})",
					child.getToponymName(), child.getLat(), child.getLng());
		}
	}
}
