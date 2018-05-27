package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.UserProvinceDao;
import com.airtel.kyc.repository.entity.UserProvince;
@Repository
public class UserProvinceDaoImpl extends GenericDaoImpl<UserProvince, Integer> implements UserProvinceDao{

	@Override
	public UserProvince findByUserId(Integer userId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("userId", userId);
		return findSingleResultByNamedQuery("UserProvince.findUserById", map);	
	}

	

}
