package com.airtel.kyc.repository.dao;

import com.airtel.user.persistence.entities.ClientOauthDetails;

public interface ClientOauthDetailsDao extends GenericDao<ClientOauthDetails, Integer> {
	
	public ClientOauthDetails getClientDetails(String uid);

	

}
