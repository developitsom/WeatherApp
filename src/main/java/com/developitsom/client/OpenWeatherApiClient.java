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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.developitsom.response.OpenWeatherCoordinateResponse;
import com.developitsom.response.WeatherOverviewResponse;
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

		log.info("Inside getCoordinatesForLocation API Client having apiKey=[{}], location=[{}]", apiKey, location);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("q", location);
		params.add("appid", apiKey);
		params.add("limit", String.valueOf(limit));

		URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParams(params).build().toUri();

		HttpRequest httpRequest = HttpRequest.newBuilder().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.uri(uri).timeout(Duration.of(30, ChronoUnit.SECONDS)).GET().build();

		log.info("API request : {}", httpRequest);

		HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		log.info("API response code: {}", httpResponse.statusCode());
		log.info("API Response: {}", httpResponse.body());

		return objectMapper.readValue(httpResponse.body(), OpenWeatherCoordinateResponse.class);
	}

	public WeatherOverviewResponse getWeatherOverviewByLonLat(String longitude, String latitude)
			throws IOException, InterruptedException {

		log.info("Inside getWeatherOverviewByLonLat API Client having apiKey=[{}], longitude=[{}], latitude=[{}]", apiKey, longitude,
				latitude);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("lon", longitude);
		params.add("lat", latitude);
		params.add("appid", apiKey);

		URI uri = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParams(params).build().toUri();

		HttpRequest httpRequest = HttpRequest.newBuilder().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				.uri(uri).timeout(Duration.of(30, ChronoUnit.SECONDS)).GET().build();

		log.info("API request : {}", httpRequest);

		HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		log.info("API response code: {}", httpResponse.statusCode());
		log.info("API Response: {}", httpResponse.body());

		return objectMapper.readValue(httpResponse.body(), WeatherOverviewResponse.class);
	}

}
