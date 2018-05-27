package com.airtel.kyc.enumData;

public enum ResponseCodes {

	/** The success response code. */
	SUCCESS_RESPONSE_CODE(1001, "Success"),
	
	/** The failure response code. */
	FAILURE_RESPONSE_CODE(1000, "Failure"),
	
	/** The failure response code. */
	FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_EXIST(4001, "Subscriber already registered with given MSISDN. "),

	/** The failure response code. */
	FAILURE_RESPONSE_CODE_NOT_FOUND_ON_NIDA(4002, "Subscriber not found on NIDA"),
	
	/** The failure response code. */
	FAILURE_RESPONSE_CODE_SUBSCRIBER_NOT_FOUND(4003, "Subscriber not available."),

	/** The failure response code. */
	FAILURE_RESPONSE_CODE_SUBSCRIBER_ALREADY_IN_PROGRESS(4004, "Subscriber already in progress."),

	/** The failure response code. */
	FAILURE_RESPONSE_CODE_SIM_DETAIL_INVALID(4005, "SIM details are invalid."),

	/** The failure response code. */
	FAILURE_RESPONSE_CODE_APP_DWOENLOAD(4006, "We have a newer and improved version for you to download at "),
	
	FAILURE_RESPONSE_CODE_APP_DWOENLOAD_URL(4007, "Please download new app from "),
	
	FAILURE_RESPONSE_CODE_UPLOAD_FILE_EMPTY(4008, "Template file is empty."),
	FAILURE_RESPONSE_CODE_SUBSCRIBER_HAS_ALREADY_TAKEN_MORE_SIM(4009, "Subscriber has already taken more Sim ")
	;
	/** The response code. */
	private Integer responseCode;

	/** The response description. */
	private String responseDescription;

	/**
	 * Instantiates a new response codes.
	 *
	 * @param responseCode
	 *            the response code
	 * @param responseDescription
	 *            the response description
	 */
	ResponseCodes(Integer responseCode, String responseDescription) {
		this.setResponseCode(responseCode);
		this.setResponseDescription(responseDescription);

	}

	/**
	 * Gets the response code.
	 *
	 * @return the response code
	 */
	public Integer getResponseCode() {
		return responseCode;
	}

	/**
	 * Sets the response code.
	 *
	 * @param responseCode
	 *            the new response code
	 */
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Gets the response description.
	 *
	 * @return the response description
	 */
	public String getResponseDescription() {
		return responseDescription;
	}

	/**
	 * Sets the response description.
	 *
	 * @param responseDescription
	 *            the new response description
	 */
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
}
