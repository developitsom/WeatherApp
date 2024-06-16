package com.developitsom.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.developitsom.response.OpenWeatherCoordinateResponse;
import com.developitsom.service.WeatherService;
import com.developitsom.validation.WeatherValidation;

@RestController
@RequestMapping(path = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {

	private WeatherService weatherService;

	@Autowired
	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@GetMapping()
	public OpenWeatherCoordinateResponse getCoordinatesForLocation(@RequestParam(name = "location", required = true) String location,
			@RequestParam(name = "limit", defaultValue = "5", required = false) int limit) {

		WeatherValidation.validate(location);
		return weatherService.getCoordinatesForLocation(location, limit);
	}

}
