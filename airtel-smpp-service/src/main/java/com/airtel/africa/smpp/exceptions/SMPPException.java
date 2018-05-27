package com.airtel.africa.smpp.exceptions;

public class SMPPException extends Exception {

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

	public SMPPException() {
		super();
	}

	public SMPPException(String message) {
		super(message);
	}

	public SMPPException(SMPPExceptionCodes exceptionCodes) {
		super(exceptionCodes.getExceptionDescription());
		this.setExceptionCode(exceptionCodes.getExceptionCode());
		this.setExceptionMessage(exceptionCodes.getExceptionDescription());
	}
	
	public SMPPException(Integer errorCode, String errorMessage, Throwable t) {
		this.setExceptionCode(errorCode);
		this.setExceptionMessage(errorMessage);
		
	}
}
