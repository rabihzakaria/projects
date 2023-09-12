package com.fabrick.bank.controllers;

import com.fabrick.bank.dtos.BaseResponse;
import com.fabrick.bank.dtos.MoneyTransferRequest;
import com.fabrick.bank.models.Balance;
import com.fabrick.bank.models.MoneyTransfer;
import com.fabrick.bank.models.Transactions;
import com.fabrick.bank.services.FabrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/account")
public class AccountController {


	private              FabrickService fabrickService;

	@Autowired
	public AccountController(FabrickService fabrickService) {
		this.fabrickService = fabrickService;
	}

	@GetMapping(value = "/balance/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse<Balance>> getBalance(@PathVariable("accountNumber")String accountNumber) {
		BaseResponse<Balance> balance = fabrickService.getBalance(accountNumber);
		if (balance != null) {
			return ResponseEntity.ok(balance);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	@GetMapping(value = "/transactions/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse<Transactions>> getTransactions(@PathVariable("accountNumber")String accountNumber,   @RequestParam("fromAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
			@RequestParam("toAccountingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to
	) {
		BaseResponse<Transactions> transactions = fabrickService.getTransactions(accountNumber, from, to);
		if (transactions != null) {
			return ResponseEntity.ok(transactions);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping(value = "/moneyTransfer/{accountNumber}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse<MoneyTransfer>> makeMoneyTransfer(@PathVariable("accountNumber")String accountNumber,  @RequestBody MoneyTransferRequest moneyTransferRequest) {
		BaseResponse<MoneyTransfer> response = fabrickService.createMoneyTransfer(accountNumber, moneyTransferRequest);
		if (response != null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}



