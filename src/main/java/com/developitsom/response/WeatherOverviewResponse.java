package com.developitsom.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherOverviewResponse(@JsonProperty("weather") List<Weather> weather,
		@JsonProperty("main") WeatherConditions weatherConditions, @JsonProperty("wind") Wind wind,
		@JsonProperty("name") String locationName) {
}
