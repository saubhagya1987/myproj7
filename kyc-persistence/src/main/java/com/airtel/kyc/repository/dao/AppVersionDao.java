package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.AppVersionMaster;

public interface AppVersionDao extends GenericDao<AppVersionMaster, Integer>{

	AppVersionMaster getAppVersion(String appId);
	

}
