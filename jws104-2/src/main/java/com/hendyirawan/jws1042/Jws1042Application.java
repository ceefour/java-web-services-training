package com.hendyirawan.jws1042;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class Jws1042Application {

	private static final Logger LOG = LoggerFactory.getLogger(Jws1042Application.class);

	public static void main(String[] args) throws IOException, URISyntaxException {
		ObjectMapper mapper = new ObjectMapper();
		try (CloseableHttpClient client =
					 HttpClientBuilder.create().useSystemProperties().build()) {
			URI uri = new URIBuilder("http://api.geonames.org/searchJSON")
					.addParameter("q", "kabupaten garut")
					.addParameter("username", "ceefour")
					.build();
			HttpGet getRequest = new HttpGet(uri);
			try (CloseableHttpResponse resp = client.execute(getRequest)) {
				String body = IOUtils.toString(resp.getEntity().getContent(),
						StandardCharsets.UTF_8);
				JsonNode bodyNode = mapper.readTree(body);
				LOG.info("Status: {}", resp.getStatusLine());
				LOG.info("Headers: {}", resp.getAllHeaders());
				LOG.info("Body: {}", body);
				LOG.info("Body (JsonNode): {}", bodyNode);
				for (JsonNode child : bodyNode.get("geonames")) {
					LOG.info("Place: {} ({}, {})", child.get("toponymName"), child.get("lat"), child.get("lng"));
				}
			}
		}
	}
}
