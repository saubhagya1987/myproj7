package com.airtel.kyc.helpers.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationResponse {

	private boolean status;
	
	private boolean isMailTriggered;

	private boolean isSMSTriggered;

	private boolean isExceptionOccured;

	private Exception exception;

	private String request;

	private String response;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isMailTriggered() {
		return isMailTriggered;
	}

	public void setMailTriggered(boolean isMailTriggered) {
		this.isMailTriggered = isMailTriggered;
	}

	public boolean isSMSTriggered() {
		return isSMSTriggered;
	}

	public void setSMSTriggered(boolean isSMSTriggered) {
		this.isSMSTriggered = isSMSTriggered;
	}

	public boolean isExceptionOccured() {
		return isExceptionOccured;
	}

	public void setExceptionOccured(boolean isExceptionOccured) {
		this.isExceptionOccured = isExceptionOccured;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
	
	
	
}
