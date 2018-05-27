package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.SiteDao;
import com.airtel.kyc.repository.entity.Site;
@Repository
public class SiteDaoImpl extends GenericDaoImpl<Site, Integer> implements SiteDao{

	@Override
	public List<Site> findSites(int clusterId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("clusterId", clusterId);
		return findResultsByNamedQuery("Site.findByClusterId", map);	
	}

}
