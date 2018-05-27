package com.airtel.africa.am.exceptions;

public class AitelMoneyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer exceptionCode;

	private String exceptionMessage;

	private Throwable cause;

	public Integer getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(Integer exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	public AitelMoneyException() {
		super();
	}

	public AitelMoneyException(String message) {
		super(message);
	}

	public AitelMoneyException(AitelMoneyExceptionCodes exceptionCodes) {
		super(exceptionCodes.getExceptionDescription());
		this.setExceptionCode(exceptionCodes.getExceptionCode());
		this.setExceptionMessage(exceptionCodes.getExceptionDescription());
	}

}
