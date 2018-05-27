package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.UserVillageDao;
import com.airtel.kyc.repository.entity.UserVillage;
@Repository
public class UserVillageDaoImpl extends GenericDaoImpl<UserVillage, Integer> implements UserVillageDao{

	

	@Override
	public UserVillage findByUserId(Integer userId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("userId", userId);
		return findSingleResultByNamedQuery("UserVillage.findUserById", map);	
	}
	

}
