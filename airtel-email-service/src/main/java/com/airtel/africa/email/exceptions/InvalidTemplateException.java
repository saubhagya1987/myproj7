package com.airtel.africa.email.exceptions;

public class InvalidTemplateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidTemplateException() {
		super("Unable to process template.");
	}
	
	public InvalidTemplateException(String msg) {
		super(msg);
	}

}
