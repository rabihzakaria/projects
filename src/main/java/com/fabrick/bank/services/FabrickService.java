package com.fabrick.bank.services;

import com.fabrick.bank.dtos.*;

import java.util.Date;

public interface FabrickService {
	public BalanceResponse getBalance(String accountId);

	public BalanceResponse getTransactions(String accountId, Date from, Date to);

	public MoneyTransferResponse createMoneyTransfer(String accountId, MoneyTransferRequest bonificoRequest);
}
