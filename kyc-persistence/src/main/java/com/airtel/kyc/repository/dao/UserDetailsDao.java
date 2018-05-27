package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.user.persistence.entities.UserDetails;

public interface UserDetailsDao extends GenericDao<UserDetails, Integer> {

	UserDetails getUserIdDetails(String msisdn, Integer oldUser);
	
	List<UserDetails> getUserDetailsList(String msisdn, Integer oldUser);
	

	
}
