package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.ConfigData;

public interface ConfigDataDAO extends GenericDao<ConfigData, Integer> {
	List<ConfigData> getConfigData();

	ConfigData getNoOFConnectionPerId(Integer simCount);
}
