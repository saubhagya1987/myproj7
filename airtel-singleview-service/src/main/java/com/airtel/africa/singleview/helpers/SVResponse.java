package com.airtel.africa.singleview.helpers;

public class SVResponse {

	private Object result;

	private Object responseObject;

	private boolean isExceptionOccured;

	private Exception exception;

	private String request;

	private String response;

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

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
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
		return "SVResponse [result=" + result + ", responseObject=" + responseObject + ", isExceptionOccured="
				+ isExceptionOccured + ", exception=" + exception + ", request=" + request + ", response=" + response
				+ "]";
	}

	
}
