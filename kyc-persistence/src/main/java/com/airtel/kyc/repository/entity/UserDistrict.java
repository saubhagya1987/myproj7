package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "user_district")
@NamedQueries({
	
	@NamedQuery(name = "UserDistrict.findUserById", query = " select u from UserDistrict u where userId=:userId ") ,
	
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="userDistrict")
public class UserDistrict extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_district_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_district_seq")
	@SequenceGenerator(allocationSize = 1, name = "user_district_seq", sequenceName = "user_district_seq")
	private Integer userDistrictId;
	
	@Column(name = "user_province_id", nullable = false)
	private Integer userProvinceId;	
	
	@Column(name = "district_id", nullable = false)
	private Integer districtId;
	
	@Column(name = "user_id")
	private Integer userId;
	
	public UserDistrict(){
		
	}

	public Integer getUserDistrictId() {
		return userDistrictId;
	}

	public void setUserDistrictId(Integer userDistrictId) {
		this.userDistrictId = userDistrictId;
	}

	
	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public Integer getUserProvinceId() {
		return userProvinceId;
	}

	public void setUserProvinceId(Integer userProvinceId) {
		this.userProvinceId = userProvinceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

}
