package com.developitsom.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.developitsom.client.OpenWeatherApiClient;
import com.developitsom.response.OpenWeatherCoordinateResponse;

@Service
public class WeatherServiceImpl implements WeatherService {

	private static final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);
	
	private final OpenWeatherApiClient openWeatherApiClient;

	public WeatherServiceImpl(OpenWeatherApiClient openWeatherApiClient) {
		this.openWeatherApiClient = openWeatherApiClient;
	}

	@Override
	public OpenWeatherCoordinateResponse getCoordinatesForLocation(String location, int limit) {
		try {
			return openWeatherApiClient.getCoordinatesForLocation(location, limit);
		} catch (IOException | InterruptedException e) {
			log.error("An error occured: {}", e.getMessage());
			return null;
		}
	}

}
