package com.airtel.kyc.helpers.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.poi.ss.formula.functions.Address;

import com.airtel.user.helper.dto.LocationDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriberDto extends BaseDto implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long subscriberId;

	@NotNull(message = "Mobile Number is mandatory")
	@Size(min=9, max=9, message = "{mobile.number.validation}")
	private String msisdn;
	
	private String newMsisdn;
	
	private String alternateNo;

	//@NotNull(message = "Sim Serial Number is mandatory")
	//@Size(min=5, max=5, message = "{sim.serial.validation}")
	private String simSerialNumber;

	private Integer parentSubscriberId;

	private String registrationType; 

	private Timestamp activationTime;

	private String kycTansactionId;
	
	private String prevKycTansactionId;

	private String registrationFrom;

	private String msisdnType;

	private String apiActivationTime;
	
	private String locationText;
	
	private String ipAddress;
	
	private String usersMsisdn;

	private List<Data> data; 
	
	@NotNull
	@Valid
	private List<SubscriberDetailsDto> subscriberDetails;
	
	private List<SubscriberDetailsDto> subscriberDetail;

	private OperactionOnSubscriberDto operactionOnSubscriber;

	private List<ActionOnSubscriberDto> actionOnSubscriber;

	//private SubscriberWorkFlowDto subscriberWorkFlow;

	private String createdBy;

	private boolean isUser;

	//private Integer isOldSubscriber;

	private String registrationMode;

	private String registrationProcess;

	private String partialActivationTime;

	private Timestamp rejectionTime;

	private String finalStatus;

	private String finalStatusReason;

	private Timestamp amActivationTime;

	private Timestamp svActivationTime;

	private Timestamp biActivationTime;

	private Timestamp emaActivationTime;

	private String subscriberRoles;

	private String simSerialNumberPrefix;

	private String msisdnPrefix;
	
	
	private LocationDto location;
	private String sourceChannelType;
	private String creatorEmailId;
	private String teamLeadMSISDN;
	private String deviceCategory;
	
    private String message;	
	private String otp;
	
	private String idImageFrontData;
	
	private String physicalFormId;
	

	
	private Integer subscriberDetailsId;

	
	private String firstName;

	
	private String middleName;

	
	private String lastName;

	
	private String email;

	
	private String gender;

	
	private Date dateOfBirth;


	private String postalAddress;
	
	private String companyName;
	
	private String contactDetails;
	
	
	private Integer isMinor;
	
	
	private Integer amAccount;
	
	
	private String channelPartnerCellId;	
	
	
	private String latitude;	
	
	
	private String longitude;	
	
	
	private String permissableRadius;	
	
	private String deviceId;
	
	private Integer isOldUserDetails;	
	
	private String imsiNumber;
	
	private String registationFrom;
	
	private Timestamp hpActivationTime;
	
	private String caseType;
	
	private String kycMode;		
	
	private String language;
	
	private String workFlowStatus;	
	
	private String workFlowReason;
	
	private String nationality;
	
	
	private Integer isPhysicalFormReceived;
	
	
	private String physicalFormStatus;

	
	private Integer blacklistFlag;
	
	
	private Integer hotlineFlag;	
	
	
	
	
	private String barringSource;
	
	
	private String occupation;

	
	private String minorName;

	
	private Integer isLatest;

	//new columns added from subscriber table end

	private SubscriberDto subscriber;

	private Set<SubscriberDetailsDto> subscriberIdDetails;
	
	
	private Set<SubscriberImageDetailDto> subscriberImageDetail;
	
	private List<SubscriberDocumentsDto> subscriberDocuments;

	private Address address;

	private SubscriberWorkFlowDto subscriberWorkFlow;
	
	
	//Bi Data
	private String first_name;
	private String last_name;
	private String date_of_birth;
	private String district_name;
	private String country_name;
	private String gsm_reg_status;
	private String am_reg_status;
	private String territory;
	private String region;
	private String site_id;
	private String id_type;
	private String id_number;
	//private String gender;
	private String physical_address;
	private String is_minor;
	//private String latitude;
	//private String longitude;
	private String channel_partner_cell_id;
	private String timestamp;
	private String agent_auuid;
	private String agent_msisdn;
	private String agent_imei;
	private String agent_fname;
	private String agent_lname;
	private String cp_type;
	
	//private Integer countryId;	

	/*public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}*/
	
	public Long getSubscriberId() {
		return subscriberId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getSimSerialNumber() {
		return simSerialNumber;
	}

	public void setSimSerialNumber(String simSerialNumber) {
		this.simSerialNumber = simSerialNumber;
	}

	public Integer getParentSubscriberId() {
		return parentSubscriberId;
	}

	public void setParentSubscriberId(Integer parentSubscriberId) {
		this.parentSubscriberId = parentSubscriberId;
	}

	public String getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}

	public Timestamp getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Timestamp activationTime) {
		this.activationTime = activationTime;
	}

	public String getKycTansactionId() {
		return kycTansactionId;
	}

	public void setKycTansactionId(String kycTansactionId) {
		this.kycTansactionId = kycTansactionId;
	}

	public String getRegistrationFrom() {
		return registrationFrom;
	}

	public void setRegistrationFrom(String registrationFrom) {
		this.registrationFrom = registrationFrom;
	}

	public String getMsisdnType() {
		return msisdnType;
	}

	public void setMsisdnType(String msisdnType) {
		this.msisdnType = msisdnType;
	}

	public String getApiActivationTime() {
		return apiActivationTime;
	}

	public void setApiActivationTime(String apiActivationTime) {
		this.apiActivationTime = apiActivationTime;
	}

	public List<SubscriberDetailsDto> getSubscriberDetails() {
		return subscriberDetails;
	}

	public void setSubscriberDetails(List<SubscriberDetailsDto> subscriberDetails) {
		this.subscriberDetails = subscriberDetails;
	}

	public OperactionOnSubscriberDto getOperactionOnSubscriber() {
		return operactionOnSubscriber;
	}

	public void setOperactionOnSubscriber(OperactionOnSubscriberDto operactionOnSubscriber) {
		this.operactionOnSubscriber = operactionOnSubscriber;
	}

	public List<ActionOnSubscriberDto> getActionOnSubscriber() {
		return actionOnSubscriber;
	}

	public void setActionOnSubscriber(List<ActionOnSubscriberDto> actionOnSubscriber) {
		this.actionOnSubscriber = actionOnSubscriber;
	}

/*	public SubscriberWorkFlowDto getSubscriberWorkFlow() {
		return subscriberWorkFlow;
	}

	public void setSubscriberWorkFlow(SubscriberWorkFlowDto subscriberWorkFlow) {
		this.subscriberWorkFlow = subscriberWorkFlow;
	}*/

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	/*public Integer getIsOldSubscriber() {
		return isOldSubscriber;
	}

	public void setIsOldSubscriber(Integer isOldSubscriber) {
		this.isOldSubscriber = isOldSubscriber;
	}*/

	public String getRegistrationMode() {
		return registrationMode;
	}

	public void setRegistrationMode(String registrationMode) {
		this.registrationMode = registrationMode;
	}

	public String getRegistrationProcess() {
		return registrationProcess;
	}

	public void setRegistrationProcess(String registrationProcess) {
		this.registrationProcess = registrationProcess;
	}

	public String getPartialActivationTime() {
		return partialActivationTime;
	}

	public void setPartialActivationTime(String partialActivationTime) {
		this.partialActivationTime = partialActivationTime;
	}

	public Timestamp getRejectionTime() {
		return rejectionTime;
	}

	public void setRejectionTime(Timestamp rejectionTime) {
		this.rejectionTime = rejectionTime;
	}

	public String getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(String finalStatus) {
		this.finalStatus = finalStatus;
	}

	public String getFinalStatusReason() {
		return finalStatusReason;
	}

	public void setFinalStatusReason(String finalStatusReason) {
		this.finalStatusReason = finalStatusReason;
	}

	public Timestamp getAmActivationTime() {
		return amActivationTime;
	}

	public void setAmActivationTime(Timestamp amActivationTime) {
		this.amActivationTime = amActivationTime;
	}

	public Timestamp getSvActivationTime() {
		return svActivationTime;
	}

	public void setSvActivationTime(Timestamp svActivationTime) {
		this.svActivationTime = svActivationTime;
	}

	public Timestamp getBiActivationTime() {
		return biActivationTime;
	}

	public void setBiActivationTime(Timestamp biActivationTime) {
		this.biActivationTime = biActivationTime;
	}

	public Timestamp getEmaActivationTime() {
		return emaActivationTime;
	}

	public void setEmaActivationTime(Timestamp emaActivationTime) {
		this.emaActivationTime = emaActivationTime;
	}

	public String getSubscriberRoles() {
		return subscriberRoles;
	}

	public void setSubscriberRoles(String subscriberRoles) {
		this.subscriberRoles = subscriberRoles;
	}

	public String getSimSerialNumberPrefix() {
		return simSerialNumberPrefix;
	}

	public void setSimSerialNumberPrefix(String simSerialNumberPrefix) {
		this.simSerialNumberPrefix = simSerialNumberPrefix;
	}

	public String getMsisdnPrefix() {
		return msisdnPrefix;
	}

	public void setMsisdnPrefix(String msisdnPrefix) {
		this.msisdnPrefix = msisdnPrefix;
	}

	public LocationDto getLocation() {
		return location;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}

	public String getSourceChannelType() {
		return sourceChannelType;
	}

	public void setSourceChannelType(String sourceChannelType) {
		this.sourceChannelType = sourceChannelType;
	}

	public String getCreatorEmailId() {
		return creatorEmailId;
	}

	public void setCreatorEmailId(String creatorEmailId) {
		this.creatorEmailId = creatorEmailId;
	}

	public String getTeamLeadMSISDN() {
		return teamLeadMSISDN;
	}

	public void setTeamLeadMSISDN(String teamLeadMSISDN) {
		this.teamLeadMSISDN = teamLeadMSISDN;
	}

	public String getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory;
	}

	public String getAlternateNo() {
		return alternateNo;
	}

	public void setAlternateNo(String alternateNo) {
		this.alternateNo = alternateNo;
	}

	public List<SubscriberDetailsDto> getSubscriberDetail() {
		return subscriberDetail;
	}

	public void setSubscriberDetail(List<SubscriberDetailsDto> subscriberDetail) {
		this.subscriberDetail = subscriberDetail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPrevKycTansactionId() {
		return prevKycTansactionId;
	}

	public void setPrevKycTansactionId(String prevKycTansactionId) {
		this.prevKycTansactionId = prevKycTansactionId;
	}

	public String getIdImageFrontData() {
		return idImageFrontData;
	}

	public void setIdImageFrontData(String idImageFrontData) {
		this.idImageFrontData = idImageFrontData;
	}

	public String getPhysicalFormId() {
		return physicalFormId;
	}

	public void setPhysicalFormId(String physicalFormId) {
		this.physicalFormId = physicalFormId;
	}

	public String getNewMsisdn() {
		return newMsisdn;
	}

	public void setNewMsisdn(String newMsisdn) {
		this.newMsisdn = newMsisdn;
	}

	public String getLocationText() {
		return locationText;
	}

	public void setLocationText(String locationText) {
		this.locationText = locationText;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

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

	public Integer getIsMinor() {
		return isMinor;
	}

	public void setIsMinor(Integer isMinor) {
		this.isMinor = isMinor;
	}

	public Integer getAmAccount() {
		return amAccount;
	}

	public void setAmAccount(Integer amAccount) {
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

	public Integer getIsOldUserDetails() {
		return isOldUserDetails;
	}

	public void setIsOldUserDetails(Integer isOldUserDetails) {
		this.isOldUserDetails = isOldUserDetails;
	}

	public String getImsiNumber() {
		return imsiNumber;
	}

	public void setImsiNumber(String imsiNumber) {
		this.imsiNumber = imsiNumber;
	}

	public String getRegistationFrom() {
		return registationFrom;
	}

	public void setRegistationFrom(String registationFrom) {
		this.registationFrom = registationFrom;
	}

	public Timestamp getHpActivationTime() {
		return hpActivationTime;
	}

	public void setHpActivationTime(Timestamp hpActivationTime) {
		this.hpActivationTime = hpActivationTime;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getKycMode() {
		return kycMode;
	}

	public void setKycMode(String kycMode) {
		this.kycMode = kycMode;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getWorkFlowStatus() {
		return workFlowStatus;
	}

	public void setWorkFlowStatus(String workFlowStatus) {
		this.workFlowStatus = workFlowStatus;
	}

	public String getWorkFlowReason() {
		return workFlowReason;
	}

	public void setWorkFlowReason(String workFlowReason) {
		this.workFlowReason = workFlowReason;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getIsPhysicalFormReceived() {
		return isPhysicalFormReceived;
	}

	public void setIsPhysicalFormReceived(Integer isPhysicalFormReceived) {
		this.isPhysicalFormReceived = isPhysicalFormReceived;
	}

	public String getPhysicalFormStatus() {
		return physicalFormStatus;
	}

	public void setPhysicalFormStatus(String physicalFormStatus) {
		this.physicalFormStatus = physicalFormStatus;
	}

	public Integer getBlacklistFlag() {
		return blacklistFlag;
	}

	public void setBlacklistFlag(Integer blacklistFlag) {
		this.blacklistFlag = blacklistFlag;
	}

	public Integer getHotlineFlag() {
		return hotlineFlag;
	}

	public void setHotlineFlag(Integer hotlineFlag) {
		this.hotlineFlag = hotlineFlag;
	}

	public String getBarringSource() {
		return barringSource;
	}

	public void setBarringSource(String barringSource) {
		this.barringSource = barringSource;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getMinorName() {
		return minorName;
	}

	public void setMinorName(String minorName) {
		this.minorName = minorName;
	}

	public Integer getIsLatest() {
		return isLatest;
	}

	public void setIsLatest(Integer isLatest) {
		this.isLatest = isLatest;
	}

	public SubscriberDto getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(SubscriberDto subscriber) {
		this.subscriber = subscriber;
	}

	public Set<SubscriberDetailsDto> getSubscriberIdDetails() {
		return subscriberIdDetails;
	}

	public void setSubscriberIdDetails(Set<SubscriberDetailsDto> subscriberIdDetails) {
		this.subscriberIdDetails = subscriberIdDetails;
	}

	public Set<SubscriberImageDetailDto> getSubscriberImageDetail() {
		return subscriberImageDetail;
	}

	public void setSubscriberImageDetail(Set<SubscriberImageDetailDto> subscriberImageDetail) {
		this.subscriberImageDetail = subscriberImageDetail;
	}

	public List<SubscriberDocumentsDto> getSubscriberDocuments() {
		return subscriberDocuments;
	}

	public void setSubscriberDocuments(List<SubscriberDocumentsDto> subscriberDocuments) {
		this.subscriberDocuments = subscriberDocuments;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public SubscriberWorkFlowDto getSubscriberWorkFlow() {
		return subscriberWorkFlow;
	}

	public void setSubscriberWorkFlow(SubscriberWorkFlowDto subscriberWorkFlow) {
		this.subscriberWorkFlow = subscriberWorkFlow;
	}

	public String getUsersMsisdn() {
		return usersMsisdn;
	}

	public void setUsersMsisdn(String usersMsisdn) {
		this.usersMsisdn = usersMsisdn;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public String getGsm_reg_status() {
		return gsm_reg_status;
	}

	public void setGsm_reg_status(String gsm_reg_status) {
		this.gsm_reg_status = gsm_reg_status;
	}

	public String getAm_reg_status() {
		return am_reg_status;
	}

	public void setAm_reg_status(String am_reg_status) {
		this.am_reg_status = am_reg_status;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getPhysical_address() {
		return physical_address;
	}

	public void setPhysical_address(String physical_address) {
		this.physical_address = physical_address;
	}

	public String getIs_minor() {
		return is_minor;
	}

	public void setIs_minor(String is_minor) {
		this.is_minor = is_minor;
	}

	public String getChannel_partner_cell_id() {
		return channel_partner_cell_id;
	}

	public void setChannel_partner_cell_id(String channel_partner_cell_id) {
		this.channel_partner_cell_id = channel_partner_cell_id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAgent_auuid() {
		return agent_auuid;
	}

	public void setAgent_auuid(String agent_auuid) {
		this.agent_auuid = agent_auuid;
	}

	public String getAgent_msisdn() {
		return agent_msisdn;
	}

	public void setAgent_msisdn(String agent_msisdn) {
		this.agent_msisdn = agent_msisdn;
	}

	public String getAgent_imei() {
		return agent_imei;
	}

	public void setAgent_imei(String agent_imei) {
		this.agent_imei = agent_imei;
	}

	public String getAgent_fname() {
		return agent_fname;
	}

	public void setAgent_fname(String agent_fname) {
		this.agent_fname = agent_fname;
	}

	public String getAgent_lname() {
		return agent_lname;
	}

	public void setAgent_lname(String agent_lname) {
		this.agent_lname = agent_lname;
	}

	public String getCp_type() {
		return cp_type;
	}

	public void setCp_type(String cp_type) {
		this.cp_type = cp_type;
	}

	

	

	

	
}
