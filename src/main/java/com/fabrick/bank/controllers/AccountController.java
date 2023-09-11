package com.fabrick.bank.controllers;

import com.fabrick.bank.dtos.BalanceResponse;
import com.fabrick.bank.dtos.TransactionsResponse;
import com.fabrick.bank.services.FabrickService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity getBalance(@PathVariable("accountNumber")String accountNumber) {
			BalanceResponse balance = fabrickService.getBalance(accountNumber);
			return new ResponseEntity<>(balance.getPayload(), HttpStatus.OK);
	}


	public ResponseEntity<TransactionsResponse> getTransactions(String accountNumber, Date from, Date to){

		return null;
	}

	@PostMapping("/moneyTransfer")
	public ResponseEntity<String> effettuaBonifico() {
		return null;
	}
}



