package com.fabrick.bank.dtos;

import com.fabrick.bank.models.Client;
import com.fabrick.bank.models.TaxRelief;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoneyTransferRequest extends BaseRequest{
	private Client creditor;
	private String executionDate;
	private String    uri;
	private String    description;
	private double    amount;
	private String    currency;
	private boolean   isUrgent;
	private boolean   isInstant;
	private String   feeType;
	private String    feeAccountId;
	private TaxRelief taxRelief;
}
