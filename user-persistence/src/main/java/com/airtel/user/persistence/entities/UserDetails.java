package com.airtel.user.persistence.entities;

import java.io.Serializable;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@JsonInclude(value = Include.NON_NULL)
@Entity
@Table(name = "user_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE,region="userdetails")

@NamedQueries({
	@NamedQuery(name = "UserDetails.findByMsisdn", query = " Select u from UserDetails u where msisdn=:msisdn and isOldUserDetails=:isOldUserDetails")
	
})
public class UserDetails extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Id
	@Column(name = "user_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_details_seq")
	@SequenceGenerator(allocationSize = 1,name = "user_details_seq", sequenceName = "user_details_seq")
	private Integer userDetailsId;

	@Column(name = "first_name", length = 60)
	private String firstName;

	@Column(name = "other_name", length = 60)
	private String otherName;
	
	@Column(name = "last_name", length = 60)
	private String lastName;

	@Column(name = "email", length = 60)
	private String email;

	@Column(name = "contact_address", length = 60)
	private String contactAddress;
	
	/*@Column(name = "country")
	private String country;

	@Column(name = "state")
	private String state;

	@Column(name = "district")
	private String district;

	@Column(name = "region")
	private String region;

	@Column(name = "site_id")
	private String siteId;*/
	
	@Column(name = "msisdn")
	private String msisdn;
	
	@Column(name = "auuid")
	private String auuid;
	
	@Column(name = "is_old_user_details", columnDefinition = "char default 0")
	private Integer isOldUserDetails;

	@Column(name = "dob")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dob;
	
	
	@Column(name = "id_card_no")
	private String idCardNo;
	
	@Column(name = "DISTRIBUTOR_NAME")
	private String distributorName;
	
	@Column(name = "TSM_AUUID")
	private String tsmAuuid;
	
	@Column(name = "EDIT_INFO_ACCESS", columnDefinition = "char default 0")
	private Integer editInfoAccess;
	
	@Column(name = "SIM_SWAP_ACCESS", columnDefinition = "char default 0")
	private Integer simSwapAccess;
	
	@Column(name = "CHANNEL_PARTNER_CELL_ID")
	private String channelPartnerCellId;
	
	@Column(name = "LATITUDE")
	private String latitude;
	
	@Column(name = "LONGITUDE")
	private String longitude;
	
	@Column(name = "PERMISSABLE_RADIUS")
	private String permissableRadius;
	
	@Column(name = "DEVICE_ID")
	private String deviceId;
	
	@Column(name = "device_name")
	private String deviceName;
	
	//newly added for v27 ppt
	@Column(name = "COMMISSION_MOBILE_NO")
	private String commissionMobileNo;
	
	@Column(name = "DISTRIBUTOR_ID")
	private String distributorId;
	
	@Column(name = "DISTRIBUTOR_MSISDN")
	private String distributorMsisdn;
	
	@Column(name = "airtel_handset_name")
	private String airtelHandsetName;
	
	@Column(name = "airtel_handset_imei")
	private String airtelHandsetImei;
	
	@Column(name = "cp_contract")
	private String cpContract;
	
	@Column(name = "dealer_code")
	private String dealerCode;
	
	
	/*@JsonBackReference("userDetails")
	@OneToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "user_id", nullable = false)
	private Users users;*/

	@JsonBackReference("userDetails")
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "user_id", nullable = false)
	private Users users;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference("userIdDetail")
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "userDetails", fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<UserIdDetails> userIdDetail;

	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonBackReference("userImageDetail")
	@Fetch(FetchMode.SELECT)
	@OneToMany(mappedBy = "userDetails", fetch = FetchType.EAGER)
	@Cascade({ CascadeType.ALL })
	private Set<UserImageDetail> userImageDetail;

	@JsonManagedReference(value = "permission")
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@Cascade({CascadeType.ALL})
	@JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "user_details_id", nullable = false)} , inverseJoinColumns = {@JoinColumn(name = "permission_id", nullable = false)} )
	private List<Permission> userPermission;
	
	@JsonManagedReference(value = "department")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	@JoinTable(name = "user_department", joinColumns = {@JoinColumn(name = "user_details_id", nullable = false)} , inverseJoinColumns = {@JoinColumn(name = "department_id", nullable = false)} )
	private List<Department> userDepartment;
	
	
	
	/*@JsonManagedReference(value="roles")
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.PERSIST})
	@JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_details_id", nullable = false)} , inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false)} )
	private List<Role> roles;*/

	public Integer getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Integer userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	/*public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}*/


	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Set<UserIdDetails> getUserIdDetail() {
		return userIdDetail;
	}

	public void setUserIdDetail(Set<UserIdDetails> userIdDetail) {
		this.userIdDetail = userIdDetail;
	}

	public Set<UserImageDetail> getUserImageDetail() {
		return userImageDetail;
	}

	public void setUserImageDetail(Set<UserImageDetail> userImageDetail) {
		this.userImageDetail = userImageDetail;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	
	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Integer getIsOldUserDetails() {
		return isOldUserDetails;
	}

	public void setIsOldUserDetails(Integer isOldUserDetails) {
		this.isOldUserDetails = isOldUserDetails;
	}

	public String getAuuid() {
		return auuid;
	}

	public void setAuuid(String auuid) {
		this.auuid = auuid;
	}

	public List<Permission> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(List<Permission> userPermission) {
		this.userPermission = userPermission;
	}

	public List<Department> getUserDepartment() {
		return userDepartment;
	}

	public void setUserDepartment(List<Department> userDepartment) {
		this.userDepartment = userDepartment;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	
	public Integer getEditInfoAccess() {
		return editInfoAccess;
	}

	public void setEditInfoAccess(Integer editInfoAccess) {
		this.editInfoAccess = editInfoAccess;
	}

	public Integer getSimSwapAccess() {
		return simSwapAccess;
	}

	public void setSimSwapAccess(Integer simSwapAccess) {
		this.simSwapAccess = simSwapAccess;
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

	public String getCommissionMobileNo() {
		return commissionMobileNo;
	}

	public void setCommissionMobileNo(String commissionMobileNo) {
		this.commissionMobileNo = commissionMobileNo;
	}

	public String getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}

	public String getDistributorMsisdn() {
		return distributorMsisdn;
	}

	public void setDistributorMsisdn(String distributorMsisdn) {
		this.distributorMsisdn = distributorMsisdn;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getTsmAuuid() {
		return tsmAuuid;
	}

	public void setTsmAuuid(String tsmAuuid) {
		this.tsmAuuid = tsmAuuid;
	}

	public String getAirtelHandsetName() {
		return airtelHandsetName;
	}

	public void setAirtelHandsetName(String airtelHandsetName) {
		this.airtelHandsetName = airtelHandsetName;
	}

	public String getAirtelHandsetImei() {
		return airtelHandsetImei;
	}

	public void setAirtelHandsetImei(String airtelHandsetImei) {
		this.airtelHandsetImei = airtelHandsetImei;
	}

	public String getCpContract() {
		return cpContract;
	}

	public void setCpContract(String cpContract) {
		this.cpContract = cpContract;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	/*public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}*/
	
}