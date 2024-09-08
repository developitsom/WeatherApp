package com.developitsom.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Weather(@JsonProperty("main") String weather, @JsonProperty("description") String weatherDescription) {

}
