package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.Zone;

public interface ZoneDao extends GenericDao<Zone, Integer>{

	List<Zone> findZoneList();

}
