package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.Country;

public interface CountryDao extends GenericDao<Country, Integer> {

	List<Country> findCountries();
	
	Country getCountryByCountryId(Integer countryId);
	
	Country getCountryByCountryName(String  countryName);

}
