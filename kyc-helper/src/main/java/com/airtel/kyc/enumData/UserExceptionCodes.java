package com.airtel.kyc.enumData;

public enum UserExceptionCodes {
			
	/** The duplicate user name. */
	DUPLICATE_USER_NAME(201, "User Name already exist."),
			
	/** The user not exists. */
	USER_UNABLE_TO_REGISTERED(202, "Username is not registered."), 
	
	/** The user not exists. */
	USER_NOT_AVAILABLE(211, "User not available."), 
	
	/** The user or password not exists. */
	USER_OR_PASSWORD_NOT_EXISTS(203, "Invalid credential"),
	
	
	/** The user or password not exists. */
	OLD_AND_NEW_PASSWORD_CAN_NOT_SAME(207, "Old and new password must not be same."),
	
	/** The user or password not exists. */
	CURRENT_AND_PROVIDED_PASSWORD_NOT_MATCH(209, "Current password does not match with existing one."),
	
	/** The user or password not exists. */
	NEW_AND_CONFIRM_PASSWORD_NOT_SAME(208, "New and Confirm password must be same."),
	
	/** The inactive user. */
	INACTIVE_USER(204,"User account is inactive."),
	
	/** The active user. */
	ACTIVE_USER(205,"You are an active user."),
	
	/** The duplicate department code. */
	DUPLICATE_DEPARTMENT_CODE (206, "Department code already exist."), 
		
	/** The duplicate designation code. */
	DUPLICATE_DESIGNATION_CODE(206,"Designation code already exist."),
	
	/** The duplicate designation code. */
	UNABLE_TO_UPDATE_USER(211,"Unable to update user.") ,
	
	/** The duplicate designation code. */
	INVALID_MISISDN(212,"User Does Not Exist.") ,
	/** The duplicate designation code. */
	INVALID_ROLES(213,"Invalid roles.") ,
	
	SECURITY_CODE_EXPIRED(222,"Security Code Expired.") ,
	
	SECURITY_CODE_CANNOT_BE_GENERATED(227,"Security Code Cannot Be Generated Since Auuid Exist.") ,
	
	INVALID_DEPARTMENTS(213,"Invalid departments."),
	
	ROLE_NOT_VALID(214,"Role not present.") ,
	
	ZONE_NOT_VALID(215,"Zone not present.") ,
	
	DEPARTMENT_NOT_VALID(216,"Department not present.") ,

	NOT_ALLOWED_TO_CHANGE_PWD(217,"Not Allowed to change Password.") ,
	
	INVALID_REQUEST(218,"Invalid Request,Please Check All the provided parameters.") ,
	
	/** Maximum Invalid Attempt. */
	USER_PASSWORD_WRONG_ATTEMPT_EXCEEDED(221, "User or password wrong attempt exceeded");
	
	
	
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
	private UserExceptionCodes(int exceptionCode, String exceptionDescription) {
		this.exceptionCode = exceptionCode;
		this.exceptionDescription = exceptionDescription;
	}

	/**
	 * Gets the exception code.
	 *
	 * @return the exception code
	 */
	public Integer getExceptionCode() {
		return this.exceptionCode;
	}

	/**
	 * Gets the exception description.
	 *
	 * @return the exception description
	 */
	public String getExceptionDescription() {
		return exceptionDescription;
	}
}
