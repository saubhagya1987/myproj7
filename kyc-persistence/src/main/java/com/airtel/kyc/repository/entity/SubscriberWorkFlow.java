package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "subscriber_work_flow")
@NamedQueries({
	@NamedQuery(name = "SubscriberWorkFlow.findByWorkFlowId", query = " Select s from SubscriberWorkFlow s where subscriberWorkFlowId=:subscriberWorkFlowId"),
	@NamedQuery(name = "SubscriberWorkFlow.findBySubscriberworkflowId", query = " Select s from SubscriberWorkFlow s where s.subscriberDetails.subscriberDetailsId=:subscriberDetailsId")

})
public class SubscriberWorkFlow extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "subscriber_work_flow_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_work_flow_seq")
	@SequenceGenerator(allocationSize = 1, name = "subscriber_work_flow_seq", sequenceName = "subscriber_work_flow_seq")
	private Integer subscriberWorkFlowId;

	@Fetch(FetchMode.SELECT)
	@JsonBackReference("subscriberWorkFlow")
	@OneToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "subscriber_details_id",unique = true, nullable = false)
	private SubscriberDetails subscriberDetails;
	
	/*@JoinColumn(name = "subscriber_id")
	private Long subscriberId;*/
		
	@Column(name = "user_id")
	private Integer userId;
		
	@Column(name = "action")
	private String action;
	
	@Column(name = "role_name")
	private String roleName;
		
	@Column(name = "status_reason")
	private String statusReason;


	public Integer getSubscriberWorkFlowId() {
		return subscriberWorkFlowId;
	}

	public void setSubscriberWorkFlowId(Integer subscriberWorkFlowId) {
		this.subscriberWorkFlowId = subscriberWorkFlowId;
	}

	public SubscriberDetails getSubscriberDetails() {
		return subscriberDetails;
	}

	public void setSubscriberDetails(SubscriberDetails subscriberDetails) {
		this.subscriberDetails = subscriberDetails;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

/*	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}*/

}