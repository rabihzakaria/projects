package com.fabrick.bank.services;

import com.fabrick.bank.dtos.*;
import com.fabrick.bank.models.Balance;
import com.fabrick.bank.models.MoneyTransfer;
import com.fabrick.bank.models.Transaction;
import com.fabrick.bank.models.Transactions;

import java.util.Date;

public interface FabrickService {
	public BaseResponse<Balance> getBalance(String accountId);

	public BaseResponse<Transactions> getTransactions(String accountId, Date from, Date to);

	public BaseResponse<MoneyTransfer> createMoneyTransfer(String accountId, MoneyTransferRequest bonificoRequest);

	public boolean save(Transaction transaction);
}
