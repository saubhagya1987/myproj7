package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.user.persistence.entities.Role;

public interface RoleDao extends GenericDao<Role, Integer> {
	 
	List<Role> findRoleList();

	List<Role> findRoleList(Integer roleId);
}
