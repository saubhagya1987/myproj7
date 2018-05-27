package com.airtel.kyc.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.airtel.kyc.repository.listener.AuditedEntityListener;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonInclude(value = Include.NON_NULL)
@Table(name = "subscriber")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({

	//@NamedQuery(name = "Subscriber.findByMsisdnSimSerialNumberIssOldSubscriber", query = " Select s from Subscriber s where msisdn=:msisdn and simSerialNumber=:simSerialNumber"),
	//@NamedQuery(name = "Subscriber.findByMsisdnIsOldSubscriberFInalStatus", query = " Select s from Subscriber s where msisdn=:msisdn and isOldSubscriber=:isOldSubscriber and finalStatus=:finalStatus "),
	//@NamedQuery(name = "Subscriber.findByMsisdnIsOldSubscriber", query = " Select s from Subscriber s where msisdn=:msisdn and isOldSubscriber=:isOldSubscriber"),
	@NamedQuery(name = "Subscriber.findByMsisdn", query = " Select s from Subscriber s where msisdn=:msisdn"),
	@NamedQuery(name = "Subscriber.findBySubscriberId", query = " Select s from Subscriber s where subscriberId=:subscriberId"),
	

	
	@NamedQuery(name = "Subscriber.findSubscriberById", query = " Select s from Subscriber s where s.subscriberId=:subscriberId ") ,
	//@NamedQuery(name = "Subscriber.findSubscriberByIdAndIsOld", query = " Select s from Subscriber s where s.subscriberId=:subscriberId and s.isOldSubscriber=:isOldSubscriber ") ,
	//@NamedQuery(name = "Subscriber.findSubscriberByMsisdn", query = " Select s from Subscriber s where s.subscriberId=:subscriberId and s.msisdn=:msisdn") ,
	//@NamedQuery(name = "Subscriber.findSubscriberByMsisdnAndSimSerialNumber", query = " Select s from Subscriber s where s.subscriberId=:subscriberId and s.msisdn=:msisdn and s.simSerialNumber=:simSerialNumber"),

	//@NamedQuery(name = "Subscriber.findSubscriberBySimSerialNumberAndMsisdnForSearch", query = " Select s from Subscriber s where s.msisdn=:msisdn and s.simSerialNumber=:simSerialNumber and s.finalStatus IN ('REJECTED','APPROVED','PENDING','PARTIAL_ACTIVATED') and s.isOldSubscriber=:isOldSubscriber"),
	//@NamedQuery(name = "Subscriber.findSubscriberByMsisdnForSearch", query = " Select s from Subscriber s where s.msisdn=:msisdn and s.msisdnPrefix=:msisdnPrefix and s.finalStatus IN ('REJECTED','APPROVED','PENDING','PARTIAL_ACTIVATED') and s.isOldSubscriber=:isOldSubscriber"),
	//@NamedQuery(name = "Subscriber.findSubscriberBySimSerialNumber", query = " Select s from Subscriber s where s.simSerialNumber=:simSerialNumber and s.simSerialNumberPrefix=:simSerialNumberPrefix and s.finalStatus IN ('REJECTED','APPROVED','PENDING','PARTIAL_ACTIVATED') and s.isOldSubscriber=:isOldSubscriber")

})


public class Subscriber extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id  
	@Column(name = "subscriber_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_seq")
	@SequenceGenerator(allocationSize = 1, name = "subscriber_seq", sequenceName = "subscriber_seq")
	private Long subscriberId;

	@Column(name = "msisdn")
	private String msisdn; 
	
	@Column(name = "kyc_tansaction_id", unique = true, nullable = false)
	private String kycTansactionId;
	
	@Column(name = "otp")
	private String otp;

	@Column(name = "otp_creation_time")
	private Timestamp otpCreationTime;
	

	@Column(name = "user_flag", columnDefinition = "char default 0")
	private Integer userFlag;
	
	@Column(name = "otp_count", columnDefinition = "char default 0")
	private Integer otpCount;


	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference("subscriberDetails")
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "subscriber", fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	private List<SubscriberDetails> subscriberDetails;

	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Timestamp getOtpCreationTime() {
		return otpCreationTime;
	}

	public void setOtpCreationTime(Timestamp otpCreationTime) {
		this.otpCreationTime = otpCreationTime;
	}

	public List<SubscriberDetails> getSubscriberDetails() {
		return subscriberDetails;
	}

	public void setSubscriberDetails(List<SubscriberDetails> subscriberDetails) {
		this.subscriberDetails = subscriberDetails;
	}

	public Integer getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(Integer userFlag) {
		this.userFlag = userFlag;
	}

	public String getKycTansactionId() {
		return kycTansactionId;
	}

	public void setKycTansactionId(String kycTansactionId) {
		this.kycTansactionId = kycTansactionId;
	}

	public Integer getOtpCount() {
		return otpCount;
	}

	public void setOtpCount(Integer otpCount) {
		this.otpCount = otpCount;
	}

	}