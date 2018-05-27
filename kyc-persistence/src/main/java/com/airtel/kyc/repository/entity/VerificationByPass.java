package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.airtel.kyc.repository.listener.AuditedEntityListener;

@Entity
@Table(name = "verification_by_pass")
@EntityListeners(AuditedEntityListener.class)
public class VerificationByPass extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bypass_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "verification_bypass_seq")
	@SequenceGenerator(allocationSize = 1, name = "verification_bypass_seq", sequenceName = "verification_bypass_seq")
	private Integer bypassId;

	@Column(name = "bypass_status", columnDefinition = "char default 0")
	private Integer bypassStatus;

	public Integer getBypassId() {
		return bypassId;
	}

	public void setBypassId(Integer bypassId) {
		this.bypassId = bypassId;
	}

	public Integer getBypassStatus() {
		return bypassStatus;
	}

	public void setBypassStatus(Integer bypassStatus) {
		this.bypassStatus = bypassStatus;
	}

	
}