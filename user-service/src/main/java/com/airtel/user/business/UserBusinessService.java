package com.airtel.user.business;

import java.util.List;

import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.BaseDto;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.PermissionDto;
import com.airtel.user.helper.dto.ResponseBeanDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.SearchDto;
import com.airtel.user.helper.dto.SearchResponseDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.exception.UserDaoException;

public interface UserBusinessService {
	public ResponseBeanDto<?> registerPermission(PermissionDto permissionDto) throws UserBusinessException;
	
	public ResponseBeanDto<?> registerDepartment(DepartmentDto departmentDto) throws UserBusinessException;
	
	public ResponseBeanDto<?> registerUser(UsersDto usersDto)
			throws UserBusinessException;

	public ResponseBeanDto<?> registerRole(RoleDto roleDto)
			throws UserBusinessException;

	public ResponseBeanDto<SearchResponseDto> SearchUser(int startIndex,
			long slot, SearchDto searchDto) throws UserBusinessException;

	public ResponseBeanDto<?> getRoles(int startIndex) throws UserBusinessException;
	
	public ResponseBeanDto<?> getRoles(RoleDto roleDto,int slotSize,int startIndex) throws UserBusinessException;
	
	public ResponseBeanDto<?> getDepartments(DepartmentDto departmentDto,int slotSize,int startIndex) throws UserBusinessException;
	
	public ResponseBeanDto<?> getUserCount() throws UserBusinessException;

	public ResponseBeanDto<List<RoleDto>> getAllRoles()throws UserBusinessException, UserDaoException;

	public ResponseBeanDto<List<RoleDto>> getRolesWithRoleIds()throws UserBusinessException, UserDaoException;

	public ResponseBeanDto<List<RoleDto>> getSubRolesWithRoleIds(int parentRoleId)throws UserBusinessException, UserDaoException;

	

	
	

}
