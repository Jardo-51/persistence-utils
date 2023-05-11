package com.jardoapps.persistence.utils.converters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jardoapps.persistence.utils.exceptions.AttributeConversionException;

import jakarta.persistence.AttributeConverter;

/**
 * This converter may be used to store collections (Lists, Maps) as JSON
 * columns. The collections should contain simple values (numbers, Strings).
 */
public class JsonAttributeConverter implements AttributeConverter<Object, String> {

	private static final ObjectMapper om = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Object attribute) {

		if (attribute == null) {
			return null;
		}

		try {
			return om.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			throw new AttributeConversionException("Failed to convert object to JSON: '" + attribute + "'", e);
		}
	}

	@Override
	public Object convertToEntityAttribute(String dbData) {

		if (dbData == null) {
			return null;
		}

		try {
			return om.readValue(dbData, Object.class);
		} catch (IOException e) {
			throw new AttributeConversionException("Failed to convert JSON to object: '" + dbData + "'", e);
		}
	}

}
