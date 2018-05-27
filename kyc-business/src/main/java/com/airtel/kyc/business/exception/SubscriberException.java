package com.airtel.kyc.business.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.airtel.kyc.enumData.*;
import com.airtel.kyc.repository.exception.BusinessException;;


@ControllerAdvice
public class SubscriberException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SubscriberException() {
		super();
	}
		
	public SubscriberException(Integer errorCode,String message,Throwable throwable){
		super(errorCode,message,throwable);
	}
		
	public SubscriberException(SubscriberExceptionCodes exceptionCodes) {
		super(exceptionCodes.getExceptionDescription());
		this.setExceptionCode(exceptionCodes.getExceptionCode());
		this.setExceptionMessage(exceptionCodes.getExceptionDescription());
	}
}
