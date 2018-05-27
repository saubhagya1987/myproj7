package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.UserVillage;

public interface UserVillageDao extends GenericDao<UserVillage, Integer> {

	UserVillage  findByUserId(Integer userId);

	
}
