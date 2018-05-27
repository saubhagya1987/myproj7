package com.airtel.africa.airtel.ema.exceptions;

public enum EmaExceptionCodes {
			
	/** The duplicate user name. */
	ENDPOINT_NOT_CONNECTED(4000, "Unable to connect endpoint."),
	
	
	/** The duplicate user name. */
	INVALID_MSISDN(4001, "Invalid MSISDN"),
	
	/** The duplicate user name. */
	INVALID_SIM_NUMBER(4002, "Invalid sim number."),
	
	/** The duplicate user name. */
	INVALID_ENDPOINT(4003, "Invalid Endpoint"),
		
	FETCH_ERROR(4004,"IAT Exception: Error in fetching HLRSUB ")
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
	private EmaExceptionCodes(int exceptionCode, String exceptionDescription) {
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
