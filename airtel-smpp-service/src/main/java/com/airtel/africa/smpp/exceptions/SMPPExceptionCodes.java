package com.airtel.africa.smpp.exceptions;

public enum SMPPExceptionCodes {
			
	/** The duplicate user name. */
	ENDPOINT_NOT_CONNECTED(7000, "Unable to connect endpoint."),
	
	
	/** The duplicate user name. */
	INVALID_MSISDN(7001, "Invalid MSISDN"),
	
	/** The duplicate user name. */
	INVALID_SIM_NUMBER(7002, "Invalid sim number."),
	
	/** The duplicate user name. */
	INVALID_ENDPOINT(7003, "Invalid Endpoint"),
	
	
	/** The duplicate user name. */
	EXCEPTION_IN_RESPONSE(7004, "Unable to get response."),
		
	FETCH_ERROR(7005,"IAT Exception: Error in fetching HLRSUB ")
	,
	
	SESSION_NOT_CREATED(7006,"Unable to crearte an session.")
	;
	
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
	private SMPPExceptionCodes(int exceptionCode, String exceptionDescription) {
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
