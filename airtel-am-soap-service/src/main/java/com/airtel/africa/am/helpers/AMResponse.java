package com.airtel.africa.am.helpers;

public class AMResponse {

	private Object result;

	private Object responseObject;

	private boolean isExceptionOccured;

	private Exception exception;

	private String request;

	private String response;

	private String ERRCODE;
	
	
	public String getERRCODE() {
		return ERRCODE;
	}

	public void setERRCODE(String eRRCODE) {
		ERRCODE = eRRCODE;
	}

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

	@Override
	public String toString() {
		return "AMResponse [result=" + result + ", responseObject=" + responseObject + ", isExceptionOccured="
				+ isExceptionOccured + ", exception=" + exception + ", request=" + request + ", response=" + response
				+ "]";
	}
	
	

}
