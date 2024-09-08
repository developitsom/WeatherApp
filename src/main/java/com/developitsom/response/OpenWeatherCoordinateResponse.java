package com.developitsom.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherCoordinateResponse(@JsonProperty("name") String name,
		@JsonProperty("coord") Coordinates coordinates) {
}
