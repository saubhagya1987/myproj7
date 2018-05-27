package com.airtel.user.exception;

public class APINotFoundInDbException extends UserBusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public APINotFoundInDbException() {
		super();
	}
	
	public APINotFoundInDbException(String message){
		super(message);
	}
	
}
