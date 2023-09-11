package com.fabrick.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AmountInfo {
	private double debtorAmount;
	private String debtorCurrency;
	private double creditorAmount;
	private String creditorCurrency;
	private String creditorCurrencyDate;
	private double exchangeRate;
}
