package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.District;
import com.airtel.user.helper.dto.UsersDto;

public interface DistrictDao extends GenericDao<District, Integer> {

	List<District> findDistrict();

	List<District> findDistrictList(int territoryId);

	List<District> findDistrictLists(UsersDto usersDto);
	
	District findByDistrictId(int districtId);
	
	District findByDistrictName(String districtName);
}
