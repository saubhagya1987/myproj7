package com.airtel.africa.airtel.money.helpers;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "COMMAND")
public class AMChurnResponse {

	private String TYPE;
	private String EXTTRID;
	private String TXNID;
	private String TXNSTATUS;
	private String MESSAGE;

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getEXTTRID() {
		return EXTTRID;
	}

	public void setEXTTRID(String eXTTRID) {
		EXTTRID = eXTTRID;
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

	public String toXml() {
		StringBuffer dtoValue = new StringBuffer("");
		dtoValue.append(
				"<?xml version=\"1.0\"?><!DOCTYPE COMMAND PUBLIC \"-//Ocam//DTD XML Command 1.0//EN\" \"xml/command.dtd\">");
		dtoValue.append("<COMMAND>");
		if (TYPE != null)
			dtoValue.append("<TYPE>").append(TYPE).append("</TYPE>");
		if (EXTTRID != null)
			dtoValue.append("<EXTTRID>").append(EXTTRID).append("</EXTTRID>");
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
