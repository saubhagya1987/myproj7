package com.airtel.africa.am.helpers;

public class AMBarUnbarRequest {

	private String TYPE;
	private String MSISDN;
	private String PROVIDER;
	private String BARTYPE;
	private String USERTYPE;
	private String REASON;
	private String REMARK;
	private String TRID;
	private String SERVICE;
	
	public String getSERVICE() {
		return SERVICE;
	}

	public void setSERVICE(String sERVICE) {
		SERVICE = sERVICE;
	}

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

	public String getPROVIDER() {
		return PROVIDER;
	}

	public void setPROVIDER(String pROVIDER) {
		PROVIDER = pROVIDER;
	}

	public String getBARTYPE() {
		return BARTYPE;
	}

	public void setBARTYPE(String bARTYPE) {
		BARTYPE = bARTYPE;
	}

	public String getUSERTYPE() {
		return USERTYPE;
	}

	public void setUSERTYPE(String uSERTYPE) {
		USERTYPE = uSERTYPE;
	}

	public String getREASON() {
		return REASON;
	}

	public void setREASON(String rEASON) {
		REASON = rEASON;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getTRID() {
		return TRID;
	}

	public void setTRID(String tRID) {
		TRID = tRID;
	}

	public String toXml() {
		StringBuffer dtoValue = new StringBuffer("");
		dtoValue.append("<?xml version=\"1.0\"?><!DOCTYPE COMMAND PUBLIC \"-//Ocam//DTD XML Command 1.0//EN\" \"xml/command.dtd\">");
		dtoValue.append("<COMMAND>");
		if(TYPE != null) 
			dtoValue.append("<TYPE>").append(TYPE).append("</TYPE>");
		if(SERVICE != null) 
			dtoValue.append("<SERVICE>").append(SERVICE).append("</SERVICE>");
		if(USERTYPE != null) 
			dtoValue.append("<USERTYPE>").append(USERTYPE).append("</USERTYPE>");
		if(BARTYPE != null) 
			dtoValue.append("<BARTYPE>").append(BARTYPE).append("</BARTYPE>");
		if(REASON != null) 
			dtoValue.append("<REASON>").append(REASON).append("</REASON>");
		if(MSISDN != null) 
			dtoValue.append("<MSISDN>").append(MSISDN).append("</MSISDN>");
		if(PROVIDER != null) 
			dtoValue.append("<PROVIDER>").append(PROVIDER).append("</PROVIDER>");
		if(REMARK != null) 
			dtoValue.append("<REMARK>").append(REMARK).append("</REMARK>");
		if(TRID != null) 
			dtoValue.append("<TRID>").append(TRID).append("</TRID>");
		dtoValue.append("</COMMAND>");
		return dtoValue.toString();
	}

	
}
