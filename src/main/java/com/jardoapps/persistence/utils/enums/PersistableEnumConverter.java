package com.jardoapps.persistence.utils.enums;

import java.lang.reflect.ParameterizedType;

import javax.persistence.AttributeConverter;

public class PersistableEnumConverter<E extends Enum<E> & PersistableEnum> implements AttributeConverter<E, String> {

	private final Class<E> enumClass;
	private final E[] enumConstants;

	@SuppressWarnings("unchecked")
	public PersistableEnumConverter() {
		this.enumClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.enumConstants = enumClass.getEnumConstants();
	}

	@Override
	public String convertToDatabaseColumn(E attribute) {

		if (attribute == null) {
			return null;
		} else {
			return attribute.getId();
		}
	}

	@Override
	public E convertToEntityAttribute(String dbData) {

		if (dbData == null) {
			return null;
		}

		for (E value : enumConstants) {
			if (value.getId().equals(dbData)) {
				return value;
			}
		}
		throw new IllegalArgumentException("Unknown id '" + dbData + "' for enum '" + enumClass.getName() + "'.");
	}

}
