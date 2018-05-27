package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ConfigDataDTO implements Serializable{
	
	private String amBarring;
	
	private String cpSupport;
	
	private String hotlineNonRgeCustomer;

	private String barringNonRgeCustomer;
	
	private String limitSimSwapDenial;
	
	private String connectionsAllowed;
	
	private String rejectBarredGKYC;
	
	private String noOfDigitsMatchIdSimSwap;
	
	private String noOfTimesGkycStatusChanged;	

	public String getAmBarring() {
		return amBarring;
	}

	public void setAmBarring(String amBarring) {
		this.amBarring = amBarring;
	}

	public String getCpSupport() {
		return cpSupport;
	}

	public void setCpSupport(String cpSupport) {
		this.cpSupport = cpSupport;
	}

	public String getHotlineNonRgeCustomer() {
		return hotlineNonRgeCustomer;
	}

	public void setHotlineNonRgeCustomer(String hotlineNonRgeCustomer) {
		this.hotlineNonRgeCustomer = hotlineNonRgeCustomer;
	}

	public String getBarringNonRgeCustomer() {
		return barringNonRgeCustomer;
	}

	public void setBarringNonRgeCustomer(String barringNonRgeCustomer) {
		this.barringNonRgeCustomer = barringNonRgeCustomer;
	}

	public String getLimitSimSwapDenial() {
		return limitSimSwapDenial;
	}

	public void setLimitSimSwapDenial(String limitSimSwapDenial) {
		this.limitSimSwapDenial = limitSimSwapDenial;
	}

	public String getConnectionsAllowed() {
		return connectionsAllowed;
	}

	public void setConnectionsAllowed(String connectionsAllowed) {
		this.connectionsAllowed = connectionsAllowed;
	}

	public String getRejectBarredGKYC() {
		return rejectBarredGKYC;
	}

	public void setRejectBarredGKYC(String rejectBarredGKYC) {
		this.rejectBarredGKYC = rejectBarredGKYC;
	}

	public String getNoOfDigitsMatchIdSimSwap() {
		return noOfDigitsMatchIdSimSwap;
	}

	public void setNoOfDigitsMatchIdSimSwap(String noOfDigitsMatchIdSimSwap) {
		this.noOfDigitsMatchIdSimSwap = noOfDigitsMatchIdSimSwap;
	}

	public String getNoOfTimesGkycStatusChanged() {
		return noOfTimesGkycStatusChanged;
	}

	public void setNoOfTimesGkycStatusChanged(String noOfTimesGkycStatusChanged) {
		this.noOfTimesGkycStatusChanged = noOfTimesGkycStatusChanged;
	}
}