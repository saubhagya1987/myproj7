package com.airtel.kyc.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "audit_log")
@NamedQueries({	
	@NamedQuery(name = "AuditLog.findByIpAddressAndMobileNo", query = "Select a from AuditLog a where mobileNumber=:mobileNumber and ipAddress=:ipAddress and createdOn=:createdOn"),
	
})
public class AuditLog implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "audit_log_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_log_seq")
	@SequenceGenerator(allocationSize = 1, name = "audit_log_seq", sequenceName = "audit_log_seq")
	private Integer auditLogId;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "activity_name")
	private String activityName;

	@Column(name = "request_message")
	private String requestMessage;

	@Column(name = "response_message")
	private String responseMessage;

	@Column(name = "error_trace")
	private String errorTrace;
	
	@Column(name = "created_on")
	private Timestamp createdOn;
	
	@Column(name = "updated_on")
	private Timestamp updatedOn;

	public Integer getAuditLogId() {
		return auditLogId;
	}

	public void setAuditLogId(Integer auditLogId) {
		this.auditLogId = auditLogId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getErrorTrace() {
		return errorTrace;
	}

	public void setErrorTrace(String errorTrace) {
		this.errorTrace = errorTrace;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	

}
