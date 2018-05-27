package com.airtel.kyc.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;


//@Entity
//@Table(name = "action_on_subscriber")
public class ActionOnSubscriber implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "action_subscriber_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "action_subscriber_seq")
	@SequenceGenerator(allocationSize = 1,name = "action_subscriber_seq", sequenceName = "action_subscriber_seq")
	private Integer actionSubscriberId;	
		
	@Column(name = "action_type")
	private Timestamp actionType;
	
	@Column(name = "action_time")
	private Timestamp actionTime;
		
	@Column(name="action_by")
	private String actionBy;
		
	@Column(name="action_status")
	private String actionStatus;
	
	@Fetch(FetchMode.SELECT)
	@JsonBackReference("actionOnSubscriber")
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "subscriber_id", nullable = false)
	private Subscriber subscriber;

	public Integer getActionSubscriberId() {
		return actionSubscriberId;
	}

	public void setActionSubscriberId(Integer actionSubscriberId) {
		this.actionSubscriberId = actionSubscriberId;
	}

	public Timestamp getActionType() {
		return actionType;
	}

	public void setActionType(Timestamp actionType) {
		this.actionType = actionType;
	}

	public Timestamp getActionTime() {
		return actionTime;
	}

	public void setActionTime(Timestamp actionTime) {
		this.actionTime = actionTime;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}
	
	
	
}