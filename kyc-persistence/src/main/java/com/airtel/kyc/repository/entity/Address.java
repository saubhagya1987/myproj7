package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.airtel.kyc.repository.listener.AuditedEntityListener;

@Entity
@Table(name = "address")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	
	@NamedQuery(name = "Address.findByAddressId", query = " Select a from Address a where addressId=:addressId")
	
})
public class Address extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
	@SequenceGenerator(allocationSize = 1, name = "address_seq", sequenceName = "address_seq")
	private Integer addressId;

	@Column(name = "address")
	private String address;

	@Column(name = "country_id")
	private Integer countryId;

	// @Fetch(FetchMode.SELECT)
	@Column(name = "region_id")
	private Integer regionId;

	@Column(name = "district_id")
	private Integer districtId;

	@Column(name = "ward")
	private String ward;
	
	@Column(name = "city")
	private String city;
	
	
	@Column(name = "permanent_address")
	private String permanentAddress;
	
	
	@Column(name = "house_no")
	private String houseNo;
	
	
	@Column(name = "village")
	private String village;

	
	@Column(name = "postal_address")
	private String postalAddress;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "village_id")
	private Integer villageId;

	
	
	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}	

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
	}

	

}