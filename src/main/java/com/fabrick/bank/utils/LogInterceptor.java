package com.fabrick.bank.utils;

import org.apache.http.HttpException;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogInterceptor implements HttpRequestInterceptor, HttpResponseInterceptor {
	private final Logger logger = LoggerFactory.getLogger(getClass());


	private void logRequest(org.apache.http.HttpRequest request) throws IOException {
		logger.info("=========================== Request ===========================");
		logger.info("URI         : {}", request.getRequestLine().getUri());
		logger.info("Method      : {}", request.getRequestLine().getMethod());
		logger.info("Headers     : {}", request.getAllHeaders());
		logger.info("===============================================================");
	}

	private void logResponse(HttpResponse response) throws IOException {
			logger.info("=========================== Response ===========================");
			logger.info("Status Code : {}", response.getStatusLine().getStatusCode());
			logger.info("Headers     : {}", response.getAllHeaders());

			/*// Read and log the response body without consuming the stream
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				entity.writeTo(buffer);
				String responseBody = buffer.toString(StandardCharsets.UTF_8.name());
				logger.info("Response Body: {}", responseBody);
			} else {
				logger.info("Response Body: Empty");
			}*/

			logger.info("===============================================================");
		}


	@Override public void process(org.apache.http.HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
		logRequest(httpRequest);
	}

	@Override public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
		logResponse(httpResponse);
	}
}
