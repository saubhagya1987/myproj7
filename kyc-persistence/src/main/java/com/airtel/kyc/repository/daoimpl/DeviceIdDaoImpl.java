package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.DeviceIdDao;
import com.airtel.kyc.repository.entity.DeviceId;
@Repository
public class DeviceIdDaoImpl extends GenericDaoImpl<DeviceId, Integer> implements DeviceIdDao{

	@Override
	public DeviceId getMacIdStatus(String modelName) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("modelName", modelName);
		return findSingleResultByNamedQuery("DeviceId.findByModelName", map);	
	}

	
}
