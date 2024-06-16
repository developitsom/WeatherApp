package com.developitsom.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherCoordinateResponse(@JsonProperty("name") String name,
		@JsonProperty("local_names") Map<String, String> localNames, @JsonProperty("lat") Double latitude,
		@JsonProperty("long") Double longitude, @JsonProperty("country") String country, @JsonProperty("state") String state) {
}
