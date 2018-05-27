package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "subscriber_id_details")
@NamedQueries({
	@NamedQuery(name = "SubscriberIdDetails.findBySubscriberDetailsIdAndIsOldIdDetails", query = " Select s from SubscriberIdDetails s where subscriberDetails.subscriberDetailsId=:subscriberDetailsId and isOldIdDetails=:isOldIdDetails"),
	@NamedQuery(name = "SubscriberIdDetails.findByIdNumber", query = " Select s from SubscriberIdDetails s where  idNumber=:idNumber and isOldIdDetails=:isOldIdDetails")
	
})
public class SubscriberIdDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_no")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_id_no_seq")
	@SequenceGenerator(allocationSize = 1,name = "subscriber_id_no_seq", sequenceName = "subscriber_id_no_seq")
	private Integer idNo;

	@Column(name = "id_type")
	private String idType;

	@Column(name = "id_expiry_date")
	private String idExpiryDate;

	@Column(name = "id_issue_date")
	private String idIssueDate;

	@Column(name = "id_issue_country")
	private String idIssueCountry;

	@Column(name = "id_path")
	private String idPath;
	
	@Column(name="id_number")
	private String idNumber;
	
	@Column(name="id_image_front")
	private String idImageFront;
	
	@Column(name="id_image_back")
	private String idImageBack;
	
	@Column(name="subscriber_image_id")
	private String subscriberImageId;
	
	@Column(name="physical_form_id")
	private String physicalFormId;
	
	@Column(name = "is_old_id_details", columnDefinition = "char default 0")
	private Integer isOldIdDetails;
	
	@JsonBackReference("subscriberIdDetails")
	@Fetch(FetchMode.SELECT)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subscriber_details_id")
	private SubscriberDetails subscriberDetails;

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

	public SubscriberDetails getSubscriberDetails() {
		return subscriberDetails;
	}

	public void setSubscriberDetails(SubscriberDetails subscriberDetails) {
		this.subscriberDetails = subscriberDetails;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdImageFront() {
		return idImageFront;
	}

	public void setIdImageFront(String idImageFront) {
		this.idImageFront = idImageFront;
	}

	public String getIdImageBack() {
		return idImageBack;
	}

	public void setIdImageBack(String idImageBack) {
		this.idImageBack = idImageBack;
	}

	public Integer getIsOldIdDetails() {
		return isOldIdDetails;
	}

	public void setIsOldIdDetails(Integer isOldIdDetails) {
		this.isOldIdDetails = isOldIdDetails;
	}

	public String getSubscriberImageId() {
		return subscriberImageId;
	}

	public void setSubscriberImageId(String subscriberImageId) {
		this.subscriberImageId = subscriberImageId;
	}

	public String getPhysicalFormId() {
		return physicalFormId;
	}

	public void setPhysicalFormId(String physicalFormId) {
		this.physicalFormId = physicalFormId;
	}
	
}
