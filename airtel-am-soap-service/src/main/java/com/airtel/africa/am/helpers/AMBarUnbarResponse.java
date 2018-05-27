package com.airtel.africa.am.helpers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "COMMAND")
public class AMBarUnbarResponse {

	private String TYPE;
	private String MSISDN;
	private String TXNID;
	private String TXNSTATUS;
	private String MESSAGE;
	private String BALANCE;
	private String COMWBALANCE;
	private String WALL3BALANCE;
	private String WALL4BALANCE;
	private String LASTPINRESET;
	private String CATEGORYCODE;

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getMSISDN() {
		return MSISDN;
	}

	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}

	public String getTXNID() {
		return TXNID;
	}

	public void setTXNID(String tXNID) {
		TXNID = tXNID;
	}

	public String getTXNSTATUS() {
		return TXNSTATUS;
	}

	public void setTXNSTATUS(String tXNSTATUS) {
		TXNSTATUS = tXNSTATUS;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}

	public String getBALANCE() {
		return BALANCE;
	}

	public void setBALANCE(String bALANCE) {
		BALANCE = bALANCE;
	}

	public String getCOMWBALANCE() {
		return COMWBALANCE;
	}

	public void setCOMWBALANCE(String cOMWBALANCE) {
		COMWBALANCE = cOMWBALANCE;
	}

	public String getWALL3BALANCE() {
		return WALL3BALANCE;
	}

	public void setWALL3BALANCE(String wALL3BALANCE) {
		WALL3BALANCE = wALL3BALANCE;
	}

	public String getWALL4BALANCE() {
		return WALL4BALANCE;
	}

	public void setWALL4BALANCE(String wALL4BALANCE) {
		WALL4BALANCE = wALL4BALANCE;
	}

	public String getLASTPINRESET() {
		return LASTPINRESET;
	}

	public void setLASTPINRESET(String lASTPINRESET) {
		LASTPINRESET = lASTPINRESET;
	}

	public String getCATEGORYCODE() {
		return CATEGORYCODE;
	}

	public void setCATEGORYCODE(String cATEGORYCODE) {
		CATEGORYCODE = cATEGORYCODE;
	}

	public String toXml() {
		StringBuffer dtoValue = new StringBuffer("");
		dtoValue.append(
				"<?xml version=\"1.0\"?><!DOCTYPE COMMAND PUBLIC \"-//Ocam//DTD XML Command 1.0//EN\" \"xml/command.dtd\">");
		dtoValue.append("<COMMAND>");
		if (TYPE != null)
			dtoValue.append("<TYPE>").append(TYPE).append("</TYPE>");
		if (MSISDN != null)
			dtoValue.append("<MSISDN>").append(MSISDN).append("</MSISDN>");
		if (TXNID != null)
			dtoValue.append("<TXNID>").append(TXNID).append("</TXNID>");
		if (TXNSTATUS != null)
			dtoValue.append("<TXNSTATUS>").append(TXNSTATUS).append("</TXNSTATUS>");
		if (MESSAGE != null)
			dtoValue.append("<MESSAGE>").append(MESSAGE).append("</MESSAGE>");
		dtoValue.append("</COMMAND>");
		return dtoValue.toString();
	}

}
