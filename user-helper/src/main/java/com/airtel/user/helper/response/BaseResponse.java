package com.airtel.user.helper.response;

import java.io.Serializable;

import com.airtel.user.helper.dto.ResponseDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class BaseResponse<T> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The response dto. */
	private ResponseDto responseDto;
	
	/** The body. */
	private T body;

	public ResponseDto getResponseDto() {
		return responseDto;
	}

	public void setResponseDto(ResponseDto responseDto) {
		this.responseDto = responseDto;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
}
