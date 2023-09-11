package com.fabrick.bank.enums;

public enum FeeType {
	SHA("SHA"),
	OUR("OUR"),
	BEN("BEN");

	private final String value;

	FeeType(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
