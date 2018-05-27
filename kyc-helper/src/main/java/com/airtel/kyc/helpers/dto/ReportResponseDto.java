package com.airtel.kyc.helpers.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ReportResponseDto  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//For ChannelPartner
	private String lastName;
	private String firstName;
	private String msisdn;	
	private Integer totalCount;
	private Integer approvedCount;
	private Integer barredCount;
	private Integer rejectedCount;
	private Integer hotLineCount;
	private Integer editCount;
	private Integer simSwapCount;
	
	
	//For Data executive
	private Integer newRegistration;
	private Integer existingCustomerNewLine;
	private Integer totalProcessed;
	private Integer totalApproved;
	private Integer totalBarred;
	private Integer totalRejected;
	private Integer totalHotlined;
	private Integer totalEditProfile;
	private Integer totalEditProfileApproved;
	private Integer totalEditProfileRejected;	
	private String caseType;
	private Date updatedOn;	
	private String updatedTime;
	private String reason;
	
	private String cpName;
	private String channelName;
	private Integer mtd;
	private Integer lmtd;
	private Integer growth;
	
	private String userName;
	private Integer totalRegistration;
	private Integer percentageApproved;
	
	private String channel;
	private Integer atm;
	private Integer	alm;
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getApprovedCount() {
		return approvedCount;
	}
	public void setApprovedCount(Integer approvedCount) {
		this.approvedCount = approvedCount;
	}
	public Integer getBarredCount() {
		return barredCount;
	}
	public void setBarredCount(Integer barredCount) {
		this.barredCount = barredCount;
	}
	public Integer getRejectedCount() {
		return rejectedCount;
	}
	public void setRejectedCount(Integer rejectedCount) {
		this.rejectedCount = rejectedCount;
	}
	public Integer getHotLineCount() {
		return hotLineCount;
	}
	public void setHotLineCount(Integer hotLineCount) {
		this.hotLineCount = hotLineCount;
	}
	public Integer getEditCount() {
		return editCount;
	}
	public void setEditCount(Integer editCount) {
		this.editCount = editCount;
	}
	public Integer getSimSwapCount() {
		return simSwapCount;
	}
	public void setSimSwapCount(Integer simSwapCount) {
		this.simSwapCount = simSwapCount;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public Integer getNewRegistration() {
		return newRegistration;
	}
	public void setNewRegistration(Integer newRegistration) {
		this.newRegistration = newRegistration;
	}
	public Integer getExistingCustomerNewLine() {
		return existingCustomerNewLine;
	}
	public void setExistingCustomerNewLine(Integer existingCustomerNewLine) {
		this.existingCustomerNewLine = existingCustomerNewLine;
	}
	public Integer getTotalProcessed() {
		return totalProcessed;
	}
	public void setTotalProcessed(Integer totalProcessed) {
		this.totalProcessed = totalProcessed;
	}
	public Integer getTotalApproved() {
		return totalApproved;
	}
	public void setTotalApproved(Integer totalApproved) {
		this.totalApproved = totalApproved;
	}
	public Integer getTotalBarred() {
		return totalBarred;
	}
	public void setTotalBarred(Integer totalBarred) {
		this.totalBarred = totalBarred;
	}
	public Integer getTotalRejected() {
		return totalRejected;
	}
	public void setTotalRejected(Integer totalRejected) {
		this.totalRejected = totalRejected;
	}
	public Integer getTotalHotlined() {
		return totalHotlined;
	}
	public void setTotalHotlined(Integer totalHotlined) {
		this.totalHotlined = totalHotlined;
	}
	public Integer getTotalEditProfile() {
		return totalEditProfile;
	}
	public void setTotalEditProfile(Integer totalEditProfile) {
		this.totalEditProfile = totalEditProfile;
	}
	public Integer getTotalEditProfileApproved() {
		return totalEditProfileApproved;
	}
	public void setTotalEditProfileApproved(Integer totalEditProfileApproved) {
		this.totalEditProfileApproved = totalEditProfileApproved;
	}
	public Integer getTotalEditProfileRejected() {
		return totalEditProfileRejected;
	}
	public void setTotalEditProfileRejected(Integer totalEditProfileRejected) {
		this.totalEditProfileRejected = totalEditProfileRejected;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getCpName() {
		return cpName;
	}
	public void setCpName(String cpName) {
		this.cpName = cpName;
	}
	public Integer getMtd() {
		return mtd;
	}
	public void setMtd(Integer mtd) {
		this.mtd = mtd;
	}
	public Integer getLmtd() {
		return lmtd;
	}
	public void setLmtd(Integer lmtd) {
		this.lmtd = lmtd;
	}
	public Integer getGrowth() {
		return growth;
	}
	public void setGrowth(Integer growth) {
		this.growth = growth;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getTotalRegistration() {
		return totalRegistration;
	}
	public void setTotalRegistration(Integer totalRegistration) {
		this.totalRegistration = totalRegistration;
	}
	public Integer getPercentageApproved() {
		return percentageApproved;
	}
	public void setPercentageApproved(Integer percentageApproved) {
		this.percentageApproved = percentageApproved;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getAtm() {
		return atm;
	}
	public void setAtm(Integer atm) {
		this.atm = atm;
	}
	public Integer getAlm() {
		return alm;
	}
	public void setAlm(Integer alm) {
		this.alm = alm;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	

}
