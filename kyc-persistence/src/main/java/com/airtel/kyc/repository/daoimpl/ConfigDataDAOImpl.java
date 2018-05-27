package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.ConfigDataDAO;
import com.airtel.kyc.repository.entity.ConfigData;

@Repository
public class ConfigDataDAOImpl extends GenericDaoImpl<ConfigData, Integer> implements ConfigDataDAO{

	@Override
	public List<ConfigData> getConfigData() {
		HashMap<String, Object> map = new HashMap<>();			
		return findResultsByNamedQuery("ConfigData.findAll", map);
	}
	
	

	@Override
	public ConfigData getNoOFConnectionPerId(Integer configDataId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("configDataId", configDataId);
		return findSingleResultByNamedQuery("ConfigData.findByDataId", map);
	}

}
