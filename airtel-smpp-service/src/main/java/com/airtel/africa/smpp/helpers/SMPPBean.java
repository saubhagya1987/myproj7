package com.airtel.africa.smpp.helpers;

import java.util.Arrays;

import org.jsmpp.bean.DataCoding;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.OptionalParameter;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.TypeOfNumber;

public class SMPPBean {

	private String serviceType;
	private TypeOfNumber sourceAddrTon;
	private NumberingPlanIndicator sourceAddrNpi;
	private String sourceAddr;
	private TypeOfNumber destAddrTon;
	private NumberingPlanIndicator destAddrNpi;
	private String destinationAddr;
	private ESMClass esmClass;
	private byte protocolId;
	private byte priorityFlag;
	private String scheduleDeliveryTime;
	private String validityPeriod;
	private RegisteredDelivery registeredDelivery;
	private byte replaceIfPresentFlag;
	private DataCoding dataCoding;
	private byte smDefaultMsgId;
	private byte[] shortMessage;
	private OptionalParameter[] optionalParameters;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public TypeOfNumber getSourceAddrTon() {
		return sourceAddrTon;
	}

	public void setSourceAddrTon(TypeOfNumber sourceAddrTon) {
		this.sourceAddrTon = sourceAddrTon;
	}

	public NumberingPlanIndicator getSourceAddrNpi() {
		return sourceAddrNpi;
	}

	public void setSourceAddrNpi(NumberingPlanIndicator sourceAddrNpi) {
		this.sourceAddrNpi = sourceAddrNpi;
	}

	public String getSourceAddr() {
		return sourceAddr;
	}

	public void setSourceAddr(String sourceAddr) {
		this.sourceAddr = sourceAddr;
	}

	public TypeOfNumber getDestAddrTon() {
		return destAddrTon;
	}

	public void setDestAddrTon(TypeOfNumber destAddrTon) {
		this.destAddrTon = destAddrTon;
	}

	public NumberingPlanIndicator getDestAddrNpi() {
		return destAddrNpi;
	}

	public void setDestAddrNpi(NumberingPlanIndicator destAddrNpi) {
		this.destAddrNpi = destAddrNpi;
	}

	public String getDestinationAddr() {
		return destinationAddr;
	}

	public void setDestinationAddr(String destinationAddr) {
		this.destinationAddr = destinationAddr;
	}

	public ESMClass getEsmClass() {
		return esmClass;
	}

	public void setEsmClass(ESMClass esmClass) {
		this.esmClass = esmClass;
	}

	public byte getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(byte protocolId) {
		this.protocolId = protocolId;
	}

	public byte getPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(byte priorityFlag) {
		this.priorityFlag = priorityFlag;
	}

	public String getScheduleDeliveryTime() {
		return scheduleDeliveryTime;
	}

	public void setScheduleDeliveryTime(String scheduleDeliveryTime) {
		this.scheduleDeliveryTime = scheduleDeliveryTime;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public RegisteredDelivery getRegisteredDelivery() {
		return registeredDelivery;
	}

	public void setRegisteredDelivery(RegisteredDelivery registeredDelivery) {
		this.registeredDelivery = registeredDelivery;
	}

	public byte getReplaceIfPresentFlag() {
		return replaceIfPresentFlag;
	}

	public void setReplaceIfPresentFlag(byte replaceIfPresentFlag) {
		this.replaceIfPresentFlag = replaceIfPresentFlag;
	}

	public DataCoding getDataCoding() {
		return dataCoding;
	}

	public void setDataCoding(DataCoding dataCoding) {
		this.dataCoding = dataCoding;
	}

	public byte getSmDefaultMsgId() {
		return smDefaultMsgId;
	}

	public void setSmDefaultMsgId(byte smDefaultMsgId) {
		this.smDefaultMsgId = smDefaultMsgId;
	}

	public byte[] getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(byte[] shortMessage) {
		this.shortMessage = shortMessage;
	}

	public OptionalParameter[] getOptionalParameters() {
		return optionalParameters;
	}

	public void setOptionalParameters(OptionalParameter[] optionalParameters) {
		this.optionalParameters = optionalParameters;
	}

	@Override
	public String toString() {
		return "SMPPBean [serviceType=" + serviceType + ", sourceAddrTon=" + sourceAddrTon + ", sourceAddrNpi="
				+ sourceAddrNpi + ", sourceAddr=" + sourceAddr + ", destAddrTon=" + destAddrTon + ", destAddrNpi="
				+ destAddrNpi + ", destinationAddr=" + destinationAddr + ", esmClass=" + esmClass + ", protocolId="
				+ protocolId + ", priorityFlag=" + priorityFlag + ", scheduleDeliveryTime=" + scheduleDeliveryTime
				+ ", validityPeriod=" + validityPeriod + ", registeredDelivery=" + registeredDelivery
				+ ", replaceIfPresentFlag=" + replaceIfPresentFlag + ", dataCoding=" + dataCoding + ", smDefaultMsgId="
				+ smDefaultMsgId + ", shortMessage=" + Arrays.toString(shortMessage) + ", optionalParameters="
				+ Arrays.toString(optionalParameters) + "]";
	}
}
