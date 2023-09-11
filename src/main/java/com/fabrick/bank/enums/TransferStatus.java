package com.fabrick.bank.enums;

public enum TransferStatus {
	EXECUTED("The money transfer has been executed."),
	BOOKED("The money transfer has been booked for execution on execution date."),
	WORK_IN_PROGRESS("The money transfer execution is in progress."),
	CANCELLED("The money transfer has been cancelled by the customer (applies only to formerly booked money transfers)."),
	REJECTED("The money transfer has been rejected.");


	private String description;

	TransferStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
