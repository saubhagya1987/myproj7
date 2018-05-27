package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.CountryDao;
import com.airtel.kyc.repository.entity.Country;
@Repository
public class CountryDaoImpl extends GenericDaoImpl<Country, Integer> implements CountryDao{

	@Override
	public List<Country> findCountries() {
		HashMap<String, Object> map = new HashMap<>();			
		return findResultsByNamedQuery("Country.findAll", map);	
	}

	@Override
	public Country getCountryByCountryId(Integer countryId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("countryId", countryId);
		return findSingleResultByNamedQuery("Country.findByCountryId", map);
	}

	@Override
	public Country getCountryByCountryName(String countryName) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("countryName", countryName);
		return findSingleResultByNamedQuery("Country.findByCountryName", map);
	}

}
