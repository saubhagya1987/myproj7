package com.airtel.africa.airtel.money.exceptions;

public enum AitelMoneyExceptionCodes {
			
	/** The duplicate user name. */
	ENDPOINT_NOT_CONNECTED(2101, "Unable to connect endpoint."),
	
	/** The duplicate user name. */
	UNABLE_TO_FETCH_RESPONSE(2102, "Unable to get response from airtel money response."),
			
	/** The duplicate user name. */
	INVALID_MSISDN(2103, "Invalid MSISDN on airtel money"),
	
	/** The duplicate user name. */
	INVALID_SIM_NUMBER(2104, "Invalid sim number on airtel money"),
	
	/** The duplicate user name. */
	INVALID_ENDPOINT(2105, "Invalid endpoint of  airtel money"),
	
	/** The user not exists. */
	USER_NOT_EXISTS(2106, "Username is not registered on airtel money."), 
	
	/** The user or password not exists. */
	USER_OR_PASSWORD_NOT_EXISTS(2107, "Username or password does not match on airtel money.");
	
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
	private AitelMoneyExceptionCodes(int exceptionCode, String exceptionDescription) {
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
