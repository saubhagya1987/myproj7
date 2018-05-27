package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(value = Include.NON_NULL)
public class EncryptedResponseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private String encryptedValue;



	public String getEncryptedValue() {
		return encryptedValue;
	}



	public void setEncryptedValue(String encryptedValue) {
		this.encryptedValue = encryptedValue;
	}
	


	
	

}