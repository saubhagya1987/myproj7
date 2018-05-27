package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.UserDistrict;

public interface UserDistrictDao extends GenericDao<UserDistrict, Integer> {

	UserDistrict  findByUserId(Integer userId);

	
}
