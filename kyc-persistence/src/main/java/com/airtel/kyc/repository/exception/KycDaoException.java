package com.airtel.kyc.repository.exception;


public class KycDaoException extends DaoException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public KycDaoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public KycDaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param cause
	 * @param message
	 */
	public KycDaoException(Throwable cause, String message) {
		super(cause, message);
	}

	/**
	 * @param throwable
	 * @param message
	 * @param errorCode
	 */
	public KycDaoException(Throwable throwable, String message, String errorCode) {
		super(throwable, message, errorCode);
	}

	/**
	 * @param message
	 * @param errorCode
	 */
	public KycDaoException(String message, String errorCode) {
		super(message, errorCode);
	}

}