package com.airtel.africa.email.exceptions;

public class EmailTemplateNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmailTemplateNotFoundException() {
		super("Email template not found.");
	}
	
	public EmailTemplateNotFoundException(String msg) {
		super(msg);
	}

}
