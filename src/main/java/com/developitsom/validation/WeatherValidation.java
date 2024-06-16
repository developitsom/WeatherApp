package com.developitsom.validation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.developitsom.exception.WeatherException;

public class WeatherValidation {

	private static final Logger log = LoggerFactory.getLogger(WeatherValidation.class);

	public static void validate(String location) {

		if (StringUtils.isBlank(location)) {
			log.error("Location cannot be empty or null.");
			throw new WeatherException("Given location=[" + location + "] is empty or null.");
		}

	}
}
