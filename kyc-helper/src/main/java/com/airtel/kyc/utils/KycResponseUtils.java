package com.airtel.kyc.utils;

import org.springframework.http.HttpStatus;

import com.airtel.kyc.helpers.dto.ResponseDto;



public class KycResponseUtils {

	public static <T> ResponseDto getKycResponse(HttpStatus httpStatus) {
	
		ResponseDto responseBean = new ResponseDto();
		responseBean.setResponseCode(httpStatus.value());
		responseBean.setResponseDescription(httpStatus.getReasonPhrase());
		return responseBean;

	}

	
	public static <T> ResponseDto getKycResponse(int statusCode,String statusDescription) {
		ResponseDto responseBean = new ResponseDto();
		responseBean.setResponseCode(statusCode);
		responseBean.setResponseDescription(statusDescription);
		return responseBean;
	}
	
	public static <T> ResponseDto getKycResponse(HttpStatus httpStatus,String statusDescription) {
		
		ResponseDto responseBean = new ResponseDto();
		responseBean.setResponseCode(httpStatus.value());
		responseBean.setResponseDescription(statusDescription);
		return responseBean;
	}
	
}
