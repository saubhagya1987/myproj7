package com.airtel.kyc.helpers.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommissionRuleDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	private Integer commissionRuleId;	
	
	private Integer subRoleId;
	
	private String commissionValue;

	public Integer getCommissionRuleId() {
		return commissionRuleId;
	}

	public void setCommissionRuleId(Integer commissionRuleId) {
		this.commissionRuleId = commissionRuleId;
	}
	
	public String getCommissionValue() {
		return commissionValue;
	}

	public void setCommissionValue(String commissionValue) {
		this.commissionValue = commissionValue;
	}

	public Integer getSubRoleId() {
		return subRoleId;
	}

	public void setSubRoleId(Integer subRoleId) {
		this.subRoleId = subRoleId;
	}

}
