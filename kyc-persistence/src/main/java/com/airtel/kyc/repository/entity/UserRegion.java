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
@Table(name = "user_region")
public class UserRegion extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_region_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_region_seq")
	@SequenceGenerator(allocationSize = 1, name = "user_region_seq", sequenceName = "user_region_seq")
	private Integer userRegionId;
	
	@Column(name = "user_id", nullable = false)
	private Integer userId;

	@Column(name = "REGION_ID", nullable = false)
	private Integer regionId;
	
	@Column(name = "is_old_record", columnDefinition = "char default 0")
	private Integer isOldRecord = 0;
	
	public  UserRegion(){
		
	}

	public Integer getUserRegionId() {
		return userRegionId;
	}

	public void setUserRegionId(Integer userRegionId) {
		this.userRegionId = userRegionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userRegionId == null) ? 0 : userRegionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRegion other = (UserRegion) obj;
		if (userRegionId == null) {
			if (other.userRegionId != null)
				return false;
		} else if (!userRegionId.equals(other.userRegionId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRegion [userRegionId=" + userRegionId + ", userId=" + userId + ", regionId=" + regionId + "]";
	}

	public Integer getIsOldRecord() {
		return isOldRecord;
	}

	public void setIsOldRecord(Integer isOldRecord) {
		this.isOldRecord = isOldRecord;
	}

	

}
