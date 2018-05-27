package com.airtel.kyc.repository.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "help_and_support")
@NamedQueries({
	@NamedQuery(name = "HelpAndSupport.findByMobileNumber", query = "Select h from HelpAndSupport h where msisdn=:msisdn")	
})
public class HelpAndSupport {	
	@Id
	@Column(name = "help_and_support_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "help_support_seq")
	@SequenceGenerator(allocationSize = 1, name = "help_support_seq", sequenceName = "help_support_seq")
	private Integer helpAndSupportId;
	
	@Column(name = "msisdn")
	private String msisdn;	
	
	@Column(name = "help_and_support_text")
	private String helpAndSupportText;	
	
	@Column(name = "updated_timeStamp")
	private Timestamp updatedTimeStamp;

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Timestamp getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}

	public void setUpdatedTimeStamp(Timestamp updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}

	public String getHelpAndSupportText() {
		return helpAndSupportText;
	}

	public void setHelpAndSupportText(String helpAndSupportText) {
		this.helpAndSupportText = helpAndSupportText;
	}

}
