package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.HelpAndSupport;

public interface HelpAndSupportDao extends GenericDao<HelpAndSupport, Integer> {
	
	HelpAndSupport getHelpAndSupportObj(String msisdn);

}
