package com.fabrick.bank.services;

import com.fabrick.bank.config.FabrickApiConfig;
import com.fabrick.bank.dtos.BaseResponse;
import com.fabrick.bank.dtos.MoneyTransferRequest;
import com.fabrick.bank.models.Balance;
import com.fabrick.bank.models.MoneyTransfer;
import com.fabrick.bank.models.Transaction;
import com.fabrick.bank.models.Transactions;
import com.fabrick.bank.repository.TransactionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FabrickServiceImpl implements FabrickService {
	private final String baseUrl;
	private final HttpHeaders           headers;
	private final TransactionRepository transactionRepository;
	private final ObjectMapper          objectMapper = new ObjectMapper();
	private final Logger log = LoggerFactory.getLogger(getClass());
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	public FabrickServiceImpl(FabrickApiConfig fabrickApiConfig, TransactionRepository transactionRepository) {
		this.baseUrl = fabrickApiConfig.getFabrickUrl();
		this.headers = fabrickApiConfig.fabrickApiHeaders();
		this.transactionRepository = transactionRepository;

	}

	private <T> BaseResponse<T> executeHttpRequest(HttpUriRequest request, TypeReference<BaseResponse<T>> responseType) {
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(request)) {
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				String responseBody = EntityUtils.toString(response.getEntity());
				return objectMapper.readValue(responseBody, responseType);
			} else {
				log.error("HTTP Request failed with status code: " + statusCode);
			}
		} catch (IOException e) {
			log.error("Error during HTTP request", e);
		}
		return null;
	}

	@Override
	public BaseResponse<Balance> getBalance(String accountId) {
		String balanceUrl = baseUrl + "/" + accountId + "/balance";
		HttpGet httpGet = new HttpGet(balanceUrl);
		addHeaders(httpGet);
		return executeHttpRequest(httpGet, new TypeReference<BaseResponse<Balance>>() {});
	}

	@Override
	public BaseResponse<Transactions> getTransactions(String accountId, Date from, Date to) {
		String transactionUrl = baseUrl + "/" + accountId + "/transactions";
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(transactionUrl)
				.queryParam("fromAccountingDate", dateFormat.format(from))
				.queryParam("toAccountingDate", dateFormat.format(to));

		HttpGet httpGet = new HttpGet(builder.toUriString());
		addHeaders(httpGet);
		BaseResponse<Transactions> transactionsBaseResponse = executeHttpRequest(httpGet, new TypeReference<BaseResponse<Transactions>>() {});
		if(transactionsBaseResponse != null && transactionsBaseResponse.getPayload() != null){
			Transactions payload = transactionsBaseResponse.getPayload();
			if(!CollectionUtils.isEmpty(payload.getList())){
				payload.getList().stream().forEach(t-> save(t));
			}
		}
		return transactionsBaseResponse;
	}

	@Override
	public BaseResponse<MoneyTransfer> createMoneyTransfer(String accountId, MoneyTransferRequest bonificoRequest) {
		String moneyTransferUrl = baseUrl + "/" + accountId + "/payments/money-transfers";
		HttpPost httpPost = new HttpPost(moneyTransferUrl);
		addHeaders(httpPost);
		try {
			String bonificoRequestJson = objectMapper.writeValueAsString(bonificoRequest);
			StringEntity requestEntity = new StringEntity(bonificoRequestJson);
			requestEntity.setContentType("application/json");
			httpPost.setEntity(requestEntity);
			return executeHttpRequest(httpPost, new TypeReference<BaseResponse<MoneyTransfer>>() {});
		}catch (IOException ex){
			log.error("Error while creating money transfer request", ex);
			return null;
		}
	}

	@Override public boolean save(Transaction transaction) {
		return transactionRepository.save(transaction) != null;
	}

	private void addHeaders(HttpRequestBase request) {
		headers.forEach((key, values) -> request.addHeader(key, values.get(0)));
	}
}
