package com.fabrick.bank.models;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;

@Converter
public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {

	@Override
	public String convertToDatabaseColumn(TransactionType attribute) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	@Override
	public TransactionType convertToEntityAttribute(String dbData) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(dbData, TransactionType.class);
		} catch (IOException e) {
			return null;
		}
	}
}
