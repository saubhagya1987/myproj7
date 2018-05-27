package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.ProvinceDao;
import com.airtel.kyc.repository.entity.Province;
@Repository
public class ProvinceDaoImpl extends GenericDaoImpl<Province, Integer> implements ProvinceDao{

	/*@Override
	public List<Province> findTerritoryList(int regionId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("regionId", regionId);
		return findResultsByNamedQuery("Province.findByRegionId", map);		
		
	}

	@Override
	public List<Province> findTerritoryLists(UsersDto usersDto) {	
		List<Integer> list =new ArrayList<>();
		for (RegionIds regionId :  usersDto.getLocation().getRegionIds()) {
			Integer regionIds=regionId.getRegionId();
			list.add(regionIds);
		}
			
		String query ="Select t from Province t where region.regionId IN (:list)";		
		return findResultsByHQLINQuery(query, list);
	}*/

	@Override
	public List<Province> findProvince() {
		HashMap<String, Object> map = new HashMap<>();			
		return findResultsByNamedQuery("Province.findAll", map);	
	}

	@Override
	public Province findByProvince(String provinceName) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("provinceName", provinceName);
		return findSingleResultByNamedQuery("Province.findByProvinceName", map);	
		}

}
