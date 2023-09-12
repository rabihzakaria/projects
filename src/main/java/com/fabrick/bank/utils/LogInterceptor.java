package com.fabrick.bank.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LogInterceptor implements ClientHttpRequestInterceptor {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		logRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		logResponse(response);
		return response;
	}

	private void logRequest(HttpRequest request, byte[] body) throws IOException {
		logger.info("=========================== Request ===========================");
		logger.info("URI         : {}", request.getURI());
		logger.info("Method      : {}", request.getMethod());
		logger.info("Headers     : {}", request.getHeaders());
		logger.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
		logger.info("===============================================================");
	}

	private void logResponse(ClientHttpResponse response) throws IOException {
		logger.info("=========================== Response ===========================");
		logger.info("Status code  : {}", response.getStatusCode());
		logger.info("Headers      : {}", response.getHeaders());
		String responseBody = new String(StreamUtils.copyToByteArray(response.getBody()));
		logger.info("Response body: {}", responseBody);
		logger.info("===============================================================");
	}
}
