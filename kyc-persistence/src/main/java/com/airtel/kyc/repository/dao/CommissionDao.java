package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.CommissionRule;

public interface CommissionDao extends GenericDao<CommissionRule, Integer> {

	CommissionRule getCommissionObj(Integer subRoleId);
	 
	
}
