package com.developitsom.service;

import com.developitsom.response.OpenWeatherCoordinateResponse;

public interface WeatherService {

	public OpenWeatherCoordinateResponse getCoordinatesForLocation(String location, int limit);
}
