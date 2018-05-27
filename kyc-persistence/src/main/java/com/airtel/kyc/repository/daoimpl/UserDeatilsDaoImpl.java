package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.UserDetailsDao;
import com.airtel.user.persistence.entities.UserDetails;
@Repository
public class UserDeatilsDaoImpl extends GenericDaoImpl<UserDetails, Integer> implements UserDetailsDao{

	@Override
	public UserDetails getUserIdDetails(String msisdn, Integer isOldUserDetails) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);		
		map.put("isOldUserDetails", isOldUserDetails);	
		return findSingleResultByNamedQuery("UserDetails.findByMsisdn", map);
	}

	@Override
	public List<UserDetails> getUserDetailsList(String msisdn, Integer isOldUserDetails) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);		
		map.put("isOldUserDetails", isOldUserDetails);	
		return findResultsByNamedQuery("UserDetails.findByMsisdn", map);
	}

	


}
