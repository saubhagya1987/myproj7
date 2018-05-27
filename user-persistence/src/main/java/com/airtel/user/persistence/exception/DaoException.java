package com.airtel.user.persistence.exception;


/**
 * This class handles exception of type DLFException
 * @author Hem Chand
 * @created August 22, 2016
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	private int exceptionType;
	private Integer errorCode;
	private Throwable cause;

	public int getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(int exceptionType) {
		this.exceptionType = exceptionType;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	/**
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DaoException(Throwable cause) {
		super(cause.getMessage());
		this.cause = cause;
	}

	/**
	 * @param cause
	 * @param message
	 */
	public DaoException(Throwable cause, String message) {
		super(message);
		this.cause = cause;
	}

	/**
	 * @param message
	 * @param exceptionType
	 */
	protected DaoException(String message, int exceptionType) {
		super(message);
		this.exceptionType = exceptionType;
	}

	/**
	 * @param throwable
	 * @param exceptionType
	 */
	protected DaoException(Throwable throwable, int exceptionType) {
		this(throwable);
		this.exceptionType = exceptionType;
	}

	/**
	 * @param throwable
	 * @param message
	 * @param errorCode
	 */
	protected DaoException(Throwable throwable, String message, Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @param message
	 * @param errorCode
	 */
	protected DaoException(String message, Integer errorCode) {
		this(message);
		this.errorCode = errorCode;
	}

}