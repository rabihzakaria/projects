package com.fabrick.bank.config;

import com.fabrick.bank.utils.LogInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration public  class FabrickApiConfig {

	@Value("${fabrick.api.base-url}")
	private String baseUrl;

	@Value("${fabrick.api.auth-schema}")
	private String authSchema;

	@Value("${fabrick.api.api-key}")
	private String apiKey;

	@Value("${fabrick.api.path}")
	private String fabrickApiPath;

	@Bean
	public HttpRequestInterceptor loggingInterceptor() {
		return new LogInterceptor();
	}

	@Bean
	public CloseableHttpClient closeableHttpClient() {
		return HttpClients.custom()
				.addInterceptorFirst((HttpRequestInterceptor) new LogInterceptor())
				.addInterceptorLast((HttpResponseInterceptor) new LogInterceptor()) // Add your LogInterceptor
				.build();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
	

	@Bean
	public HttpHeaders fabrickApiHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Auth-Schema", authSchema);
		headers.set("Api-Key", apiKey);
		headers.set("X-Time-Zone", "Europe/Rome");
		return headers;
	}

	public String getFabrickUrl() {
		return baseUrl+fabrickApiPath;
	}
}

