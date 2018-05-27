package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.DeviceId;

public interface DeviceIdDao extends GenericDao<DeviceId, Integer>{

	DeviceId getMacIdStatus(String modelName);
	

}
