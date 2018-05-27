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
@Table(name = "user_province")
@NamedQueries({

	
	@NamedQuery(name = "UserProvince.findUserById", query = " select u from UserProvince u where userId=:userId ") ,
	
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="userProvince")
public class UserProvince extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	@Id
	@Column(name = "user_province_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_province_seq")
	@SequenceGenerator(allocationSize = 1, name = "user_province_seq", sequenceName = "user_province_seq")
	private Integer userProvinceId;	
	
	@Column(name = "province_id", nullable = false)
	private Integer provinceId;
	
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	
	
	public UserProvince(){
		
	}

	public Integer getUserProvinceId() {
		return userProvinceId;
	}

	public void setUserProvinceId(Integer userProvinceId) {
		this.userProvinceId = userProvinceId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	

}
