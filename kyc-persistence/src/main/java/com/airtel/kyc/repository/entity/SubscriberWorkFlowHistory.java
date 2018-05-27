package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "subscriber_wkf_history")
public class SubscriberWorkFlowHistory extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "subscriber_wkf_history_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_wkf_hist_seq")
	@SequenceGenerator(allocationSize = 1, name = "subscriber_wkf_hist_seq", sequenceName = "subscriber_wkf_hist_seq")
	private Integer subscriberWorkFlowHistoryId;

	/*@Column(name = "subscriber_id", nullable = false)
	private Long subscriberId;*/
	/*@Column(name = "subscriber_details_id", nullable = false)
	private Long subscriberDetailsId;*/
		
	@Column(name = "user_id")
	private Integer userId;
		
	@Column(name = "action")
	private String action;
	
	@Column(name = "role_name")
	private String roleName;

	@Column(name = "subscriber_work_flow_id")
	private Integer subscriberWorkFlowId;
	
	public Integer getSubscriberWorkFlowHistoryId() {
		return subscriberWorkFlowHistoryId;
	}

	public void setSubscriberWorkFlowHistoryId(Integer subscriberWorkFlowHistoryId) {
		this.subscriberWorkFlowHistoryId = subscriberWorkFlowHistoryId;
	}

	/*public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}*/
	

	public String getAction() {
		return action;
	}

/*	public Long getSubscriberDetailsId() {
		return subscriberDetailsId;
	}

	public void setSubscriberDetailsId(Long subscriberDetailsId) {
		this.subscriberDetailsId = subscriberDetailsId;
	}
*/
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

	public Integer getSubscriberWorkFlowId() {
		return subscriberWorkFlowId;
	}

	public void setSubscriberWorkFlowId(Integer subscriberWorkFlowId) {
		this.subscriberWorkFlowId = subscriberWorkFlowId;
	}
}