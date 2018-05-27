package com.airtel.kyc.business.exception;

import com.airtel.kyc.enumData.UserExceptionCodes;
import com.airtel.kyc.repository.exception.BusinessException;

public class UserException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public UserException() {
		super();
	}
		
	public UserException(Integer errorCode,String message,Throwable throwable){
		super(errorCode,message,throwable);
	}
		
	public UserException(UserExceptionCodes exceptionCodes) {
		super(exceptionCodes.getExceptionDescription());
		this.setExceptionCode(exceptionCodes.getExceptionCode());
		this.setExceptionMessage(exceptionCodes.getExceptionDescription());
	}
	
}
