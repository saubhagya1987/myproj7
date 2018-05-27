package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.ClusterDao;
import com.airtel.kyc.repository.entity.Cluster_;
@Repository
public class ClusterDaoImpl extends GenericDaoImpl<Cluster_, Integer> implements ClusterDao{

	@Override
	public List<Cluster_> findClusterList(int branchId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("branchId", branchId);
		return findResultsByNamedQuery("Cluster_.findByBranchId", map);	
	}
	

}
