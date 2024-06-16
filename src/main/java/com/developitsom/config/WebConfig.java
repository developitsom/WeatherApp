package com.developitsom.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.developitsom.client.OpenWeatherApiClient;

@Configuration
public class WebConfig {

	@Bean
	public OpenWeatherApiClient openWeatherApiClient(@Value("${openweather.url}") String baseUrl,
			@Value("${openweather.apiKey}") String apiKey) {
		return new OpenWeatherApiClient(baseUrl, apiKey);
	}
}
