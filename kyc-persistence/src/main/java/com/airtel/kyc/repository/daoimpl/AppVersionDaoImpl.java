package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.AppVersionDao;
import com.airtel.kyc.repository.entity.AppVersionMaster;
@Repository
public class AppVersionDaoImpl extends GenericDaoImpl<AppVersionMaster, Integer> implements AppVersionDao{

	@Override
	public AppVersionMaster getAppVersion(String appId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("appId", appId);
		return findSingleResultByNamedQuery("AppVersionMaster.findByAppId", map);
	}

	
}
