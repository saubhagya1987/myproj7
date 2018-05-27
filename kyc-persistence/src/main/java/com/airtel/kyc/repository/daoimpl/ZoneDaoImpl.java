package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.ZoneDao;
import com.airtel.kyc.repository.entity.Zone;
@Repository
public class ZoneDaoImpl extends GenericDaoImpl<Zone, Integer> implements ZoneDao{

	@Override
	public List<Zone> findZoneList() {
		HashMap<String, Object> map = new HashMap<>();			
		return findResultsByNamedQuery("Zone.findAll", map);	
	}

}
