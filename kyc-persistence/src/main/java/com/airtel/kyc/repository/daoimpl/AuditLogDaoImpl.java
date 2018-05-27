package com.airtel.kyc.repository.daoimpl;

import java.sql.Timestamp;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.AuditLogDao;
import com.airtel.kyc.repository.entity.AuditLog;

@Repository
public class AuditLogDaoImpl extends GenericDaoImpl<AuditLog, Integer> 
                        implements AuditLogDao {

	@Override
	public AuditLog getAuditlog(String mobileNumber, String ipAddress,Timestamp createdOn) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("ipAddress", ipAddress);
		map.put("mobileNumber", mobileNumber);
		map.put("createdOn", createdOn);
		return findSingleResultByNamedQuery("AuditLog.findByIpAddressAndMobileNo", map);
	}  
    
	
}