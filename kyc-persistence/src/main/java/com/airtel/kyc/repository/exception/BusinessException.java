package com.airtel.kyc.repository.exception;

import com.airtel.kyc.enumData.ExceptionCodes;

/**
 * @author Airtel
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	private Integer exceptionCode;
	
	private String exceptionMessage;

	private Throwable cause;
		
	public BusinessException() {
		super();
	}
	
	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause.getMessage());
		this.cause = cause;
	}

	public BusinessException(Throwable cause, String message) {
		super(message);
		this.cause = cause;
	}

	public BusinessException(Integer errorCode,String message) {
		super(message);
		this.exceptionCode = errorCode;
	}

	protected BusinessException(Integer errorCode, String message,Throwable throwable) {
		this(throwable);
		this.exceptionCode = errorCode;
		this.exceptionMessage = message;
	}

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
	
	public BusinessException(ExceptionCodes exceptionCodes) {
		super(exceptionCodes.getExceptionDescription());
		this.setExceptionCode(exceptionCodes.getExceptionCode());
		this.setExceptionMessage(exceptionCodes.getExceptionDescription());
	}
	

}
