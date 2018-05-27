/*package com.airtel.kycugnd.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.airtel.kycugnd.repository.dao.RoleDao;
import com.airtel.user.persistence.entities.Role;
@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRoleList() {
		HashMap<String, Object> map = new HashMap<>();		
		String namedQuery ="Select r from Role r where parentRoleId <1";		
		return (List<Role>) findSingleResultByHqlQuery(namedQuery, map);	
	}

	@Override
	public List<Role> findRoleList(Integer parentRoleId) {
		HashMap<String, Object> map = new HashMap<>();	
		map.put("parentRoleId", parentRoleId);
		return findResultsByNamedQuery("Role.findSubRole", map);
	}

}
*/