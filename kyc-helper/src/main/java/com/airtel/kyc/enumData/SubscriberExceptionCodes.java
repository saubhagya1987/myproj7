package com.airtel.kyc.enumData;

/**
 * @author User
 *
 */
public enum SubscriberExceptionCodes {

	/** The duplicate Subscriber. */
	DUPLICATE_SUBSCRIBER(109, "Subcriber already exist."),
	
	/** The Subscriber not exists. */
	SUBSCRIBER_NOT_EXISTS(113, "Subscriber is not registered."), 
	
	/** The inactive Subscriber. */
	INACTIVE_SUBSCRIBER(120,"Subscriber is inactive."),
	
	/** The active user. */
	ACTIVE_SUBSCRIBER(121,"You are an active subscriber."),
	
	/** The duplicate department code. */
	DUPLICATE_DEPARTMENT_CODE (125, "Department code already exist."),
	
	SECURITY_CODE_EXPIRED(222,"Security Code Expired.") ,
	
	NOT_APPROVED_SUBSCRIBER(245,"Not Approved Subscriber") ,
	
	MAXIMUM_LIMIS_REACHED(267,"Maxium Limits Reached") ,
	
	ROLE_NAME_NOT_PASS(206,"Role Name not passed.") 
	;
	
	
	/** The exception code. */
	private final Integer exceptionCode;
	
	/** The exception description. */
	private final String exceptionDescription;

	/**
	 * Instantiates a new exception codes.
	 *
	 * @param exceptionCode the exception code
	 * @param exceptionDescription the exception description
	 */
	private SubscriberExceptionCodes(int exceptionCode, String exceptionDescription) {
		this.exceptionCode = exceptionCode;
		this.exceptionDescription = exceptionDescription;
	}

	
	public Integer getExceptionCode() {
		return exceptionCode;
	}

	public String getExceptionDescription() {
		return exceptionDescription;
	}
	
	
	
}
