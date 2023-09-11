package com.fabrick.bank.utils;

import com.fabrick.bank.enums.FeeType;
import com.fabrick.bank.enums.TransferStatus;
import io.micrometer.common.util.StringUtils;

import java.util.Arrays;

public class FabrickUtils {

	public static boolean isValidFeeType(String value) {
		return StringUtils.isNotEmpty(value) && Arrays.stream(FeeType.values()).anyMatch(f -> f.getValue().equalsIgnoreCase(value));
	}

	public static boolean isValidTransferStatus(String status){
		return StringUtils.isNotEmpty(status) && Arrays.stream(TransferStatus.values()).anyMatch(s -> s.name().equalsIgnoreCase(status));
	}

}
