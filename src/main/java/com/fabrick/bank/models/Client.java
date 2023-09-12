package com.fabrick.bank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {
	private String name;
	private Account account;
	private Address address;
}
