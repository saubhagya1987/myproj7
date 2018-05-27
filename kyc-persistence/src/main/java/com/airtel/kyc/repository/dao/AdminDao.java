package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.Admin;

public interface AdminDao extends GenericDao<Admin, Integer>{
    public boolean removeAdmin(Integer id);
    public boolean isAdminRegistered(String userName, String password);
    public Admin getAdmin(String username);
}