package com.airtel.user.exception;

public class SubscriberNotFoundException extends UserBusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubscriberNotFoundException() {
		super();
	}
	
	public SubscriberNotFoundException(String message){
		super(message);
	}
	
}
