package com.fabrick.bank.models;

import com.fabrick.bank.models.AmountInfo;
import com.fabrick.bank.models.Client;
import com.fabrick.bank.models.Fee;
import com.fabrick.bank.utils.FabrickUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoneyTransfer {
	private String    moneyTransferId;
	private String    status;
	private String    direction;
	private Client    creditor;
	private Client    debtor;
	private String    cro;
	private String    trn;
	private String    uri;
	private String    description;
	private String createdDatetime;
	private String accountedDatetime;
	private String debtorValueDate;
	private String     creditorValueDate;
	private AmountInfo amount;
	private boolean    isUrgent;
	private boolean isInstant;
	private String feeType;
	private String    feeAccountId;
	private List<Fee> fees;
	private Boolean   hasTaxRelief;

	public void setFeeType(String feeType) {
		if (!FabrickUtils.isValidFeeType(feeType)) {
			throw new IllegalArgumentException("Invalid feeType value: " + feeType);
		}
		this.feeType = feeType;
	}

	public void setStatus(String transferStatus){
		if (!FabrickUtils.isValidTransferStatus(transferStatus)) {
			throw new IllegalArgumentException("Invalid feeType value: " + feeType);
		}
		this.status = transferStatus;
	}


}
