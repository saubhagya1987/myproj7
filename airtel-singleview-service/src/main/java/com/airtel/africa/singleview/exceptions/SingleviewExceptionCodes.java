package com.airtel.africa.singleview.exceptions;

public enum SingleviewExceptionCodes {
			
	/** The duplicate user name. */
	ENDPOINT_NOT_CONNECTED(4000, "Unable to connect endpoint."),
	
	
	/** The duplicate user name. */
	INVALID_MSISDN(4001, "Invalid MSISDN"),
	
	/** The duplicate user name. */
	INVALID_SIM_NUMBER(4002, "Invalid sim number."),
	
	/** The duplicate user name. */
	INVALID_ENDPOINT(4003, "Invalid Endpoint"),
			
	/** The duplicate user name. */
	UPDATE_DEMOGRAPHIC_DETAIL_FAIL(4004, "Unable to update user detail."),
	
	
	/** The user not exists. */
	USER_NOT_EXISTS(202, "Username is not registered."), 
	
	/** The user or password not exists. */
	USER_OR_PASSWORD_NOT_EXISTS(203, "Username or password does not match."),
	
	/** The inactive user. */
	INACTIVE_USER(204,"User account is inactive."),
	
	/** The active user. */
	ACTIVE_USER(205,"You are an active user."),
	
	/** The duplicate department code. */
	DUPLICATE_DEPARTMENT_CODE (206, "Department code already exist."), 
		
	/** The duplicate designation code. */
	DUPLICATE_DESIGNATION_CODE(206,"Designation code already exist."), 
	
	/** ID TYPE can not null. */
	ID_TYPE_NOT_NILLABLE(9001,"ID type can not be null on singleview demographic update.") ;
	
	/** The exception code. */
	private final Integer exceptionCode;
	
	/** The exception description. */
	private final String exceptionDescription;

	/**
	 * Instantiates a new exception codes.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionDescription the exception description
	 */
	private SingleviewExceptionCodes(int exceptionCode, String exceptionDescription) {
		this.exceptionCode = exceptionCode;
		this.exceptionDescription = exceptionDescription;
	}

	/**
	 * Gets the exception code.
	 *
	 * @return the exception code
	 */
	public Integer getExceptionCode() {
		return this.exceptionCode;
	}

	/**
	 * Gets the exception description.
	 *
	 * @return the exception description
	 */
	public String getExceptionDescription() {
		return exceptionDescription;
	}
}
