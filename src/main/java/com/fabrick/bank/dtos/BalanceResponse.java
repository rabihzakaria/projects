package com.fabrick.bank.dtos;

import com.fabrick.bank.models.Balance;
import com.fabrick.bank.models.Error;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceResponse implements Serializable {

	private String      status;

	private List<String> error;

	private Balance     payload;
}
