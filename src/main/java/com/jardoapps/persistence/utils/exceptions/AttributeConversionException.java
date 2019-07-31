package com.jardoapps.persistence.utils.exceptions;

public class AttributeConversionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AttributeConversionException() {
		super();
	}

	public AttributeConversionException(String message) {
		super(message);
	}

	public AttributeConversionException(Throwable cause) {
		super(cause);
	}

	public AttributeConversionException(String message, Throwable cause) {
		super(message, cause);
	}

}
