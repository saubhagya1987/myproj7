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
@Table(name = "user_village")
@NamedQueries({
	
	@NamedQuery(name = "UserVillage.findUserById", query = " select u from UserVillage u where userId=:userId ") ,
	
})

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="userVillage")
public class UserVillage extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_village_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_village_seq")
	@SequenceGenerator(allocationSize = 1, name = "user_village_seq", sequenceName = "user_village_seq")
	private Integer userVillageId;
	
	@Column(name = "user_district_id", nullable = false)
	private Integer userDistrictId;	
	
	@Column(name = "village_id", nullable = false)
	private Integer villageId;
	
	@Column(name = "user_id")
	private Integer userId;
	
	public UserVillage(){
		
	}

	public Integer getUserVillageId() {
		return userVillageId;
	}

	public void setUserVillageId(Integer userVillageId) {
		this.userVillageId = userVillageId;
	}

	public Integer getUserDistrictId() {
		return userDistrictId;
	}

	public void setUserDistrictId(Integer userDistrictId) {
		this.userDistrictId = userDistrictId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

}
