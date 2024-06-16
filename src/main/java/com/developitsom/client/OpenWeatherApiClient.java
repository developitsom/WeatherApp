package com.developitsom.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import com.developitsom.response.OpenWeatherCoordinateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenWeatherApiClient {

	private static final Logger log = LoggerFactory.getLogger(OpenWeatherApiClient.class);
	private final String baseUrl;
	private final String apiKey;
	private final HttpClient httpClient;
	private final ObjectMapper objectMapper;

	public OpenWeatherApiClient(String baseUrl, String apiKey) {
		this.baseUrl = baseUrl;
		this.apiKey = apiKey;
		this.httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NEVER).build();
		this.objectMapper = new ObjectMapper();
	}

	public OpenWeatherCoordinateResponse getCoordinatesForLocation(String location, int limit)
			throws IOException, InterruptedException {

		URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).path("?q={location}&appid={apiKey}&limit={limit}")
				.buildAndExpand(location, apiKey, limit).toUri();

		HttpRequest httpRequest = HttpRequest.newBuilder().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.uri(uri).timeout(Duration.of(30, ChronoUnit.SECONDS)).GET().build();

		log.info("API request : {}", httpRequest);

		HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		log.info("API response code: {}", httpResponse.statusCode());
		log.info("API Response: {}", httpResponse.body());

		return objectMapper.readValue(httpResponse.body(), OpenWeatherCoordinateResponse.class);
	}

}
