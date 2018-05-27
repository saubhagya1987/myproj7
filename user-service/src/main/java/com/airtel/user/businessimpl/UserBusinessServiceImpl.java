package com.airtel.user.businessimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.airtel.user.business.UserBusinessService;
import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.BaseDto;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.PermissionDto;
import com.airtel.user.helper.dto.ResponseBeanDto;
import com.airtel.user.helper.dto.ResponseDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.SearchDto;
import com.airtel.user.helper.dto.SearchResponseDto;
import com.airtel.user.helper.dto.TotalCountResponseDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.helper.util.UserResponseUtils;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.exception.UserDaoException;
import com.airtel.user.services.UserService;

@Service
public class UserBusinessServiceImpl implements UserBusinessService {

	@Qualifier("uerService")
	@Autowired
	private UserService userService;
	
	@Override
	public ResponseBeanDto<?> registerUser(UsersDto usersDto) throws UserBusinessException {
		// TODO Auto-generated method stub
		ResponseBeanDto<BaseDto> responseBean=null;
		try {
			usersDto =(UsersDto) userService.addUser(usersDto);
			responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
			responseBean.setBody(usersDto);		
		}catch (UserBusinessException cause) {
			// TODO Auto-generated catch block
			responseBean = UserResponseUtils.getUserResponse(201,cause.getMessage());
		}
		
		return responseBean;
	}

	@Override
	public ResponseBeanDto<?> registerRole(RoleDto roleDto) throws UserBusinessException {
		// TODO Auto-generated method stub
		ResponseBeanDto<BaseDto> responseBean=null;
		
		try {
			roleDto=(RoleDto) userService.addRole(roleDto);
			responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
			responseBean.setBody(roleDto);
		}catch (UserBusinessException cause) {
			// TODO Auto-generated catch block
			responseBean = UserResponseUtils.getUserResponse(201,cause.getMessage());
		}

		return responseBean;
	}


	@Override
	public ResponseBeanDto<?> registerPermission(PermissionDto permissionDto) throws UserBusinessException {
		// TODO Auto-generated method stub
		ResponseBeanDto<BaseDto> responseBean=null;		
		try {
			permissionDto=(PermissionDto) userService.addPermission(permissionDto);
			responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
			responseBean.setBody(permissionDto);
		}catch (UserBusinessException cause) {
			// TODO Auto-generated catch block
			responseBean = UserResponseUtils.getUserResponse(201,cause.getMessage());
		}
		return responseBean;
	}

	@Override
	public ResponseBeanDto<SearchResponseDto> SearchUser(int startIndex,long slot, SearchDto searchDto)
			throws UserBusinessException {
		
		//Fetch count
		
		//Fetch result slot
		//this.userService.
		
		//assemble response
		//this.userService.searchUser(searchDto);
		
		return null;
	}



	@Override
	public ResponseBeanDto<?> registerDepartment(DepartmentDto departmentDto) throws UserBusinessException {
		// TODO Auto-generated method stub
		ResponseBeanDto<DepartmentDto> responseBean=null;
			
		try {
			departmentDto=(DepartmentDto) userService.addDepartment(departmentDto);
			responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
			responseBean.setBody(departmentDto);
		
		}catch (UserBusinessException cause) {
			// TODO Auto-generated catch block
			responseBean = UserResponseUtils.getUserResponse(201,cause.getMessage());
		}
		return responseBean;
	}

	@Override
	public ResponseBeanDto<?> getRoles(int roleID) throws UserBusinessException {
		ResponseBeanDto<List<RoleDto>> responseBean = null;
		HashMap<String, Object> parameterMap= null ; 
		if(roleID != 0) {
			parameterMap = new HashMap<>();
			parameterMap.put("roleId", roleID);
			
		}
		List<RoleDto> dtos = userService.getRole(parameterMap);
		responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
		responseBean.setBody(dtos);
		return responseBean;
	}

	@Override
	public ResponseBeanDto<?> getRoles(RoleDto roleDto,int slotSize,int startIndex) throws UserBusinessException {
		ResponseBeanDto<SearchResponseDto> responseBean = null;
		List<BaseDto> dtos = userService.getAllRole(roleDto,slotSize,startIndex);
		long roleCount = userService.getRoleCount();
		SearchResponseDto searchResponseDto=new SearchResponseDto();
		searchResponseDto.setResult(dtos);
		searchResponseDto.setTotalRecord(roleCount);
		searchResponseDto.setSlotSize(slotSize);
		searchResponseDto.setStartIndex(startIndex);
		responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
		responseBean.setBody(searchResponseDto);
		return responseBean;
	}

	@Override
	public ResponseBeanDto<?> getUserCount() throws UserBusinessException {
		TotalCountResponseDto totalCountResponseDto = new TotalCountResponseDto();
		ResponseBeanDto responseBean = null;
		try {
			long userCount = userService.getUserCount();
			long roleCount = userService.getRoleCount();
			long deptCount = userService.getDepartmentCount();
			totalCountResponseDto.setUserCount(userCount);
			totalCountResponseDto.setRoleCount(roleCount);
			totalCountResponseDto.setDepartmentCount(deptCount);
			responseBean =  UserResponseUtils.getUserResponse(HttpStatus.OK);
			} catch (Exception cause) {
			responseBean =  UserResponseUtils.getUserResponse(HttpStatus.FAILED_DEPENDENCY);
			
		}
		responseBean.setBody(totalCountResponseDto);
		return responseBean;
	}

	@Override
	public ResponseBeanDto<?> getDepartments(DepartmentDto departmentDto, int slotSize, int startIndex)
			throws UserBusinessException {
		ResponseBeanDto<SearchResponseDto> responseBean = null;
		List<BaseDto> dtos = userService.getAllDepartments(departmentDto, slotSize, startIndex);
		
		long roleCount = userService.getDepartmentCount();
		
		SearchResponseDto searchResponseDto=new SearchResponseDto();
		searchResponseDto.setResult(dtos);
		searchResponseDto.setTotalRecord(roleCount);
		searchResponseDto.setSlotSize(slotSize);
		searchResponseDto.setStartIndex(startIndex);
		responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
		responseBean.setBody(searchResponseDto);
		return responseBean;
	}

	@Override
	public ResponseBeanDto<List<RoleDto>> getAllRoles() throws UserBusinessException, UserDaoException {
		ResponseBeanDto<List<RoleDto>> responseBean = new ResponseBeanDto<>();
		ResponseDto responseDto = new ResponseDto();		
		List<RoleDto> roles=new ArrayList<>();	
		
		List<RoleDto> dtos = userService.getRole();
		if(dtos!=null)
		{
		for (RoleDto role : dtos) {	
				Integer roleId=role.getRoleId();
				List<Role> subRoleList = userService.findSubRoleList(roleId);
				if(subRoleList.size()>0 ){
					RoleDto roleObj = new RoleDto();
					BeanUtils.copyProperties(role, roleObj);
					List<RoleDto> roleDtoList=new ArrayList<>();
					for (Role subRole : subRoleList) {
						RoleDto roleDto=new RoleDto();
						BeanUtils.copyProperties(subRole, roleDto);	
						roleDtoList.add(roleDto);
						
					}
					roleObj.setSubRoleList(roleDtoList);
					
					roles.add(roleObj);
				}
				
				
			}
		
		}
		
		responseDto.setResponseCode(200);
		responseDto.setResponseDescription("successfully fetched data");
		responseBean.setResponseDto(responseDto);
		
		//responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
		responseBean.setBody(roles);
		return responseBean;
	}

	@Override
	public ResponseBeanDto<List<RoleDto>> getRolesWithRoleIds() throws UserBusinessException, UserDaoException {
		ResponseBeanDto<List<RoleDto>> responseBean = null;				
		List<RoleDto> dtos = userService.getRolesWithRoleIds();		
		responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
		responseBean.setBody(dtos);
		return responseBean;
	}

	@Override
	public ResponseBeanDto<List<RoleDto>> getSubRolesWithRoleIds(int parentRoleId) throws UserBusinessException, UserDaoException {
		ResponseBeanDto<List<RoleDto>> responseBean = null;				
		List<RoleDto> dtos = userService.getSubRolesWithRoleIds(parentRoleId);		
		responseBean = UserResponseUtils.getUserResponse(HttpStatus.OK);
		responseBean.setBody(dtos);
		return responseBean;
	}
}