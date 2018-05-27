package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.HelpAndSupportDao;
import com.airtel.kyc.repository.entity.HelpAndSupport;

@Repository
public class HelpAndSupportDaoImpl extends GenericDaoImpl<HelpAndSupport, Integer> implements HelpAndSupportDao{
	
	@Override
	public HelpAndSupport getHelpAndSupportObj(String msisdn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);				
		return findSingleResultByNamedQuery("HelpAndSupport.findByMobileNumber", map);
	}

}
