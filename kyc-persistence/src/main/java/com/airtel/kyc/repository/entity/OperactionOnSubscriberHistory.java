package com.airtel.kyc.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


//@Entity
//@Table(name = "operaction_on_subscriber_hist")
public class OperactionOnSubscriberHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "operaction_subscriber_Hist_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opt_subscriber_Hist_seq")
	@SequenceGenerator(allocationSize = 1,name = "opt_subscriber_Hist_seq", sequenceName = "opt_subscriber_Hist_seq")
	private Integer operactionSubscriberHistId;	
				
	@Column(name="operaction_status")
	private String operactionStatus;
		
	@Column(name="operaction_type")
	private String operactionType;
		
	@Column(name = "subscriber_id")
	private Integer subscriberId;
	
	@Column(name = "operaction_subscriber_id")
	private Integer operactionSubscriberId;	
	
	@Column(name = "operaction_time")
	private Timestamp operactionTime;
	
	@Column(name = "operaction_by")
	private String operactionBy;
		
	@Column(name = "operaction_remark")
	private String operactionRemark;

	public Integer getOperactionSubscriberHistId() {
		return operactionSubscriberHistId;
	}

	public void setOperactionSubscriberHistId(Integer operactionSubscriberHistId) {
		this.operactionSubscriberHistId = operactionSubscriberHistId;
	}

	public String getOperactionStatus() {
		return operactionStatus;
	}

	public void setOperactionStatus(String operactionStatus) {
		this.operactionStatus = operactionStatus;
	}

	public String getOperactionType() {
		return operactionType;
	}

	public void setOperactionType(String operactionType) {
		this.operactionType = operactionType;
	}

	public Integer getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Integer subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Timestamp getOperactionTime() {
		return operactionTime;
	}

	public void setOperactionTime(Timestamp operactionTime) {
		this.operactionTime = operactionTime;
	}

	public String getOperactionBy() {
		return operactionBy;
	}

	public void setOperactionBy(String operactionBy) {
		this.operactionBy = operactionBy;
	}

	public String getOperactionRemark() {
		return operactionRemark;
	}

	public void setOperactionRemark(String operactionRemark) {
		this.operactionRemark = operactionRemark;
	}

	public Integer getOperactionSubscriberId() {
		return operactionSubscriberId;
	}

	public void setOperactionSubscriberId(Integer operactionSubscriberId) {
		this.operactionSubscriberId = operactionSubscriberId;
	}

	
}