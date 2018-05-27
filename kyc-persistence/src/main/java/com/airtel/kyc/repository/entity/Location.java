package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.airtel.kyc.repository.listener.AuditedEntityListener;



@Entity
@Table(name = "user_location")
@EntityListeners(AuditedEntityListener.class)
public class Location extends BaseEntity implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "location_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
	@SequenceGenerator(allocationSize = 1, name = "location_seq", sequenceName = "location_seq")
	private Integer locationId;

	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "zone_id")
	private Integer zoneId;

	@Column(name = "branch_id")
	private Integer branchId;

	@Column(name = "subscriber_id")
	private Long subscriberId;

	@Column(name = "cluster_id")
	private Integer clusterId;

	@Column(name = "site_id")
	private Integer siteId;

	@Column(name = "is_old", columnDefinition = "char default 0")
	private Integer isOldRecord = 0;
	
	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Integer getIsOldRecord() {
		return isOldRecord;
	}

	public void setIsOldRecord(Integer isOldRecord) {
		this.isOldRecord = isOldRecord;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
}
