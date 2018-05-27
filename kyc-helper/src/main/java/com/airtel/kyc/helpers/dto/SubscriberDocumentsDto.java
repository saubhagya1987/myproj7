package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriberDocumentsDto extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer subscriberDocumentId;

	private String documentName;

	private String documentType;

	private String documentNoType;

	private String documentNo;

	private String documentImageData;

	private String documentImage;

	private String documentImagePath;

	private Integer isOldIdDetails;

	public String getDocumentImageData() {
		return documentImageData;
	}

	public void setDocumentImageData(String documentImageData) {
		this.documentImageData = documentImageData;
	}

	public Integer getSubscriberDocumentId() {
		return subscriberDocumentId;
	}

	public void setSubscriberDocumentId(Integer subscriberDocumentId) {
		this.subscriberDocumentId = subscriberDocumentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNoType() {
		return documentNoType;
	}

	public void setDocumentNoType(String documentNoType) {
		this.documentNoType = documentNoType;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getDocumentImage() {
		return documentImage;
	}

	public void setDocumentImage(String documentImage) {
		this.documentImage = documentImage;
	}

	public String getDocumentImagePath() {
		return documentImagePath;
	}

	public void setDocumentImagePath(String documentImagePath) {
		this.documentImagePath = documentImagePath;
	}

	public Integer getIsOldIdDetails() {
		return isOldIdDetails;
	}

	public void setIsOldIdDetails(Integer isOldIdDetails) {
		this.isOldIdDetails = isOldIdDetails;
	}

}