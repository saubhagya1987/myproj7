package com.airtel.kyc.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;


//@Entity
//@Table(name = "operaction_on_subscriber")
public class OperactionOnSubscriber implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "operaction_subscriber_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operaction_subscriber_seq")
	@SequenceGenerator(allocationSize = 1,name = "operaction_subscriber_seq", sequenceName = "operaction_subscriber_seq")
	private Integer operactionSubscriberId;	
		
	@Column(name = "am_activation_time")
	private Timestamp amActivationTime;
	
	@Column(name = "sv_activation_time")
	private Timestamp svActivationTime;
		
	@Column(name="bi_activation_time")
	private Timestamp biActivationTime;
		
	@Column(name="ema_activation_time")
	private Timestamp emaActivationTime;
	
	@JsonBackReference("operactionOnSubscriber")
	@Fetch(FetchMode.SELECT)
	@OneToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "subscriber_id")
	private Subscriber subscriber;

	public Integer getOperactionSubscriberId() {
		return operactionSubscriberId;
	}

	public void setOperactionSubscriberId(Integer operactionSubscriberId) {
		this.operactionSubscriberId = operactionSubscriberId;
	}

	public Timestamp getAmActivationTime() {
		return amActivationTime;
	}

	public void setAmActivationTime(Timestamp amActivationTime) {
		this.amActivationTime = amActivationTime;
	}

	public Timestamp getSvActivationTime() {
		return svActivationTime;
	}

	public void setSvActivationTime(Timestamp svActivationTime) {
		this.svActivationTime = svActivationTime;
	}

	public Timestamp getBiActivationTime() {
		return biActivationTime;
	}

	public void setBiActivationTime(Timestamp biActivationTime) {
		this.biActivationTime = biActivationTime;
	}

	public Timestamp getEmaActivationTime() {
		return emaActivationTime;
	}

	public void setEmaActivationTime(Timestamp emaActivationTime) {
		this.emaActivationTime = emaActivationTime;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}
	
	
	
	
}