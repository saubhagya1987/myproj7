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
@Table(name = "country")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	@NamedQuery(name = "Country.findAll", query = " Select c from Country c"),
	@NamedQuery(name = "Country.findByCountryId", query = " Select c from Country c where countryId=:countryId"),
	@NamedQuery(name = "Country.findByCountryName", query = " Select c from Country c where countryName=:countryName")
	
})
public class Country extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "country_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
	@SequenceGenerator(allocationSize = 1, name = "country_seq", sequenceName = "country_seq")
	private Integer countryId;

	@Column(name = "country_name")
	private String countryName;
	
	@Column(name = "iso_code")
	private String isoCode;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

}