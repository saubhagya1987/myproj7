package com.airtel.kyc.repository.dao;

import java.sql.Timestamp;

import com.airtel.kyc.repository.entity.AuditLog;

public interface AuditLogDao extends GenericDao<AuditLog, Integer>{
	
	 public AuditLog getAuditlog(String mobileNo,String ipAddress,Timestamp time);
  
  
}