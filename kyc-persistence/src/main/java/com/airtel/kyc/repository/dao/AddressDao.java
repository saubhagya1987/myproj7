package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.Address;

public interface AddressDao extends GenericDao<Address, Integer>{
  
    public Address getAddress(Integer addressId);
}