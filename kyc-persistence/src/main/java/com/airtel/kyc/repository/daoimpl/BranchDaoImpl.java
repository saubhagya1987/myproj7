package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.BranchDao;
import com.airtel.kyc.repository.entity.Branch;
@Repository
public class BranchDaoImpl extends GenericDaoImpl<Branch, Integer> implements BranchDao{

	@Override
	public List<Branch> findBranchList(int zoneId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("zoneId", zoneId);
		return findResultsByNamedQuery("Branch.findByZoneId", map);	
	}

}
