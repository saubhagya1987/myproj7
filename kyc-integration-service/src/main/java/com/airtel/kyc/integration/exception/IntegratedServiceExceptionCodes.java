/*package com.airtel.kyc.integration.exception;

public enum IntegratedServiceExceptionCodes {

	*//** MSISDN not provided. *//*
	MSISDN_NOT_PROVIDED(10001, "MSISDN not provided."),
	
	*//** MSISDN not provided. *//*
	NIN_NOT_PROVIDED(10001, "NIN not provided."),
	
	
	*//** MSISDN not provided. *//*
	PARAMETERS_NOT_PROVIDED(10002, "parameters not provided."),

	*//** MSISDN not provided. *//*
	SINGLE_VIEW_NOT_UPDATED(10003, "unable to update singleview."),
	
	*//** MSISDN not provided. *//*
	INVALID_SIM_OR_MSISDN_PROVIDED(10004, "Invalid MSISDN or sim number not provided."),

	*//** MSISDN not provided. *//*
	USER_NOT_ON_NIDA(10005, "User not available on nida"),
	
	*//** MSISDN not provided. *//*
	USER_FINGERPRINT_NOT_FOUND_ON_NIDA(10006, "No fingerprint image found for specified fingerprint code."),
	
	*//** MSISDN not provided. *//*
	USER_INVALID_FINGERPRINT_ON_NIDA(10007, "Invalid or incomplete input biometric parameter."),
	
	*//** MSISDN not provided. *//*
	SINGLE_VIEW_FINGERPRINT_ON_NIDA(10009, "Identification details are invalid"),
	
	
	*//** MSISDN & email not provided. *//*
	NOTIFY_MSISDN_EMAIL_NOT_PROVIDED(10010, "Notify : msisdn and email id not provided."),

	*//** MSISDN not provided. *//*
	AM_NOT_UPDATED(10011, "unable to update AM."),
	
	ISSUE_IN_EMA(10012, "unable to connect EMA."),
	

	*//** The duplicate designation code. *//*
	DUPLICATE_DESIGNATION_CODE(206, "Designation code already exist.");

	*//** The exception code. *//*
	private final Integer exceptionCode;

	*//** The exception description. *//*
	private final String exceptionDescription;

	*//**
	 * Instantiates a new exception codes.
	 *
	 * @param exceptionCode
	 *            the exception code
	 * @param exceptionDescription
	 *            the exception description
	 *//*
	private IntegratedServiceExceptionCodes(int exceptionCode, String exceptionDescription) {
		this.exceptionCode = exceptionCode;
		this.exceptionDescription = exceptionDescription;
	}

	*//**
	 * Gets the exception code.
	 *
	 * @return the exception code
	 *//*
	public Integer getExceptionCode() {
		return this.exceptionCode;
	}

	*//**
	 * Gets the exception description.
	 *
	 * @return the exception description
	 *//*
	public String getExceptionDescription() {
		return exceptionDescription;
	}
}
*/