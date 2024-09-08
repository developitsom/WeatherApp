package com.developitsom.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.developitsom.client.OpenWeatherApiClient;
import com.developitsom.response.OpenWeatherCoordinateResponse;
import com.developitsom.response.WeatherOverviewResponse;

@Service
public class WeatherServiceImpl implements WeatherService {

	private static final String WEATHER_DESCRIPTION_STRING = "Currently %s with latitude=%s & longitude=%s having a temperature of %.2f°F (%.2f°C) with a minimum of %.2f°F (%.2f°C) & maximum of %.2f°F (%.2f°C). It also does have a %s weather with %s.";

	private static final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

	private final OpenWeatherApiClient openWeatherApiClient;

	public WeatherServiceImpl(OpenWeatherApiClient openWeatherApiClient) {
		this.openWeatherApiClient = openWeatherApiClient;
	}

	@Override
	public String getWeatherByLocation(String location, int limit) {
		try {
			OpenWeatherCoordinateResponse coordinateResponseByLocation = openWeatherApiClient
					.getCoordinatesForLocation(location, limit);
			WeatherOverviewResponse weatherForecastResponse = openWeatherApiClient.getWeatherOverviewByLonLat(
					coordinateResponseByLocation.coordinates().longitude().toString(),
					coordinateResponseByLocation.coordinates().latitude().toString());

			double farhenheitTemp = convertKelvinToFahrenheit(
					weatherForecastResponse.weatherConditions().temperature());
			double farhenheitMinTemp = convertKelvinToFahrenheit(weatherForecastResponse.weatherConditions().minTemp());
			double farhenheitMaxTemp = convertKelvinToFahrenheit(weatherForecastResponse.weatherConditions().maxTemp());

			double celciusTemp = convertKelvinToCelcius(weatherForecastResponse.weatherConditions().temperature());
			double celciusMinTemp = convertKelvinToCelcius(weatherForecastResponse.weatherConditions().minTemp());
			double celciusMaxTemp = convertKelvinToCelcius(weatherForecastResponse.weatherConditions().maxTemp());

			return String.format(WEATHER_DESCRIPTION_STRING, weatherForecastResponse.locationName(),
					coordinateResponseByLocation.coordinates().latitude().toString(),
					coordinateResponseByLocation.coordinates().longitude().toString(), farhenheitTemp, celciusTemp,
					farhenheitMinTemp, celciusMinTemp, farhenheitMaxTemp, celciusMaxTemp,
					weatherForecastResponse.weather().get(0).weather(),
					weatherForecastResponse.weather().get(0).weatherDescription());
		} catch (IOException | InterruptedException e) {
			log.error("An error occured: {}", e.getMessage());
			return null;
		}
	}

	private double convertKelvinToCelcius(String temperature) {
		return (Double.parseDouble(temperature) - 273.15);
	}

	private double convertKelvinToFahrenheit(String temperature) {
		return (Double.parseDouble(temperature) - 273.15) * 9 / 5 + 32;
	}

}
