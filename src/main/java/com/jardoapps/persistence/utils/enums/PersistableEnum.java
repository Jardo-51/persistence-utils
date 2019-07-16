package com.jardoapps.persistence.utils.enums;

/**
 * Interface for enums which are persisted in database.
 */
public interface PersistableEnum {

	/**
	 * Unique ID for each enum constant which is used to store the constant in
	 * database. Once an ID is assigned to a constant, it cannot be changed.
	 * 
	 * @return ID which has to be unique for each constant.
	 */
	String getId();

}
