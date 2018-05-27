package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class TotalCountDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long subscriberCount;
	private long cmSubscriberCount;
	private long cmSelfAssignedSubscriberCount;
	private long cmApprovedSubscriberCount;
	private long cmRejectedSubscriberCount;
	private long kycOpenCasesSubscriberCount;
	private long kycClosedCasesSubscriberCount;
	
	public long getSubscriberCount() {
		return subscriberCount;
	}
	public void setSubscriberCount(long subscriberCount) {
		this.subscriberCount = subscriberCount;
	}
	public long getCmSubscriberCount() {
		return cmSubscriberCount;
	}
	public void setCmSubscriberCount(long cmSubscriberCount) {
		this.cmSubscriberCount = cmSubscriberCount;
	}
	public long getCmSelfAssignedSubscriberCount() {
		return cmSelfAssignedSubscriberCount;
	}
	public void setCmSelfAssignedSubscriberCount(long cmSelfAssignedSubscriberCount) {
		this.cmSelfAssignedSubscriberCount = cmSelfAssignedSubscriberCount;
	}
	public long getCmApprovedSubscriberCount() {
		return cmApprovedSubscriberCount;
	}
	public void setCmApprovedSubscriberCount(long cmApprovedSubscriberCount) {
		this.cmApprovedSubscriberCount = cmApprovedSubscriberCount;
	}
	public long getCmRejectedSubscriberCount() {
		return cmRejectedSubscriberCount;
	}
	public void setCmRejectedSubscriberCount(long cmRejectedSubscriberCount) {
		this.cmRejectedSubscriberCount = cmRejectedSubscriberCount;
	}
	public long getKycOpenCasesSubscriberCount() {
		return kycOpenCasesSubscriberCount;
	}
	public void setKycOpenCasesSubscriberCount(long kycOpenCasesSubscriberCount) {
		this.kycOpenCasesSubscriberCount = kycOpenCasesSubscriberCount;
	}
	public long getKycClosedCasesSubscriberCount() {
		return kycClosedCasesSubscriberCount;
	}
	public void setKycClosedCasesSubscriberCount(long kycClosedCasesSubscriberCount) {
		this.kycClosedCasesSubscriberCount = kycClosedCasesSubscriberCount;
	}
}