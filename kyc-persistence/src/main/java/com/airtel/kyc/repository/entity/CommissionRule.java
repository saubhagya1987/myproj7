package com.airtel.kyc.repository.entity;

import java.io.Serializable;

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
@Table(name = "commission_rule")
@NamedQueries({

	@NamedQuery(name = "CommissionRule.findBySubRoleId", query = "Select c from CommissionRule c where subRoleId=:subRoleId")
	
})
public class CommissionRule extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "commission_rule_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commission_rule_seq")
	@SequenceGenerator(allocationSize = 1, name = "commission_rule_seq", sequenceName = "commission_rule_seq")
	private Integer commissionRuleId;	
	@Column(name = "sub_role_id")
	private Integer subRoleId;
	@Column(name = "commission_value")
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