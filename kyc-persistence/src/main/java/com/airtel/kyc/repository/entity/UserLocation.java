/*package com.airtel.kyc.repository.entity;

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
public class UserLocation extends BaseEntity implements Serializable {

	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "location_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
	@SequenceGenerator(allocationSize = 1, name = "location_seq", sequenceName = "location_seq")
	private Integer locationId;
	
	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Column(name = "REGION_ID", nullable = false)
	private Integer regionId;

	@Column(name = "TERRITORY_ID", nullable = false)
	private Integer territoryId;

	@Column(name = "DISTRICT_ID")
	private Integer districtId;	

	@Column(name = "is_old", columnDefinition = "char default 0")
	private Integer isOldRecord = 0;

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(Integer territoryId) {
		this.territoryId = territoryId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getIsOldRecord() {
		return isOldRecord;
	}

	public void setIsOldRecord(Integer isOldRecord) {
		this.isOldRecord = isOldRecord;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
*/