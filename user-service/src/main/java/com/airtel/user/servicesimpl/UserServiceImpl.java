package com.airtel.user.servicesimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Service;


import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.constant.UserConstants;
import com.airtel.user.helper.dto.BaseDto;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.PermissionDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.UserDetailsDto;
import com.airtel.user.helper.dto.UserIdDetailsDto;
import com.airtel.user.helper.dto.UserImageDetailDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.config.dao.UserDaoService;
import com.airtel.user.persistence.entities.ClientOauthDetails;
import com.airtel.user.persistence.entities.Department;
import com.airtel.user.persistence.entities.Permission;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.entities.UserDetails;
import com.airtel.user.persistence.entities.UserIdDetails;
import com.airtel.user.persistence.entities.UserImageDetail;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;
import com.airtel.user.services.UserService;

@Service
@Qualifier("uerService")
public class UserServiceImpl implements UserService {

	@Qualifier("userDaoSession")
	@Autowired
	private UserDaoService userDaoService;
	
	@Autowired
	private JdbcClientDetailsService clientDetails;
	
	/*@Qualifier("userOauthDetailsRepository")
	@Autowired
	private UserOauthDetailsRepository userOauthDetailsRepository;
	*/
	@Override
	public BaseDto addUser(UsersDto usersDto) throws UserBusinessException {
		// TODO Auto-generated method stub
		Users users=new Users();		
		BeanUtils.copyProperties(usersDto,users);
		
		List<UserDetails> userDetailsList=new ArrayList<>();
		
		List<UserDetailsDto> UserDetailsDtoList=usersDto.getUserDetails();
		
			
		for (UserDetailsDto userDetailsDto : UserDetailsDtoList) {
			
			UserDetails userDetails=new UserDetails	();
			BeanUtils.copyProperties(usersDto.getUserDetails(),userDetails);
			
			Set<UserIdDetails> userIdDetails=new HashSet<>();
			if(userDetailsDto.getUserIdDetail()!=null)
				for (UserIdDetailsDto userIdDetailsDto : userDetailsDto.getUserIdDetail()) {
					UserIdDetails userIdDetail=new UserIdDetails();
					BeanUtils.copyProperties(userIdDetailsDto,userIdDetail);
					userIdDetail.setUserDetails(userDetails);
					userIdDetails.add(userIdDetail);
				}
			userDetails.setUserIdDetail(userIdDetails);
			
			Set<UserImageDetail> userImageDetails=new HashSet<>();
			if(userDetailsDto.getUserImageDetail()!=null)
				for (UserImageDetailDto userImageDetailDto : userDetailsDto.getUserImageDetail()) {
					UserImageDetail userImageDetail=new UserImageDetail();
					BeanUtils.copyProperties(userImageDetailDto,userImageDetail);
					userImageDetail.setUserDetails(userDetails);
					userImageDetails.add(userImageDetail);
				}
			userDetails.setUserImageDetail(userImageDetails);
			
			
			List<Department> userDepartment=new ArrayList<>();
			if(userDetailsDto.getUserDepartment()!=null)
				for (DepartmentDto departmentDto : userDetailsDto.getUserDepartment()) {
					Department department=new Department();
					BeanUtils.copyProperties(departmentDto,department);
					userDepartment.add(department);
				}
			userDetails.setUserDepartment(userDepartment);
			
			
			List<Permission> userPermission=new ArrayList<>();
			if(userDetailsDto.getUserPermission()!=null)
				for (PermissionDto permissionDto : userDetailsDto.getUserPermission()) {
					Permission permission=new Permission();
					BeanUtils.copyProperties(permissionDto,permission);
					userPermission.add(permission);
				}
			userDetails.setUserPermission(userPermission);
			
			userDetails.setUsers(users);
			
			userDetailsList.add(userDetails);

		}
		
		List<Role> roles=new ArrayList<>();
		if(usersDto.getRoles()!=null)
			for (RoleDto roleDto : usersDto.getRoles()) {
				Role role=new Role();
				BeanUtils.copyProperties(roleDto,role);
				if(roleDto.getRoleId()==22 || roleDto.getRoleId()==37 || roleDto.getRoleId()==38 || roleDto.getRoleId()==39
				|| roleDto.getRoleId()==40  || roleDto.getRoleId()==41 || roleDto.getRoleId()==42 || roleDto.getRoleId()== 43)
				{
					role.setMobileAccessFlag(1);
				}
				else{
					role.setMobileAccessFlag(0);	
				}
				role.setWebAccessFlag(1);
				roles.add(role);
			}
		users.setRoles(roles);
						
		users.setUserDetails(userDetailsList);
				
		/*List<Role> roles=new ArrayList<>();
		for (RoleDto roleDto : usersDto.getRoles()) {
			Role role=new Role();
			BeanUtils.copyProperties(roleDto,role);
			roles.add(role);
		}
		users.setRoles(roles);*/
		
		
		if(users.getUserId()!=null)
		{
			users.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				users.setUpdatedBy(auth.getName());
			}
		}
		else
		{
			users.setDeleteFlag(UserConstants.FALSE);
			users.setStatusFlag(UserConstants.TRUE);
			users.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				users.setCreatedBy(auth.getName());
			}
			
		}
		
		try {
			
			users =(Users) userDaoService.saveOrUpdateEntity(users);	
			
		} catch (UserDaoException cause) {
			// TODO Auto-generated catch block
			throw new UserBusinessException(cause);
		}
		return usersDto;
	}

	@Override
	public BaseDto addRole(RoleDto roleDto) throws UserBusinessException {
		// TODO Auto-generated method stub
		Role role=new Role();
		try {
			BeanUtils.copyProperties(roleDto,role);
			role.setUserRoleName("ROLE_"+roleDto.getRoleName());
			
			if(role.getRoleId()!=null)
			{
				role.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null) {
					role.setUpdatedBy(auth.getName());
				}
				
				if(roleDto.getStatusFlag()==UserConstants.TRUE)
				{
					role.setDeleteFlag(UserConstants.FALSE);
					role.setStatusFlag(UserConstants.TRUE);
				}
				else
				{
					role.setDeleteFlag(UserConstants.TRUE);
					role.setStatusFlag(UserConstants.FALSE);
				}
			}
			else
			{
				role.setDeleteFlag(UserConstants.FALSE);
				role.setStatusFlag(UserConstants.TRUE);
				role.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null) {
					role.setCreatedBy(auth.getName());
				}
				
			}
			role=(Role) userDaoService.saveOrUpdateEntity(role);
			BeanUtils.copyProperties(role, roleDto);
		}
		catch (UserDaoException cause) {
			// TODO Auto-generated catch block
			throw new UserBusinessException("Role "+cause.getMessage());
		}
		return roleDto;
	}

	@Override
	public BaseDto addUserRole(UsersDto usersDto) throws UserBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDto getUserByUserName(String userName) throws UserBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDto getRoleById(Integer roleId) throws UserBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDto addPermission(PermissionDto permissionDto) throws UserBusinessException {
		// TODO Auto-generated method stub
		Permission permission=new Permission();
		try {
			BeanUtils.copyProperties(permissionDto,permission);
			
			if(permission.getPermissionId()!=null)
			{
				permission.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null) {
					permission.setUpdatedBy(auth.getName());
				}
			}
			else
			{
				permission.setDeleteFlag(UserConstants.FALSE);
				permission.setStatusFlag(UserConstants.TRUE);
				permission.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null) {
					permission.setCreatedBy(auth.getName());
				}
				
				if(permissionDto.getStatusFlag()==UserConstants.TRUE)
				{
					permission.setDeleteFlag(UserConstants.FALSE);
					permission.setStatusFlag(UserConstants.TRUE);
				}
				else
				{
					permission.setDeleteFlag(UserConstants.TRUE);
					permission.setStatusFlag(UserConstants.FALSE);
				}
				
			}
			permission=(Permission) userDaoService.saveOrUpdateEntity(permission);
			BeanUtils.copyProperties(permission, permissionDto);
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
			throw new UserBusinessException("Permission "+cause.getMessage());
		}
		return permissionDto;
	}

	@Override
	public DepartmentDto addDepartment(DepartmentDto departmentDto) throws UserBusinessException {
		// TODO Auto-generated method stub
		Department department=new Department();
		try {
			//BeanUtils.copyProperties(departmentDto,department);
			
			department.setDepartmentId(departmentDto.getDepartmentId());
			department.setDepartmentName(departmentDto.getDepartmentName());
			department.setDescription(departmentDto.getDescription());
			
			
			if(department.getDepartmentId()!=null)
			{
				department.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null) {
					department.setUpdatedBy(auth.getName());
				}
				
				if(departmentDto.getStatusFlag()==UserConstants.TRUE)
				{
					department.setDeleteFlag(UserConstants.FALSE);
					department.setStatusFlag(UserConstants.TRUE);
				}
				else
				{
					department.setDeleteFlag(UserConstants.TRUE);
					department.setStatusFlag(UserConstants.FALSE);
				}
				
			}
			else
			{
				department.setDeleteFlag(UserConstants.FALSE);
				department.setStatusFlag(UserConstants.TRUE);
				department.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if (auth != null) {
					department.setCreatedBy(auth.getName());
				}
				
			}
			
			department=(Department) userDaoService.saveOrUpdateEntity(department);
			BeanUtils.copyProperties(department, departmentDto);
			
			
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
			throw new UserBusinessException("Department "+cause.getMessage());
		}
		return departmentDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UsersDto loadUserByName(String userName) throws UserBusinessException {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> parameterMap=new HashMap<>();
		parameterMap.put("userName", userName);
		List<Users>	userList=new ArrayList<>();	
		List<Users>	userObj=new ArrayList<>();	
		Users users=new Users();
		UsersDto usersDto=null;
		
		String client_id=userName;
		ClientDetails clientDetailsObj = null;
		boolean isAuuid=false;
		
		try {
			
			userList=(List<Users>) userDaoService.findByCriteria(Users.class, parameterMap);
			if(userList.size()<1){
				
				userObj=(List<Users>) userDaoService.findByAuuId(userName);
				
				
				clientDetailsObj = clientDetails.loadClientByClientId(client_id);
				isAuuid=true;
				usersDto=new UsersDto();
				
				if(isAuuid)
					usersDto.setAuuIdFlag(isAuuid);
				
				
				usersDto.setIsPasswordModified(userObj.get(0).getIsPasswordModified());
				usersDto.setPassword(clientDetailsObj.getClientSecret());
				usersDto.setUserName(clientDetailsObj.getClientId());
				//usersDto.setPassword(userObj.get(0).getPassword());
				//usersDto.setUserName(userObj.get(0).getAuuid());
                List<Role> roleList=userObj.get(0).getRoles();
				
				if(roleList!=null)
				{
					List<RoleDto> roleDtoList=new ArrayList<>();
					for (Role role : roleList) {
						RoleDto roleDto=new RoleDto();
						BeanUtils.copyProperties(role,roleDto);
						roleDtoList.add(roleDto);
					}
					usersDto.setRoles(roleDtoList);
					
				}
				
			}
			
			
			//userList=(List<Users>) userOauthDetailsRepository.loadUserByName(userName);	
						
			if(userList!=null & userList.size()>0)
			{
				usersDto=new UsersDto();
			
				users=userList.get(0);
				BeanUtils.copyProperties(users,usersDto);
				usersDto.setIsPasswordModified(users.getIsPasswordModified());
				usersDto.setUserDetails(null);
				
				List<Role> roleList=users.getRoles();
				
				if(roleList!=null)
				{
					List<RoleDto> roleDtoList=new ArrayList<>();
					for (Role role : roleList) {
						RoleDto roleDto=new RoleDto();
						BeanUtils.copyProperties(role,roleDto);
						roleDtoList.add(roleDto);
					}
					usersDto.setRoles(roleDtoList);
					
				}
				
				List<UserDetails> userDetails=users.getUserDetails();	
				List<UserDetailsDto> userDetailsDtoList=null;
				if(userDetails!=null)
				{
					userDetailsDtoList=new ArrayList<>();
					for (UserDetails userDetailsData : userDetails) {
						UserDetailsDto userDetailsDtoData=new UserDetailsDto();
						BeanUtils.copyProperties(userDetailsData,userDetailsDtoData);
						userDetailsDtoList.add(userDetailsDtoData);
					}
					usersDto.setUserDetails(userDetailsDtoList);
				}
				
			}
				
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
		}
		//usersDto.setAuuIdFlag(isAuuid);
		return usersDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public UsersDto loadUserByAuuid(String auuid) throws UserBusinessException {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> parameterMap=new HashMap<>();
		parameterMap.put("auuid", auuid);
		List<Users>	userList=new ArrayList<>();	
		Users users=new Users();
		UsersDto usersDto=null;
		
		
		
		try {
			
			userList=(List<Users>) userDaoService.findByCriteria(Users.class, parameterMap);	
			//userList=(List<Users>) userOauthDetailsRepository.loadUserByName(userName);	
						
			if(userList!=null & userList.size()>0)
			{
				usersDto=new UsersDto();
				users=userList.get(0);
				BeanUtils.copyProperties(users,usersDto);
				usersDto.setIsPasswordModified(users.getIsPasswordModified());
				usersDto.setUserDetails(null);
				
				List<Role> roleList=users.getRoles();
				
				if(roleList!=null)
				{
					List<RoleDto> roleDtoList=new ArrayList<>();
					for (Role role : roleList) {
						RoleDto roleDto=new RoleDto();
						BeanUtils.copyProperties(role,roleDto);
						roleDtoList.add(roleDto);
					}
					usersDto.setRoles(roleDtoList);
					
				}
				
				List<UserDetails> userDetails=users.getUserDetails();	
				List<UserDetailsDto> userDetailsDtoList=null;
				if(userDetails!=null)
				{
					userDetailsDtoList=new ArrayList<>();
					for (UserDetails userDetailsData : userDetails) {
						UserDetailsDto userDetailsDtoData=new UserDetailsDto();
						BeanUtils.copyProperties(userDetailsData,userDetailsDtoData);
						userDetailsDtoList.add(userDetailsDtoData);
					}
					usersDto.setUserDetails(userDetailsDtoList);
				}
				
			}
				
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
		}
		return usersDto;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getRole(HashMap hashMap) throws UserBusinessException {
		// TODO Auto-generated method stub
		List<RoleDto> dtos = new ArrayList<>();
		List<Role>	roleList=new ArrayList<>();	
		try {
			roleList=(List<Role>) userDaoService.findByCriteria(Role.class, hashMap);	
			if(roleList!=null & roleList.size()>0)
			{
				for(Role role : roleList) {
					RoleDto temp = new RoleDto();
					BeanUtils.copyProperties(role, temp);
					dtos.add(temp);
				}
			}
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
		}
		return dtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseDto> getAllRole(RoleDto roleDto,int slotSize,int startIndex) throws UserBusinessException {
		List<BaseDto> dtos = new ArrayList<>();
		List<Role>	roleList=new ArrayList<>();	
		try {
			roleList=(List<Role>) userDaoService.findByCriteria(Role.class, null,slotSize,startIndex);	
			if(roleList!=null & roleList.size()>0)
			{
				for(Role role : roleList) {
					BaseDto temp = new RoleDto();
					BeanUtils.copyProperties(role, temp);
					dtos.add(temp);
				}
			}
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
		}
		return dtos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getUserCount() throws UserBusinessException {
		long userCount=0L;
		try {
			userCount= userDaoService.findByCriteria(Users.class);
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
		}
		return userCount;
	}

	@Override
	public long getRoleCount() throws UserBusinessException {
		long roleCount=0L;
		try {
			roleCount= userDaoService.findByCriteria(Role.class);
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
		}
		return roleCount;
	}

	@Override
	public long getDepartmentCount() throws UserBusinessException {
		long deptCount=0L;
		try {
			deptCount= userDaoService.findByCriteria(Department.class);
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
		}
		return deptCount;
	}

	@Override
	public List<BaseDto> getAllDepartments(DepartmentDto departmentDto, int slotSize, int startIndex)
			throws UserBusinessException {
		List<BaseDto> dtos = new ArrayList<>();
		List<Department>	departmentList=new ArrayList<>();	
		try {
			departmentList=(List<Department>) userDaoService.findByCriteria(Department.class, null,slotSize,startIndex);	
			if(departmentList!=null & departmentList.size()>0)
			{
				for(Department department : departmentList) {
					BaseDto temp = new RoleDto();
					BeanUtils.copyProperties(department, temp);
					dtos.add(temp);
				}
			}
		}catch (UserDaoException cause) {
			// TODO Auto-generated catch block
		}
		return dtos;
	}

	

	@Override
	public List<Role> findSubRoleList(Integer roleId) throws UserDaoException {
		List<Role>departmentList=userDaoService.findSubRoleList(roleId);
		return departmentList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getRole() throws UserBusinessException {
		List<RoleDto>roleListDto=new ArrayList<>();
		List<Role>roleList=new ArrayList<>();
		try {
			roleList=userDaoService.findRoleList();
			for (Role role : roleList) {
				RoleDto roleDto=new RoleDto();
				BeanUtils.copyProperties(role, roleDto);
				roleListDto.add(roleDto);
				
			}
			
		} catch (UserDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleListDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getRolesWithRoleIds() throws UserBusinessException, UserDaoException {
		List<RoleDto>roleListDto=new ArrayList<>();
		List<Role>roleList=new ArrayList<>();
		try {
			roleList=userDaoService.getRolesWithRoleIds();
			for (Role role : roleList) {
				RoleDto roleDto=new RoleDto();
				BeanUtils.copyProperties(role, roleDto);
				roleListDto.add(roleDto);
				
			}
			
		} catch (UserDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleListDto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getSubRolesWithRoleIds(int parentRoleId) throws UserBusinessException, UserDaoException {
		List<RoleDto>roleListDto=new ArrayList<>();
		List<Role>roleList=new ArrayList<>();
		try {
			roleList=userDaoService.getSubRolesWithRoleIds(parentRoleId);
			for (Role role : roleList) {
				RoleDto roleDto=new RoleDto();
				BeanUtils.copyProperties(role, roleDto);
				roleListDto.add(roleDto);
				
			}
			
		} catch (UserDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleListDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getAllRoles() throws UserBusinessException, UserDaoException {
		List<RoleDto> roleDtoList=new ArrayList<>();
		List<RoleDto> parentRoleListDto=new ArrayList<>();		
		List<Role> dtos = userDaoService.findRoleList();
		if(dtos!=null)
		{
		for (Role role : dtos) {	
			RoleDto parentRoleDto=new RoleDto();  
			BeanUtils.copyProperties(role, parentRoleDto);	
				Integer roleId=role.getRoleId();
				List<Role> subRoleList = userDaoService.findSubRoleList(roleId);
				if(subRoleList.size()>0 ){
					roleDtoList=new ArrayList<>();
					for (Role subRole : subRoleList) {
						RoleDto roleDto=new RoleDto();
						BeanUtils.copyProperties(subRole, roleDto);				
						roleDtoList.add(roleDto);
						
					}
					parentRoleDto.setSubRoleList(roleDtoList);
					parentRoleListDto.add(parentRoleDto);
				}
			}
		}
		return parentRoleListDto;
	}

	@Override
	public void updateUser(UsersDto user) throws UserBusinessException, UserDaoException {
		HashMap<String, Object> parameterMap=new HashMap<>();
		parameterMap.put("auuid", user.getAuuId());
		Users userObj=null;		
		try {			
			userObj= (Users) userDaoService.getEntityByCriteria(Users.class, parameterMap);
			if(userObj!=null){
				String password = userObj.getPassword();
				BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(UserConstants.PASSWORD_LENGTH);
				password = cryptPasswordEncoder.encode(password);
				userObj.setPassword(password);	
				userObj.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				userDaoService.updateEntity(userObj);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateClientOuathDeatils(UsersDto user) {
		HashMap<String, Object> parameterMap=new HashMap<>();
		parameterMap.put("client_id", user.getAuuId());
		ClientOauthDetails clientOauthDetailsObj=null;		
		try {			
			clientOauthDetailsObj= (ClientOauthDetails) userDaoService.getEntityByCriteria(ClientOauthDetails.class, parameterMap);
			if(clientOauthDetailsObj!=null){
				String password = clientOauthDetailsObj.getClient_secret();
				BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(UserConstants.PASSWORD_LENGTH);
				password = cryptPasswordEncoder.encode(password);
				clientOauthDetailsObj.setClient_secret(password);				
				userDaoService.updateEntity(clientOauthDetailsObj);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleDto> getAllRoleList() throws UserBusinessException, UserDaoException {
		List<RoleDto> roleDtoList=new ArrayList<>();
				
		List<Role> role = userDaoService.findRole();
		if(role!=null)
		{
			for (Role roleList : role) {
				RoleDto roleObj=new RoleDto();
				roleObj.setUserRoleName(roleList.getUserRoleName());
				roleObj.setRoleId(roleList.getRoleId());
				roleDtoList.add(roleObj);
			}
			
		}
		return roleDtoList;
	}

	@Override
	public List<RoleDto> getSubRoleList(UsersDto usersDto) throws UserBusinessException, UserDaoException {
		List<RoleDto> roleDtoList=new ArrayList<>();
		
		Users  userObj = userDaoService.findRoleByUserName(usersDto.getUserName());
		if(userObj!=null){
			RoleDto roleDto=new RoleDto();
			roleDto.setRoleId(userObj.getRoles().get(0).getRoleId());
			roleDtoList.add(roleDto);
		}	
		
		return roleDtoList;
	}

	
	
}
