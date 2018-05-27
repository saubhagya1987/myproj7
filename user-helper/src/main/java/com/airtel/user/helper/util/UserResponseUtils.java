package com.airtel.user.helper.util;

import org.springframework.http.HttpStatus;

import com.airtel.user.helper.dto.ResponseBeanDto;
public class UserResponseUtils {

	public static <T> ResponseBeanDto<T> getUserResponse(HttpStatus httpStatus) {
		ResponseBeanDto<T> responseBean = new ResponseBeanDto<>();
		responseBean.setStatusCode(httpStatus.value());
		responseBean.setStatusDescription(httpStatus.getReasonPhrase());
		return responseBean;
	}

	public static <T> ResponseBeanDto<T> getUserResponse(UserCode userCode) {
		ResponseBeanDto<T> responseBean = new ResponseBeanDto<>();
		responseBean.setStatusCode(userCode.getCode());
		responseBean.setStatusDescription(userCode.getDescription());
		return responseBean;
	}

	
	public static <T> ResponseBeanDto<T> getUserResponse(int statusCode,String statusDescription) {
		ResponseBeanDto<T> responseBean = new ResponseBeanDto<>();
		responseBean.setStatusCode(statusCode);
		responseBean.setStatusDescription(statusDescription);
		return responseBean;
	}
}
