package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class GKYCStatusRequestDto  implements Serializable{

	private static final long serialVersionUID = 1L;

	private String tUserName;
	private String tPassword;
	private String tMSISDN;
	public String gettUserName() {
		return tUserName;
	}
	public void settUserName(String tUserName) {
		this.tUserName = tUserName;
	}
	public String gettPassword() {
		return tPassword;
	}
	public void settPassword(String tPassword) {
		this.tPassword = tPassword;
	}
	public String gettMSISDN() {
		return tMSISDN;
	}
	public void settMSISDN(String tMSISDN) {
		this.tMSISDN = tMSISDN;
	}
}