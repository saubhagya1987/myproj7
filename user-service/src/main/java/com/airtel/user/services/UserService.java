package com.airtel.user.services;

import java.util.HashMap;
import java.util.List;

import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.BaseDto;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.PermissionDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.exception.UserDaoException;

public interface UserService {

	public BaseDto addUser(UsersDto usersDto) throws UserBusinessException;

	public BaseDto addRole(RoleDto roleDto) throws UserBusinessException;

	public BaseDto addUserRole(UsersDto usersDto) throws UserBusinessException;

	public BaseDto getUserByUserName(String userName)
			throws UserBusinessException;

	public BaseDto getRoleById(Integer roleId) throws UserBusinessException;
	
	public BaseDto addPermission(PermissionDto permissionDto) throws UserBusinessException;
	
	public DepartmentDto addDepartment(DepartmentDto departmentDto) throws UserBusinessException;

	public UsersDto loadUserByName(String userName) throws UserBusinessException;
	
	public UsersDto loadUserByAuuid(String auuid) throws UserBusinessException;
	
	public List<RoleDto> getRole(HashMap hashMap) throws UserBusinessException;
	
	public List<BaseDto> getAllRole(RoleDto roleDto,int slotSize,int startIndex) throws UserBusinessException;
	
	public List<BaseDto> getAllDepartments(DepartmentDto departmentDto,int slotSize,int startIndex) throws UserBusinessException;
	
	public long getUserCount() throws UserBusinessException;
	
	public long getRoleCount() throws UserBusinessException;
	
	public long getDepartmentCount() throws UserBusinessException;

	public List<RoleDto> getRole()throws UserBusinessException;

	public List<Role> findSubRoleList(Integer roleId)throws UserBusinessException, UserDaoException;

	public List<RoleDto> getRolesWithRoleIds()throws UserBusinessException, UserDaoException;
	
	public List<RoleDto> getSubRolesWithRoleIds(int parentRoleId)throws UserBusinessException, UserDaoException;

	public List<RoleDto> getAllRoles()throws UserBusinessException, UserDaoException;

	public void updateUser(UsersDto user)throws UserBusinessException, UserDaoException;

	public void updateClientOuathDeatils(UsersDto user);

	public List<RoleDto> getAllRoleList()throws UserBusinessException, UserDaoException;

	public List<RoleDto> getSubRoleList(UsersDto usersDto)throws UserBusinessException, UserDaoException;
	
}
