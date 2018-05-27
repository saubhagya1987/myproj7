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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "subscriber_document")
@NamedQueries({
	@NamedQuery(name = "SubscriberDocuments.findBySubscriberDetailsIdAndIsOldIdDetails", query = " Select s from SubscriberDocuments s where subscriberDetails.subscriberDetailsId=:subscriberDetailsId and isOldIdDetails=:isOldIdDetails")	
	
})
public class SubscriberDocuments extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "subscriber_document_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_document_seq")
	@SequenceGenerator(allocationSize = 1, name = "subscriber_document_seq", sequenceName = "subscriber_document_seq")
	private Integer subscriberDocumentId;

	@Column(name = "document_name")
	private String documentName;

	@Column(name = "document_type")
	private String documentType;

	@Column(name = "document_no_type")
	private String documentNoType;

	@Column(name = "document_no")
	private String documentNo;

	@Column(name = "document_image")
	private String documentImage;

	@Column(name = "document_image_path")
	private String documentImagePath;

	@Column(name = "is_old_id_details", columnDefinition = "char default 0")
	private Integer isOldIdDetails;

	@JsonBackReference("subscriberDocuments")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_details_id")
	private SubscriberDetails subscriberDetails;
	
	

	public SubscriberDetails getSubscriberDetails() {
		return subscriberDetails;
	}

	public void setSubscriberDetails(SubscriberDetails subscriberDetails) {
		this.subscriberDetails = subscriberDetails;
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