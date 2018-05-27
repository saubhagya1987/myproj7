package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.AddressDao;
import com.airtel.kyc.repository.entity.Address;

@Repository
public class AdddressDaoImpl extends GenericDaoImpl<Address, Integer> 
                        implements AddressDao {
   
    @Override
    public Address getAddress(Integer addressId) {
    	HashMap<String, Object> map = new HashMap<>();	
		map.put("addressId", addressId);
		return findSingleResultByNamedQuery("Address.findByAddressId", map);
 
    }
}