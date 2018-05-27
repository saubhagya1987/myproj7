package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.Village;

public interface VillageDao extends GenericDao<Village, Integer> {

	List<Village> findParishList(int districtId);

	Village findByDistrictId(Integer villageId);
	
	Village findByVillageName(String villageName);

	
}
