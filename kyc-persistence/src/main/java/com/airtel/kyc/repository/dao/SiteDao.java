package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.Site;

public interface SiteDao extends GenericDao<Site, Integer> {

	List<Site> findSites(int clusterId);

}
