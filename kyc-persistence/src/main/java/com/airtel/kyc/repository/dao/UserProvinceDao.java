package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.UserProvince;

public interface UserProvinceDao extends GenericDao<UserProvince, Integer> {

	UserProvince  findByUserId(Integer userId);
	

	
}
