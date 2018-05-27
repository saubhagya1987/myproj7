package com.airtel.kyc_controller;

import java.sql.SQLException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SystemException;
import com.airtel.kyc.enumData.ExceptionCodes;
import com.airtel.kyc.enumData.ResponseCodes;
import com.airtel.kyc.enumData.UserExceptionCodes;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.ResponseDto;
import com.airtel.kyc.helpers.dto.TokenResponse;



@SuppressWarnings("rawtypes")
@ControllerAdvice
public class ExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	/**
	 * Handle business exception.
	 *
	 * @param businessException
	 *            the business exception
	 */
	
	@ExceptionHandler(value = BusinessException.class)
	public @ResponseBody BaseResponse handleBusinessException(BusinessException businessException) {
		LOGGER.error("BUSINESS EXCEPTION : " + ExceptionUtils.getStackTrace(businessException));
		BaseResponse kycResponse = new BaseResponse();
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseCode(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode());
		responseDto.setResponseDescription(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseDescription());
		responseDto.setExceptionCode(businessException.getExceptionCode());
		responseDto.setExceptionDescription(businessException.getExceptionMessage());
		kycResponse.setResponseDto(responseDto);
		return kycResponse;
	}

	@ExceptionHandler(value = SystemException.class)
	public @ResponseBody BaseResponse handleSystemException(SystemException systemException) {

		LOGGER.error("SYSTEM EXCEPTION : " + ExceptionUtils.getStackTrace(systemException));
		BaseResponse kycResponse = new BaseResponse();
		ResponseDto responseDto = new ResponseDto();
		responseDto.setResponseCode(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode());
		responseDto.setResponseDescription(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseDescription());
		responseDto.setExceptionCode(ExceptionCodes.SYSTEM_EXCEPTION.getExceptionCode());
		responseDto.setExceptionDescription(ExceptionCodes.SYSTEM_EXCEPTION.getExceptionDescription());
		kycResponse.setResponseDto(responseDto);
		return kycResponse;
	}
	
	  @ExceptionHandler({SQLException.class,DataAccessException.class})
	  public @ResponseBody BaseResponse databaseError() {

		    BaseResponse kycResponse = new BaseResponse();
			ResponseDto responseDto = new ResponseDto();
			
			responseDto.setResponseCode(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseCode());
			responseDto.setResponseDescription(ResponseCodes.FAILURE_RESPONSE_CODE.getResponseDescription());
			responseDto.setExceptionCode(ExceptionCodes.SYSTEM_EXCEPTION.getExceptionCode());
			responseDto.setExceptionDescription(ExceptionCodes.SYSTEM_EXCEPTION.getExceptionDescription());
			
			return kycResponse;
	  }
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  public ResponseEntity<BaseResponse> invalidInput(MethodArgumentNotValidException ex) {
		    BaseResponse response = new BaseResponse();
		    ResponseDto responseDto = new ResponseDto();
		    responseDto.setResponseCode(UserExceptionCodes.INVALID_REQUEST.getExceptionCode());
			responseDto.setResponseDescription(ex.getBindingResult().getFieldError().getDefaultMessage());
			responseDto.setKey(ex.getBindingResult().getFieldError().getField());		   
			response.setResponseDto(responseDto);
	        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	    }
	  
	  
	  @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
	  public ResponseEntity<BaseResponse> invalidInput(AccessDeniedException ex) {		  
		    BaseResponse response = new BaseResponse();
		    ResponseDto responseDto = new ResponseDto();
		    responseDto.setResponseCode(UserExceptionCodes.INVALID_REQUEST.getExceptionCode());					   
			response.setResponseDto(responseDto);
	        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);		  
		  //return new ResponseEntity<BaseResponse>(HttpStatus.FORBIDDEN );
	    }
	  
}
