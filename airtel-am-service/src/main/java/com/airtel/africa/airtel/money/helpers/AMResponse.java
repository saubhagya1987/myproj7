package com.airtel.africa.airtel.money.helpers;

public class AMResponse {

	private Object result;
	
	private Object responseObject;
	
	private boolean isExceptionOccured;

	private Exception exception;

	private String request;

	private String response;
	

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
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

	public void setResult(String result) {
		this.result = result;
	}
}