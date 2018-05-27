package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.airtel.user.helper.dto.UsersDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class EncryptedJsonDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String encyptredKey;
	
	private String encyptedValue;
	
	private String tokenKey;
	
	private String tokenValue;
	
	//private UsersDto userData;

	public String getEncyptredKey() {
		return encyptredKey;
	}

	public void setEncyptredKey(String encyptredKey) {
		this.encyptredKey = encyptredKey;
	}

	public String getEncyptedValue() {
		return encyptedValue;
	}

	public void setEncyptedValue(String encyptedValue) {
		this.encyptedValue = encyptedValue;
	}

	/*public UsersDto getUserData() {
		return userData;
	}

	public void setUserData(UsersDto userData) {
		this.userData = userData;
	}*/

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public String getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}

	

	

	
	
	
	

}