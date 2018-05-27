/*package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.RegionDao;
import com.airtel.kyc.repository.entity.Region;
@Repository
public class RegionDaoImpl extends GenericDaoImpl<Region, Integer> implements RegionDao{

	@Override
	public List<Region> findRegions() {
		HashMap<String, Object> map = new HashMap<>();			
		return findResultsByNamedQuery("Region.findAll", map);	
	}

}
*/