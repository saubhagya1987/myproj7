package com.airtel.kyc.helpers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriberIdDetailsDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer idNo;

	private String idType;

	private String idImageName;

	private String idExpiryDate;

	private String idIssueDate;

	private String idIssueCountry;

	private String idPath;

	private String idNumber;

	private String idImageFront;

	private String idImageBack;

	private String idImageFrontData;

	private String idImageBackData;
	
	private String subscriberImageId;
	
	private String physicalFormId;

	public String getIdImageFrontData() {
		return idImageFrontData;
	}

	public void setIdImageFrontData(String idImageFrontData) {
		this.idImageFrontData = idImageFrontData;
	}

	public String getIdImageBackData() {
		return idImageBackData;
	}

	public void setIdImageBackData(String idImageBackData) {
		this.idImageBackData = idImageBackData;
	}

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
