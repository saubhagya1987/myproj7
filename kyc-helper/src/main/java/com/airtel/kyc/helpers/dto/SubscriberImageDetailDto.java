package com.airtel.kyc.helpers.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriberImageDetailDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer imageId;

	private String imageType;

	private String imageResolution;

	private String imageSapLevel;

	private String imageGreyLevel;

	private String imageHeight;

	private String imageWidth;

	private String imageArea;

	private String imageCompressionAlgo;

	private String imageCompressionRatio;

	private String imageSencorCertification;

	private String imageNfiqLevel;

	private String imagePupilsDistance;

	private String imageBitDepth;

	private String imageFormat;

	private String imageSize;

	private String imageStr;

	private String imagePath;

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

	public String getImageStr() {
		return imageStr;
	}

	public void setImageStr(String imageStr) {
		this.imageStr = imageStr;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
