package com.airtel.kyc.helpers.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriberDetailsDto extends BaseDto implements Comparable<SubscriberDetailsDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer subscriberDetailsId;

	private String firstName;

	private String middleName;

	private String lastName;

	private String email;

	private String gender;
	
	private Timestamp updatedOn;

	//private Timestamp dateOfBirth;
	private String dateOfBirth;

	private String postalAddress;

	private String companyName;

	private String contactDetails;
	
	private String isMinor;
	
	private String proxyForRegistration;	
	

	private String amAccount;
	
	private String isImageEdited;
	
    
	private String channelPartnerCellId;	
	
	private String latitude;	
	
	private String longitude;	
	
	private String permissableRadius;
	
	private String deviceId;
	
	
    private String nationality;	
	private String caseType;
	
	private String msisdn;
	
	//@NotNull(message = "Alternate Mobile Number is mandatory")
	//@Size(min=9, max=9, message = "{mobile.number.validation}")
	private String alternateNo;
	private String physicalFormStatus;
	
	private String occupation;
	
	private String minorName;
	
	private Integer isLatest;
	
	private String fingerPrint;
	
	private Integer isOldUserDetails;
	
	
	private String submitedOn;
	
	private String syncedOn;
	
	private String registeredOn;
	
	private String placeOfBirth;
	
	private String onlineOfflineFlag;
	
	//@NotNull(message = "Sim Serial Number is mandatory")
	//@Size(min=5, max=5, message = "{sim.serial.validation}")
	private String SimSerialNumber;
	
	private Integer bulkSubscriberFlag;
	
	
	private String subscriberType;
	
	private String imei;
	
	private String caseAssignedNumber;
	
	private String userName;
	
	private String finalStatus;
	
	private Set<SubscriberIdDetailsDto> subscriberIdDetails;

	private Set<SubscriberImageDetailDto> subscriberImageDetail;

	private List<SubscriberDocumentsDto> subscriberDocuments;
	
	private SubscriberWorkFlowDto subscriberWorkFlow;

	

	private AddressDto address;

	public List<SubscriberDocumentsDto> getSubscriberDocuments() {
		return subscriberDocuments;
	}

	public void setSubscriberDocuments(List<SubscriberDocumentsDto> subscriberDocuments) {
		this.subscriberDocuments = subscriberDocuments;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public Integer getSubscriberDetailsId() {
		return subscriberDetailsId;
	}

	public void setSubscriberDetailsId(Integer subscriberDetailsId) {
		this.subscriberDetailsId = subscriberDetailsId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public Set<SubscriberIdDetailsDto> getSubscriberIdDetails() {
		return subscriberIdDetails;
	}

	public void setSubscriberIdDetails(Set<SubscriberIdDetailsDto> subscriberIdDetails) {
		this.subscriberIdDetails = subscriberIdDetails;
	}

	public Set<SubscriberImageDetailDto> getSubscriberImageDetail() {
		return subscriberImageDetail;
	}

	public void setSubscriberImageDetail(Set<SubscriberImageDetailDto> subscriberImageDetail) {
		this.subscriberImageDetail = subscriberImageDetail;
	}

	/*public NidaDetailsDto getNidaDetails() {
		return nidaDetails;
	}

	public void setNidaDetails(NidaDetailsDto nidaDetails) {
		this.nidaDetails = nidaDetails;
	}*/

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getIsMinor() {
		return isMinor;
	}

	public void setIsMinor(String isMinor) {
		this.isMinor = isMinor;
	}

	public String getAmAccount() {
		return amAccount;
	}

	public void setAmAccount(String amAccount) {
		this.amAccount = amAccount;
	}

	public String getChannelPartnerCellId() {
		return channelPartnerCellId;
	}

	public void setChannelPartnerCellId(String channelPartnerCellId) {
		this.channelPartnerCellId = channelPartnerCellId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPermissableRadius() {
		return permissableRadius;
	}

	public void setPermissableRadius(String permissableRadius) {
		this.permissableRadius = permissableRadius;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getAlternateNo() {
		return alternateNo;
	}

	public void setAlternateNo(String alternateNo) {
		this.alternateNo = alternateNo;
	}

	public String getPhysicalFormStatus() {
		return physicalFormStatus;
	}

	public void setPhysicalFormStatus(String physicalFormStatus) {
		this.physicalFormStatus = physicalFormStatus;
	}

	public SubscriberWorkFlowDto getSubscriberWorkFlow() {
		return subscriberWorkFlow;
	}

	public void setSubscriberWorkFlow(SubscriberWorkFlowDto subscriberWorkFlow) {
		this.subscriberWorkFlow = subscriberWorkFlow;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}


	@Override
	public int compareTo(SubscriberDetailsDto o) {
		// TODO Auto-generated method stub
		return o.getUpdatedOn().compareTo(this.getUpdatedOn());
	}

	public String getMinorName() {
		return minorName;
	}

	public void setMinorName(String minorName) {
		this.minorName = minorName;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIsLatest() {
		return isLatest;
	}

	public void setIsLatest(Integer isLatest) {
		this.isLatest = isLatest;
	}

	public String getIsImageEdited() {
		return isImageEdited;
	}

	public void setIsImageEdited(String isImageEdited) {
		this.isImageEdited = isImageEdited;
	}

	public Integer getIsOldUserDetails() {
		return isOldUserDetails;
	}

	public void setIsOldUserDetails(Integer isOldUserDetails) {
		this.isOldUserDetails = isOldUserDetails;
	}

	public String getFingerPrint() {
		return fingerPrint;
	}

	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

	public String getSubmitedOn() {
		return submitedOn;
	}

	public void setSubmitedOn(String submitedOn) {
		this.submitedOn = submitedOn;
	}

	public String getSyncedOn() {
		return syncedOn;
	}

	public void setSyncedOn(String syncedOn) {
		this.syncedOn = syncedOn;
	}

	public String getOnlineOfflineFlag() {
		return onlineOfflineFlag;
	}

	public void setOnlineOfflineFlag(String onlineOfflineFlag) {
		this.onlineOfflineFlag = onlineOfflineFlag;
	}

	public String getProxyForRegistration() {
		return proxyForRegistration;
	}

	public void setProxyForRegistration(String proxyForRegistration) {
		this.proxyForRegistration = proxyForRegistration;
	}

	public String getSimSerialNumber() {
		return SimSerialNumber;
	}

	public void setSimSerialNumber(String simSerialNumber) {
		SimSerialNumber = simSerialNumber;
	}

	public String getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(String registeredOn) {
		this.registeredOn = registeredOn;
	}

	public Integer getBulkSubscriberFlag() {
		return bulkSubscriberFlag;
	}

	public void setBulkSubscriberFlag(Integer bulkSubscriberFlag) {
		this.bulkSubscriberFlag = bulkSubscriberFlag;
	}

	public String getSubscriberType() {
		return subscriberType;
	}

	public void setSubscriberType(String subscriberType) {
		this.subscriberType = subscriberType;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	public String getCaseAssignedNumber() {
		return caseAssignedNumber;
	}

	public void setCaseAssignedNumber(String caseAssignedNumber) {
		this.caseAssignedNumber = caseAssignedNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	

}
