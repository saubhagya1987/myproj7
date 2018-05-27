package com.airtel.kyc.repository.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "subscriber_details")
@NamedQueries({
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdAndIsOldUserDetails", query = " Select s from SubscriberDetails s where subscriber.subscriberId=:subscriberId and isOldUserDetails=:isOldUserDetails"),
	@NamedQuery(name = "SubscriberDetails.findByMsisdnAndIsOldUserDetails", query = " Select s from SubscriberDetails s where msisdn=:msisdn and isOldUserDetails=:isOldUserDetails and finalStatus=:finalStatus "),
	@NamedQuery(name = "SubscriberDetails.findByMsisdnAndIsOldUserDetail", query = " Select s from SubscriberDetails s where msisdn=:msisdn and isOldUserDetails=:isOldUserDetails"),
	@NamedQuery(name = "SubscriberDetails.findByMsisdnAndSimValidationFlag", query = " Select s from SubscriberDetails s where msisdn=:msisdn and isOldUserDetails=:isOldUserDetails and simValidationFlag=:simValidationFlag "),
	@NamedQuery(name = "SubscriberDetails.findByMsisdnAndIsOldUserDetailsAndFinalStatus", query = " Select s from SubscriberDetails s where msisdn=:msisdn and isOldUserDetails=:isOldUserDetails and finalStatus=:finalStatus "),
	@NamedQuery(name = "SubscriberDetails.findByMsisdn", query = " Select s from SubscriberDetails s where msisdn=:msisdn"),
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdAndUpdatedOnAuto", query = "Select s from SubscriberDetails s where ((s.isOldUserDetails=0 and s.finalStatus='PENDING') or (s.isOldUserDetails=1 and s.finalStatus='PENDING' and (s.updatedOn=:updatedOn or s.updatedOn is null))) and subscriber.subscriberId=:subscriberId and (s.subscriberWorkFlow.action='PENDING' or s.subscriberWorkFlow.action is null ) "),
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdAndUpdatedOn", query = " Select s from SubscriberDetails s where ((s.isOldUserDetails=0 and s.finalStatus='PENDING') or (s.isOldUserDetails=0 and s.finalStatus='IN_PROCESS') or (s.isOldUserDetails=1 and s.finalStatus='PENDING' and updatedOn=:updatedOn)) and subscriber.subscriberId=:subscriberId"),
	
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdAndUpdatedOnApproved", query = " Select s from SubscriberDetails s where s.isOldUserDetails=0 and s.finalStatus='APPROVED' and updatedOn=:updatedOn and subscriber.subscriberId=:subscriberId"),
	
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdAndUpdatedOnObject", query = " Select s from SubscriberDetails s where ((s.isOldUserDetails=0 and s.finalStatus='PENDING') or (s.isOldUserDetails=1 and s.finalStatus='PENDING' and updatedOn=:updatedOn)) and subscriber.subscriberId=:subscriberId and (action='PENDING' or action is null)"),
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdAndUpdatedOnObjectForUpdate", query = " Select s from SubscriberDetails s where updatedOn=:updatedOn and subscriber.subscriberId=:subscriberId "),
	//@NamedQuery(name = "SubscriberDetails.findBySubscriberIdApproved", query = " Select s from SubscriberDetails s where subscriberWorkFlow.action='APPROVED' and subscriber.subscriberId=:subscriberId and s.subscriberWorkFlow.action='APPROVED'"),
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdApproved", query = " Select s from SubscriberDetails s where action='APPROVED' and subscriber.subscriberId=:subscriberId "),
	//@NamedQuery(name = "SubscriberDetails.findBySubscriberIdBarred", query = " Select s from SubscriberDetails s where subscriberWorkFlow.action='BARRED' and subscriber.subscriberId=:subscriberId and s.subscriberWorkFlow.action='BARRED'"),
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdBarred", query = " Select s from SubscriberDetails s where action='BARRED' and subscriber.subscriberId=:subscriberId"),
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdPendingAll", query = " Select s from SubscriberDetails s where s.finalStatus='PENDING' and subscriber.subscriberId=:subscriberId"),
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdClosedAll", query = " Select s from SubscriberDetails s where (s.finalStatus='BARRED' or s.finalStatus='APPROVED' or s.finalStatus='REJECTED' or s.hotlineFlag=1) and subscriber.subscriberId=:subscriberId"),
	@NamedQuery(name = "SubscriberDetails.getSubscriberDetailsHotline", query = " Select s from SubscriberDetails s where s.hotlineFlag=1 and subscriber.subscriberId=:subscriberId"),
	@NamedQuery(name = "SubscriberDetails.getSubscriberDetailsObj", query = " Select s from SubscriberDetails s where  subscriber.subscriberId=:subscriberId and isOldUserDetails=:isOldUserDetails and action=:action"),
	@NamedQuery(name = "SubscriberDetails.findBySubscriberIdAndUpdatedOnObj", query = " Select s from SubscriberDetails s where ((s.isOldUserDetails=0 and s.finalStatus='PENDING') or (s.isOldUserDetails=1 and s.finalStatus='PENDING' and s.updatedOn=:updatedOn)) and subscriber.subscriberId=:subscriberId"),
	@NamedQuery(name = "SubscriberDetails.findPendingAllWithNew", query = " Select s from SubscriberDetails s where s.finalStatus=:finalStatus and s.isOldUserDetails=:isOldUserDetails and s.caseType='NEW' and (action='PENDING' or action is null)"),
	@NamedQuery(name = "SubscriberDetails.findPendingAllWithEdit", query = " Select s from SubscriberDetails s where s.finalStatus=:finalStatus and s.isOldUserDetails=:isOldUserDetails and s.caseType='EDIT' and (action='PENDING' or action is null)"),
	@NamedQuery(name = "SubscriberDetails.findPendingAllWithExisting", query = " Select s from SubscriberDetails s where s.finalStatus=:finalStatus and s.isOldUserDetails=:isOldUserDetails and s.caseType='EXISTING' and (action='PENDING' or action is null)"),
	@NamedQuery(name = "SubscriberDetails.findPendingAllWithAm", query = " Select s from SubscriberDetails s where s.finalStatus=:finalStatus and s.isOldUserDetails=:isOldUserDetails and s.caseType='AM' and amAccount=1 and (action='PENDING' or action is null)")

})


public class SubscriberDetails extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "subscriber_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_details_seq")
	@SequenceGenerator(allocationSize = 1, name = "subscriber_details_seq", sequenceName = "subscriber_details_seq")
	private Integer subscriberDetailsId;

	@Column(name = "first_name", length = 100)
	private String firstName;

	@Column(name = "middle_name", length = 100)
	private String middleName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "gender")
	private String gender;

	@Column(name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;


	@Column(name = "postal_address")
	private String postalAddress;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "contact_details")
	private String contactDetails;
	
	@Column(name = "is_minor", columnDefinition = "char default 0")
	private Integer isMinor;
	
	@Column(name = "am_account", columnDefinition = "char default 0")
	private Integer amAccount;
	
	@Column(name = "channel_partner_cell_id")
	private String channelPartnerCellId;	
	
	@Column(name = "latitude")
	private String latitude;	
	
	@Column(name = "longitude")
	private String longitude;	
	
	@Column(name = "permissable_radius")
	private String permissableRadius;
	
	@Column(name = "device_id")
	private String deviceId;
	

	@Column(name = "is_old_user_details", columnDefinition = "char default 0")
	private Integer isOldUserDetails;
	
	//new columns added from subscriber table	
	@Column(name = "msisdn")
	private String msisdn;
	
	@Column(name = "alternate_no")
	private String alternateNo;

	@Column(name = "imsi_number")
	private String imsiNumber;

	@Column(name = "registration_type")
	private String registrationType;	

	@Column(name = "registation_from")
	private String registationFrom;

	@Column(name = "activation_time")
	private Timestamp activationTime;

	@Column(name = "rejection_time")
	private Timestamp rejectionTime;

	@Column(name = "final_status")
	private String finalStatus;

	@Column(name = "final_status_reason")
	private String finalStatusReason;
	
	@Column(name = "partial_activation_time")
	private Timestamp partialActivationTime;

	@Column(name = "api_activation_time")
	private Timestamp apiActivationTime;

	@Column(name = "registration_mode")
	private String registrationMode;

	@Column(name = "am_activation_time")
	private Timestamp amActivationTime;

	@Column(name = "sv_activation_time")
	private Timestamp svActivationTime;
	
	@Column(name = "sv_validation_time")
	private Timestamp svValidationTime;	

	@Column(name = "bi_activation_time")
	private Timestamp biActivationTime;

	@Column(name = "ema_activation_time")
	private Timestamp emaActivationTime;

	@Column(name = "case_type",length=5)
	private String caseType;

	@Column(name="kyc_mode")
	private String kycMode;	
	
	@Column(name = "source_channel_type")
	private String sourceChannelType;

	@Column(name = "creator_email_id")
	private String creatorEmailId;

	@Column(name="language")
	private String language;


	@Column(name="work_flow_status")
	private String workFlowStatus;
	
	@Column(name="work_flow_reason")
	private String workFlowReason;
	
	@Column(name="location")
	private String location;
	
	@Column(name="nationality")
	private String nationality;
	
	@Column(name = "is_physical_form_received", columnDefinition = "char default 0")
	private Integer isPhysicalFormReceived;
	
	@Column(name = "physical_form_status")
	private String physicalFormStatus;

	@Column(name = "blacklist_flag", columnDefinition = "char default 0")
	private Integer blacklistFlag;
	
	@Column(name = "hotline_flag", columnDefinition = "char default 0")
	private Integer hotlineFlag;	
	
	@Column(name = "parent_subscriberId")
	private Integer parentSubscriberId;	
	
	@Column(name = "barring_source")
	private String barringSource;
	
	@Column(name = "occupation")
	private String occupation;

	@Column(name = "minor_name")
	private String minorName;

	@Column(name = "is_latest",columnDefinition = "char default 0")
	private Integer isLatest;
	
	@Column(name = "is_image_edited")
	private String isImageEdited;
	
	@Column(name = "finger_print", length= 1000000000)
	private Blob fingerPrint;
	
	
	@Column(name = "assigned_on")
	private Timestamp assignedOn;
	
	@Column(name = "approved_on")
	private Timestamp approvedOn;
	
	@Column(name = "submited_on")
	private Timestamp submitedOn;
	
	@Column(name = "synced_on")
	private Timestamp syncedOn;
	
	
	@Column(name = "registered_on")
	private Timestamp registeredOn;

	@Column(name = "online_offline_flag")
	private String onlineOfflineFlag;
	
	@Column(name = "sim_serial_number")
	private String simSerialNumber;
	
	@Column(name = "place_of_birth")
	private String placeOfBirth;
	
	/*@Column(name = "country_id")
	private Integer countryId;*/
	
	@Column(name = "proxy_for_registration",columnDefinition = "char default 0")
	private Integer proxyForRegistration;
	
	@Column(name = "bulk_subscriber_flag",columnDefinition = "char default 0")
	private Integer bulkSubscriberFlag;
	
	@Column(name = "subscriber_type")
	private String subscriberType;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "sim_validation_flag", columnDefinition = "char default 0")
	private Integer simValidationFlag;
	
	@Column(name = "sim_validation_time")
	private Timestamp simValidationTime;
	
	@Column(name = "assignment_flag", columnDefinition = "char default 0")
	private Integer assignmentFlag;
	
	@Column(name = "ip_address")
	private String ipAddress;
	
	@Column(name = "imei")
	private String imei;
	
	//new columns added from subscriber table end

	public Integer getAssignmentFlag() {
		return assignmentFlag;
	}

	public void setAssignmentFlag(Integer assignmentFlag) {
		this.assignmentFlag = assignmentFlag;
	}

	public Integer getSimValidationFlag() {
		return simValidationFlag;
	}

	public void setSimValidationFlag(Integer simValidationFlag) {
		this.simValidationFlag = simValidationFlag;
	}

	public Timestamp getSimValidationTime() {
		return simValidationTime;
	}

	public void setSimValidationTime(Timestamp simValidationTime) {
		this.simValidationTime = simValidationTime;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SELECT)
	@JsonBackReference("subscriberDetails")
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "subscriber_id", nullable = false)
	private Subscriber subscriber;

	//@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference("subscriberIdDetails")
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "subscriberDetails", fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	private Set<SubscriberIdDetails> subscriberIdDetails;
	
	
	/*@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference("subscriberImageDetail")
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "subscriberDetails", fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	private Set<SubscriberImageDetail> subscriberImageDetail;*/
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference("subscriberDocuments")
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "subscriberDetails", fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	private List<SubscriberDocuments> subscriberDocuments;

	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SELECT)
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "address_id",  nullable = true)
	private Address address;

	@JsonManagedReference(value = "subscriberWorkFlow")
	@Fetch(FetchMode.SELECT)
	@OneToOne(mappedBy = "subscriberDetails", fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	private SubscriberWorkFlow subscriberWorkFlow;
	
	/*
	 * @Fetch(FetchMode.SELECT)
	 * 
	 * @OneToOne(fetch = FetchType.LAZY)
	 * 
	 * @Cascade({CascadeType.ALL})
	 * 
	 * @JoinTable(name = "subscriber_details_nida", joinColumns =
	 * {@JoinColumn(name = "subscriber_details_id", nullable = false)} ,
	 * inverseJoinColumns = {@JoinColumn(name = "nida_details_id", nullable =
	 * false)} ) private NidaDetails nidaDetails;
	 */
	
	public List<SubscriberDocuments> getSubscriberDocuments() {
		return subscriberDocuments;
	}

	public String getBarringSource() {
		return barringSource;
	}

	public void setBarringSource(String barringSource) {
		this.barringSource = barringSource;
	}

	public void setSubscriberDocuments(List<SubscriberDocuments> subscriberDocuments) {
		this.subscriberDocuments = subscriberDocuments;
	}

	public Address getAddress() {
		return address;
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

	public void setAddress(Address address) {
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

	

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	public Set<SubscriberIdDetails> getSubscriberIdDetails() {
		return subscriberIdDetails;
	}

	public void setSubscriberIdDetails(Set<SubscriberIdDetails> subscriberIdDetails) {
		this.subscriberIdDetails = subscriberIdDetails;
	}

	/*public Set<SubscriberImageDetail> getSubscriberImageDetail() {
		return subscriberImageDetail;
	}

	public void setSubscriberImageDetail(Set<SubscriberImageDetail> subscriberImageDetail) {
		this.subscriberImageDetail = subscriberImageDetail;
	}*/

	public Integer getIsOldUserDetails() {
		return isOldUserDetails;
	}

	public void setIsOldUserDetails(Integer isOldUserDetails) {
		this.isOldUserDetails = isOldUserDetails;
	}

	

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAlternateNo() {
		return alternateNo;
	}

	public void setAlternateNo(String alternateNo) {
		this.alternateNo = alternateNo;
	}

	public String getImsiNumber() {
		return imsiNumber;
	}

	public void setImsiNumber(String imsiNumber) {
		this.imsiNumber = imsiNumber;
	}

	public String getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}

	
	public String getRegistationFrom() {
		return registationFrom;
	}

	public void setRegistationFrom(String registationFrom) {
		this.registationFrom = registationFrom;
	}

	public Timestamp getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Timestamp activationTime) {
		this.activationTime = activationTime;
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

	public Timestamp getPartialActivationTime() {
		return partialActivationTime;
	}

	public void setPartialActivationTime(Timestamp partialActivationTime) {
		this.partialActivationTime = partialActivationTime;
	}

	public Timestamp getApiActivationTime() {
		return apiActivationTime;
	}

	public void setApiActivationTime(Timestamp apiActivationTime) {
		this.apiActivationTime = apiActivationTime;
	}

	public String getRegistrationMode() {
		return registrationMode;
	}

	public void setRegistrationMode(String registrationMode) {
		this.registrationMode = registrationMode;
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

	/*public Timestamp getHpActivationTime() {
		return hpActivationTime;
	}

	public void setHpActivationTime(Timestamp hpActivationTime) {
		this.hpActivationTime = hpActivationTime;
	}*/

	
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Integer getParentSubscriberId() {
		return parentSubscriberId;
	}

	public void setParentSubscriberId(Integer parentSubscriberId) {
		this.parentSubscriberId = parentSubscriberId;
	}

	public SubscriberWorkFlow getSubscriberWorkFlow() {
		return subscriberWorkFlow;
	}

	public void setSubscriberWorkFlow(SubscriberWorkFlow subscriberWorkFlow) {
		this.subscriberWorkFlow = subscriberWorkFlow;
	}
	/*
	 * public NidaDetails getNidaDetails() { return nidaDetails; }
	 * 
	 * public void setNidaDetails(NidaDetails nidaDetails) { this.nidaDetails =
	 * nidaDetails; }
	 */

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

	public String getIsImageEdited() {
		return isImageEdited;
	}

	public void setIsImageEdited(String isImageEdited) {
		this.isImageEdited = isImageEdited;
	}

	public Timestamp getAssignedOn() {
		return assignedOn;
	}

	public void setAssignedOn(Timestamp assignedOn) {
		this.assignedOn = assignedOn;
	}

	public Timestamp getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Timestamp approvedOn) {
		this.approvedOn = approvedOn;
	}

	public Blob getFingerPrint() {
		return fingerPrint;
	}

	public void setFingerPrint(Blob fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

	
	public Timestamp getSyncedOn() {
		return syncedOn;
	}

	public void setSyncedOn(Timestamp syncedOn) {
		this.syncedOn = syncedOn;
	}

	public String getOnlineOfflineFlag() {
		return onlineOfflineFlag;
	}

	public void setOnlineOfflineFlag(String onlineOfflineFlag) {
		this.onlineOfflineFlag = onlineOfflineFlag;
	}

	public Timestamp getSubmitedOn() {
		return submitedOn;
	}

	public void setSubmitedOn(Timestamp submitedOn) {
		this.submitedOn = submitedOn;
	}

	public String getSimSerialNumber() {
		return simSerialNumber;
	}

	public void setSimSerialNumber(String simSerialNumber) {
		this.simSerialNumber = simSerialNumber;
	}

	

	public Integer getProxyForRegistration() {
		return proxyForRegistration;
	}

	public void setProxyForRegistration(Integer proxyForRegistration) {
		this.proxyForRegistration = proxyForRegistration;
	}

	public Timestamp getSvValidationTime() {
		return svValidationTime;
	}

	public void setSvValidationTime(Timestamp svValidationTime) {
		this.svValidationTime = svValidationTime;
	}

	public Timestamp getRegisteredOn() {
		return registeredOn;
	}

	public void setRegisteredOn(Timestamp registeredOn) {
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

	public Timestamp getEmaActivationTime() {
		return emaActivationTime;
	}

	public void setEmaActivationTime(Timestamp emaActivationTime) {
		this.emaActivationTime = emaActivationTime;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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

}