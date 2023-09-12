package com.fabrick.bank.errors;

import com.fabrick.bank.models.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@Autowired
	private                HttpServletRequest request;
	private   static final Logger             LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
		logRequestInfo(request);
		HttpStatus statusCode = (HttpStatus) ex.getStatusCode();
		String responseBody = ex.getResponseBodyAsString();
		Error error = new Error(statusCode, responseBody);
		return new ResponseEntity<>(error, statusCode);
	}

	private void logRequestInfo(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		String httpMethod = request.getMethod();
		Enumeration<String> headerNames = request.getHeaderNames();
		LOGGER.info("Received a request to {} with HTTP method {}", requestURL, httpMethod);

		// Log request headers
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			LOGGER.info("Header: {} = {}", headerName, headerValue);
		}
	}
}
