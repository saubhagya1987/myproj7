package com.airtel.africa.airtel.money.helpers;

public class AMChurnRequest {

	private String TYPE;
	private String MSISDN;
	private String INTERFACEID;
	private String EXTTRID;

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

	public String getINTERFACEID() {
		return INTERFACEID;
	}

	public void setINTERFACEID(String iNTERFACEID) {
		INTERFACEID = iNTERFACEID;
	}

	public String getEXTTRID() {
		return EXTTRID;
	}

	public void setEXTTRID(String eXTTRID) {
		EXTTRID = eXTTRID;
	}

	public String toXml() {
		StringBuffer dtoValue = new StringBuffer("");
		dtoValue.append(
				"<?xml version=\"1.0\"?><!DOCTYPE COMMAND PUBLIC \"-//Ocam//DTD XML Command 1.0//EN\" \"xml/command.dtd\">");
		dtoValue.append("<COMMAND>");
		if (TYPE != null)
			dtoValue.append("<TYPE>").append(TYPE).append("</TYPE>");
		if (INTERFACEID != null)
			dtoValue.append("<INTERFACEID>").append(INTERFACEID).append("</INTERFACEID>");
		if (MSISDN != null)
			dtoValue.append("<MSISDN>").append(MSISDN).append("</MSISDN>");
		if (EXTTRID != null)
			dtoValue.append("<EXTTRID>").append(EXTTRID).append("</EXTTRID>");
		dtoValue.append("</COMMAND>");
		return dtoValue.toString();
	}

}
