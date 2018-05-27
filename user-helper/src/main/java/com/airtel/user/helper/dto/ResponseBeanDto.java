package com.airtel.user.helper.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseBeanDto<T> extends SearchResponseDto implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;

	  private String statusDescription;

	  private String developerMessage;

	  private String userMessage;

	  private T body;
	  
	  private ResponseDto responseDto;

	  public String getDeveloperMessage() {
	    return developerMessage;
	  }

	  public void setDeveloperMessage(String developerMessage) {
	    this.developerMessage = developerMessage;
	  }

	  public String getUserMessage() {
	    return userMessage;
	  }

	  public void setUserMessage(String userMessage) {
	    this.userMessage = userMessage;
	  }

	  public T getBody() {
	    return body;
	  }

	  public void setBody(T body) {
	    this.body = body;
	  }

	  public int getStatusCode() {
	    return statusCode;
	  }

	  public void setStatusCode(int status) {
	    this.statusCode = status;
	  }

	  public String getStatusDescription() {
	    return statusDescription;
	  }

	  public void setStatusDescription(String statusDescription) {
	    this.statusDescription = statusDescription;
	  }

	public ResponseDto getResponseDto() {
		return responseDto;
	}

	public void setResponseDto(ResponseDto responseDto) {
		this.responseDto = responseDto;
	}
	}