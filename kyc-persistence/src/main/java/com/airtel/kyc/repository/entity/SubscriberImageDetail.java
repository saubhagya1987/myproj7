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
@Table(name = "subscriber_image_details")
@NamedQueries({
	@NamedQuery(name = "SubscriberImageDetail.findBySubscriberDetailsIdAndIsOldImageDetails", query = " Select s from SubscriberImageDetail s where subscriberDetails.subscriberDetailsId=:subscriberDetailsId and isOldImageDetails=:isOldImageDetails")	
	
})
public class SubscriberImageDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "image_id")
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_image_seq")
	@SequenceGenerator(allocationSize = 1,name = "subscriber_image_seq", sequenceName = "subscriber_image_seq")
	private Integer imageId;

	@Column(name = "image_type")
	private String imageType;

	@Column(name = "image_resolution") // 500ppi+/-10ppi
	private String imageResolution;

	@Column(name = "image_sap_level") // 10
	private String imageSapLevel;

	@Column(name = "image_grey_level") // 256
	private String imageGreyLevel;

	@Column(name = "image_height") // 0.8"
	private String imageHeight;

	@Column(name = "image_width") // 1.0"
	private String imageWidth;

	@Column(name = "image_area")
	private String imageArea;

	@Column(name = "image_compression_algo") // WSQ
	private String imageCompressionAlgo;

	@Column(name = "image_compression_ratio") // 10:01
	private String imageCompressionRatio;

	@Column(name = "image_sencor_certification") // PIV
	private String imageSencorCertification;

	@Column(name = "image_nfiq_level") // 1-3
	private String imageNfiqLevel;

	@Column(name = "image_pupils_distance") // 64px
	private String imagePupilsDistance;

	@Column(name = "image_bit_depth") // 15bits
	private String imageBitDepth;

	@Column(name = "image_format") // PNG,PNG
	private String imageFormat;

	@Column(name = "image_size") // 100kb
	private String imageSize;

	@Column(name = "image_path") 
	private String imagePath;
	
	@Column(name = "is_old_image_details", columnDefinition = "char default 0")
	private Integer isOldImageDetails;
		
	@JsonBackReference("subscriberImageDetail")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subscriber_details_id")
	private SubscriberDetails subscriberDetails;
	
	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageResolution() {
		return imageResolution;
	}

	public void setImageResolution(String imageResolution) {
		this.imageResolution = imageResolution;
	}

	public String getImageSapLevel() {
		return imageSapLevel;
	}

	public void setImageSapLevel(String imageSapLevel) {
		this.imageSapLevel = imageSapLevel;
	}

	public String getImageGreyLevel() {
		return imageGreyLevel;
	}

	public void setImageGreyLevel(String imageGreyLevel) {
		this.imageGreyLevel = imageGreyLevel;
	}

	public String getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(String imageHeight) {
		this.imageHeight = imageHeight;
	}

	public String getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(String imageWidth) {
		this.imageWidth = imageWidth;
	}

	public String getImageArea() {
		return imageArea;
	}

	public void setImageArea(String imageArea) {
		this.imageArea = imageArea;
	}

	public String getImageCompressionAlgo() {
		return imageCompressionAlgo;
	}

	public void setImageCompressionAlgo(String imageCompressionAlgo) {
		this.imageCompressionAlgo = imageCompressionAlgo;
	}

	public String getImageCompressionRatio() {
		return imageCompressionRatio;
	}

	public void setImageCompressionRatio(String imageCompressionRatio) {
		this.imageCompressionRatio = imageCompressionRatio;
	}

	public String getImageSencorCertification() {
		return imageSencorCertification;
	}

	public void setImageSencorCertification(String imageSencorCertification) {
		this.imageSencorCertification = imageSencorCertification;
	}

	public String getImageNfiqLevel() {
		return imageNfiqLevel;
	}

	public void setImageNfiqLevel(String imageNfiqLevel) {
		this.imageNfiqLevel = imageNfiqLevel;
	}

	public String getImagePupilsDistance() {
		return imagePupilsDistance;
	}

	public void setImagePupilsDistance(String imagePupilsDistance) {
		this.imagePupilsDistance = imagePupilsDistance;
	}

	public String getImageBitDepth() {
		return imageBitDepth;
	}

	public void setImageBitDepth(String imageBitDepth) {
		this.imageBitDepth = imageBitDepth;
	}

	public String getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(String imageFormat) {
		this.imageFormat = imageFormat;
	}

	public String getImageSize() {
		return imageSize;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	public SubscriberDetails getSubscriberDetails() {
		return subscriberDetails;
	}

	public void setSubscriberDetails(SubscriberDetails subscriberDetails) {
		this.subscriberDetails = subscriberDetails;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getIsOldImageDetails() {
		return isOldImageDetails;
	}

	public void setIsOldImageDetails(Integer isOldImageDetails) {
		this.isOldImageDetails = isOldImageDetails;
	}
	

}
