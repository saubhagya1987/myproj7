package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.UserDistrictDao;
import com.airtel.kyc.repository.entity.UserDistrict;
@Repository
public class UserDistrictDaoImpl extends GenericDaoImpl<UserDistrict, Integer> implements UserDistrictDao{

	
	@Override
	public UserDistrict findByUserId(Integer userId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("userId", userId);
		return findSingleResultByNamedQuery("UserDistrict.findUserById", map);	
	}

	

}
