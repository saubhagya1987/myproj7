package com.airtel.africa.airtel.ema.helpers;

import com.airtel.africa.airtel.ema.exceptions.EmaException;

public class EMAResponse {

	private boolean isUnbar;

	private boolean isExceptionOccured;

	private EmaException exception;
	
	private String exceptionMessage;

	private String request;

	private String response;

	
	
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public boolean isUnbar() {
		return isUnbar;
	}

	public void setUnbar(boolean isUnbar) {
		this.isUnbar = isUnbar;
	}

	public boolean isExceptionOccured() {
		return isExceptionOccured;
	}

	public void setExceptionOccured(boolean isExceptionOccured) {
		this.isExceptionOccured = isExceptionOccured;
	}

	public EmaException getException() {
		return exception;
	}

	public void setException(EmaException exception) {
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
		return "EMAResponse [isUnbar=" + isUnbar + ", isExceptionOccured=" + isExceptionOccured + ", exception="
				+ exception + ", request=" + request + ", response=" + response + "]";
	}

	
}
