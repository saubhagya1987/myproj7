package com.airtel.user.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "user_id_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="userIdDetails")
public class UserIdDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_no")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_no_seq")
	@SequenceGenerator(allocationSize = 1,name = "id_no_seq", sequenceName = "id_no_seq")
	private Integer idNo;

	@Column(name = "id_type")
	private String idType;

	@Column(name = "id_image_name")
	private String idImageName;

	// @Lob
	@Transient
	@Column(name = "id_image")
	private String idImage;

	@Column(name = "id_expiry_date")
	private String idExpiryDate;

	@Column(name = "id_issue_date")
	private String idIssueDate;

	@Column(name = "id_issue_country")
	private String idIssueCountry;

	@Column(name = "id_path")
	private String idPath;
	
	@Column(name = "is_old_id_details", columnDefinition = "char default 0")
	private Integer isOldIdDetails;

	@JsonBackReference("userIdDetail")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;

	public Integer getIdNo() {
		return idNo;
	}

	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdImageName() {
		return idImageName;
	}

	public void setIdImageName(String idImageName) {
		this.idImageName = idImageName;
	}

	public String getIdImage() {
		return idImage;
	}

	public void setIdImage(String idImage) {
		this.idImage = idImage;
	}

	public String getIdExpiryDate() {
		return idExpiryDate;
	}

	public void setIdExpiryDate(String idExpiryDate) {
		this.idExpiryDate = idExpiryDate;
	}

	public String getIdIssueDate() {
		return idIssueDate;
	}

	public void setIdIssueDate(String idIssueDate) {
		this.idIssueDate = idIssueDate;
	}

	public String getIdIssueCountry() {
		return idIssueCountry;
	}

	public void setIdIssueCountry(String idIssueCountry) {
		this.idIssueCountry = idIssueCountry;
	}

	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public Integer getIsOldIdDetails() {
		return isOldIdDetails;
	}

	public void setIsOldIdDetails(Integer isOldIdDetails) {
		this.isOldIdDetails = isOldIdDetails;
	}

	
}
