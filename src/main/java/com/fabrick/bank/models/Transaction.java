package com.fabrick.bank.models;

import com.fabrick.bank.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
	private String          transactionId;
	private String          operationId;
	private Date            accountingDate;
	private Date            valueDate;
	private TransactionType type;
	private double          amount;
	private String          currency;
	private String          description;
}
