package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.ClientOauthDetailsDao;
import com.airtel.user.persistence.entities.ClientOauthDetails;

@Repository
public class ClientOauthDetailsDaoImpl extends GenericDaoImpl<ClientOauthDetails, Integer>  implements ClientOauthDetailsDao {

	@Override
	public ClientOauthDetails getClientDetails(String client_id) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("client_id", client_id);
		return findSingleResultByNamedQuery("ClientOauthDetails.findByClientId", map);	
	}
    
   
}