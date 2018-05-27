package com.airtel.user.persistence.exception;


/**
 * This class handles exception of type DBException
 * @author Hem Chand
 * @created August 22, 2016
 */
public class UserDaoException extends DaoException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public UserDaoException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UserDaoException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param cause
	 * @param message
	 */
	public UserDaoException(Throwable cause, String message) {
		super(cause, message);
	}

	/**
	 * @param throwable
	 * @param message
	 * @param errorCode
	 */
	public UserDaoException(Throwable throwable, String message, Integer errorCode) {
		super(throwable, message, errorCode);
	}

	/**
	 * @param message
	 * @param errorCode
	 */
	public UserDaoException(String message, Integer errorCode) {
		super(message, errorCode);
	}

}