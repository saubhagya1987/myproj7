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
@Table(name = "templates")
@EntityListeners(AuditedEntityListener.class)
@NamedQueries({
	@NamedQuery(name = "Templates.findByTemplateNameChannelAndLocale", query = " Select t from Templates t where templateName=:templateName and locale=:locale and chanel=:chanel")	
	
})
public class Templates extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "template_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq")
	@SequenceGenerator(allocationSize = 1, name = "country_seq", sequenceName = "country_seq")
	private Integer countryId;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "template_content")
	private String templateContent;

	@Column(name = "locale")
	private String locale;

	@Column(name = "chanel")
	private String chanel;

	@Column(name = "end_user")
	private String enduser;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getChanel() {
		return chanel;
	}

	public void setChanel(String chanel) {
		this.chanel = chanel;
	}

	public String getEnduser() {
		return enduser;
	}

	public void setEnduser(String enduser) {
		this.enduser = enduser;
	}

}