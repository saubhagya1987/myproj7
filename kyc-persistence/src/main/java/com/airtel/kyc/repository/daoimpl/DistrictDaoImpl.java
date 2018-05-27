package com.airtel.kyc.repository.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.DistrictDao;
import com.airtel.kyc.repository.entity.District;
import com.airtel.user.helper.dto.ProvinceIds;
import com.airtel.user.helper.dto.UsersDto;
@Repository
public class DistrictDaoImpl extends GenericDaoImpl<District, Integer> implements DistrictDao{

	@Override
	public List<District> findDistrict() {
		HashMap<String, Object> map = new HashMap<>();			
		return findResultsByNamedQuery("District.findAll", map);	
	}

	@Override
	public List<District> findDistrictList(int provinceId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("provinceId", provinceId);
		return findResultsByNamedQuery("District.findByProvinceId", map);	
	}

	@Override
	public List<District> findDistrictLists(UsersDto usersDto) {
		List<Integer> list =new ArrayList<>();
		for (ProvinceIds provinceId : usersDto.getLocation().getProvinceIds()) {
			Integer provinceIds=provinceId.getProvinceId();
			list.add(provinceIds);
		}			
		String query ="Select d from District d where province.provinceId IN (:list)";		
		return findResultsByHQLINQuery(query, list);
	}

	@Override
	public District findByDistrictId(int districtId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("districtId", districtId);
		return findSingleResultByNamedQuery("District.findByDistrictId", map);
	}

	@Override
	public District findByDistrictName(String districtName) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("districtName", districtName);
		return findSingleResultByNamedQuery("District.findByDistrictName", map);
	}

}
