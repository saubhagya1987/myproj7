package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.VillageDao;
import com.airtel.kyc.repository.entity.Village;
@Repository
public class VillageDaoImpl extends GenericDaoImpl<Village, Integer> implements VillageDao{

	@Override
	public List<Village> findParishList(int districtId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("districtId", districtId);
		return findResultsByNamedQuery("Village.findByDistrictId", map);	
	}

	@Override
	public Village findByDistrictId(Integer villageId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("villageId", villageId);
		return findSingleResultByNamedQuery("Village.findByVillageId", map);	
	}

	@Override
	public Village findByVillageName(String villageName) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("villageName", villageName);
		return findSingleResultByNamedQuery("Village.findByVillageName", map);
	}

	

}
