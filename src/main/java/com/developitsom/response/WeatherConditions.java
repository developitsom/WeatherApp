package com.developitsom.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherConditions(@JsonProperty("temp") String temperature,
		@JsonProperty("feels_like") String temperatureFeelsLike, @JsonProperty("temp_min") String minTemp,
		@JsonProperty("temp_max") String maxTemp) {

}
