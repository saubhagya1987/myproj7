package com.airtel.kyc.enumData;

public enum ExceptionCodes {
	
	/** The system exception. */
	SYSTEM_EXCEPTION(104,"System exception occurred."),
	
	/** The business exception. */
	BUSINESS_EXCEPTION(101,"Business exception occurred."),
					
	/** The invalid request object. */
	INVALID_REQUEST_OBJECT(102, "Request object is invalid"),
	
	/** The business exception. */
	DATA_NOT_FOUND_EXCEPTION(106,"Business exception occurred.")
	
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
	private ExceptionCodes(int exceptionCode, String exceptionDescription) {
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
