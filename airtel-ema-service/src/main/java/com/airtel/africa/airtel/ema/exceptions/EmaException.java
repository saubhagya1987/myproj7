package com.airtel.africa.airtel.ema.exceptions;

public class EmaException extends Exception {

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

	public EmaException() {
		super();
	}

	public EmaException(String message) {
		super(message);
	}

	public EmaException(EmaExceptionCodes exceptionCodes) {
		super(exceptionCodes.getExceptionDescription());
		this.setExceptionCode(exceptionCodes.getExceptionCode());
		this.setExceptionMessage(exceptionCodes.getExceptionDescription());
	}
	
	public EmaException(Integer errorCode, String errorMessage, Throwable t) {
		this.setExceptionCode(errorCode);
		this.setExceptionMessage(errorMessage);
		
		
	}

}
