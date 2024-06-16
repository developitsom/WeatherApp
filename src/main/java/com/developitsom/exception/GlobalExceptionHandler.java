package com.developitsom.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public void handleException(Exception e) {
		throw new RuntimeException("An error occured: " + e.getMessage());
	}
}
