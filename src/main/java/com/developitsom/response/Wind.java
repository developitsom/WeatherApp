package com.developitsom.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Wind(@JsonProperty("speed") String windSpeed, @JsonProperty("deg") String windDegree) {

}
