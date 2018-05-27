package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.Province;
import com.airtel.user.helper.dto.UsersDto;

public interface ProvinceDao extends GenericDao<Province, Integer>{

	/*List<Province> findTerritoryList(int regionId);

	List<Province> findTerritoryLists(UsersDto usersDto);*/

	List<Province> findProvince();
    
	Province findByProvince(String provincename);
}
