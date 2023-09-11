package com.fabrick.bank.services;

import com.fabrick.bank.config.FabrickApiConfig;
import com.fabrick.bank.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class FabrickServiceImpl implements  FabrickService{
	private final     String baseUrl;

	private final HttpHeaders  headers;
	private final RestTemplate restTemplate;

	@Autowired
	public FabrickServiceImpl(FabrickApiConfig fabrickApiConfig, RestTemplate restTemplate) {
		this.baseUrl = fabrickApiConfig.getFabrickUrl();
		this.headers = fabrickApiConfig.fabrickApiHeaders();
		this.restTemplate = restTemplate;
	}

		@Override
		public BalanceResponse getBalance(String accountId) {
			String saldoUrl = baseUrl + accountId + "/balance";
			/*ResponseEntity<BalanceResponse> response = restTemplate.exchange(
					saldoUrl,
					HttpMethod.GET,
					new HttpEntity<>(headers),
					BalanceResponse.class
			);
			return response.getBody();*/
			return null;
		}
	@Override
	public BalanceResponse getTransactions(String accountId, Date from, Date to) {
		String transactionUrl = baseUrl +  accountId + "/transactions";
		ResponseEntity<BalanceResponse> response = restTemplate.exchange(
				transactionUrl,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				 BalanceResponse.class
		);
		return response.getBody();
	}
	@Override
	public MoneyTransferResponse createMoneyTransfer(String accountId, MoneyTransferRequest bonificoRequest) {
		String bonificoUrl = baseUrl +  accountId + "/payments/money-transfers";
		ResponseEntity<MoneyTransferResponse> response = restTemplate.exchange(
				bonificoUrl,
				HttpMethod.POST,
				new HttpEntity<>(bonificoRequest, headers),
				MoneyTransferResponse.class
		);
		return response.getBody();
	}
}
