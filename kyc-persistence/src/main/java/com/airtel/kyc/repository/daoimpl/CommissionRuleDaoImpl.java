package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.CommissionDao;
import com.airtel.kyc.repository.entity.CommissionRule;
@Repository
public class CommissionRuleDaoImpl extends GenericDaoImpl<CommissionRule, Integer> implements CommissionDao{

	@Override
	public CommissionRule getCommissionObj(Integer subRoleId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subRoleId", subRoleId);				
		return findSingleResultByNamedQuery("CommissionRule.findBySubRoleId", map);
	}

	

}
