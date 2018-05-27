package com.airtel.kyc.business.service.impl;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.business.service.UserManagementService;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.enumData.UserExceptionCodes;
import com.airtel.kyc.helpers.dto.NotificationDto;
import com.airtel.kyc.helpers.dto.UserSearchResponseDto;
import com.airtel.kyc.integration.exception.IntegrationServiceException;
import com.airtel.kyc.integration.service.IntegrationService;
import com.airtel.kyc.repository.dao.KycDaoService;
import com.airtel.kyc.repository.dao.SubscriberDetailsDao;
import com.airtel.kyc.repository.dao.SubscriberIdDetailsDao;
import com.airtel.kyc.repository.dao.TemplatesDao;
import com.airtel.kyc.repository.dao.UserDetailsDao;
import com.airtel.kyc.repository.dao.UserDistrictDao;
import com.airtel.kyc.repository.dao.UserIdDetailsDao;
import com.airtel.kyc.repository.dao.UserProvinceDao;
import com.airtel.kyc.repository.dao.UserVillageDao;
import com.airtel.kyc.repository.entity.Branch;
import com.airtel.kyc.repository.entity.Cluster_;
import com.airtel.kyc.repository.entity.Location;
import com.airtel.kyc.repository.entity.Site;
import com.airtel.kyc.repository.entity.Subscriber;
import com.airtel.kyc.repository.entity.SubscriberDetails;
import com.airtel.kyc.repository.entity.SubscriberIdDetails;
import com.airtel.kyc.repository.entity.SubscriberUser;
import com.airtel.kyc.repository.entity.Templates;
import com.airtel.kyc.repository.entity.UserDistrict;
import com.airtel.kyc.repository.entity.UserProvince;
import com.airtel.kyc.repository.entity.UserVillage;
import com.airtel.kyc.repository.entity.Zone;
import com.airtel.kyc.repository.exception.KycDaoException;
import com.airtel.kyc.security.web.AesUtil;
import com.airtel.kyc.utils.DateUtils;
import com.airtel.kyc.utils.Utility;
import com.airtel.user.helper.constant.ConfigConstants;
import com.airtel.user.helper.constant.UserConstants;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.DistrictIds;
import com.airtel.user.helper.dto.LocationDto;
import com.airtel.user.helper.dto.ProvinceIds;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.SearchParamDto;
import com.airtel.user.helper.dto.UserDetailsDto;
import com.airtel.user.helper.dto.UserIdDetailsDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.helper.dto.VillageIds;
import com.airtel.user.helper.util.MaskUtils;
import com.airtel.user.persistence.config.dao.UserDaoService;
import com.airtel.user.persistence.config.dao.UserRepositry;
import com.airtel.user.persistence.entities.ClientOauthDetails;
import com.airtel.user.persistence.entities.Department;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.entities.UserDetails;
import com.airtel.user.persistence.entities.UserIdDetails;
import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

@Service
public class UserManagementServiceImpl implements UserManagementService, ConfigConstants {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementServiceImpl.class);
	private static final String SECURITY_CODE_EXPIRY_TIME = "security.code.expiry.time";
	private static final String SECURITY_CODE_EXPIRED = "security.code.expired";
	private static final String SECURITY_CODE_VALIDATED = "security.code.validated";
	private static final String SECURITY_CODE_INVALID = "security.code.invalid";
	public static final String SMS_OTP_USER = "SMS_OTP_USER";
	public static final String SMS_OTP_USER_CREATION = "SMS_OTP_USER_CREATION";
	
	
	@Qualifier("userRepositry")
	@Autowired
	private UserRepositry userRepositry;

	@SuppressWarnings("rawtypes")
	@Qualifier("userDaoSession")
	@Autowired
	private UserDaoService userDaoService;

	@SuppressWarnings("rawtypes")
	@Autowired
	private KycDaoService kycDaoService;
	
	@Autowired
	private IntegrationService integrationService;
	
	@Autowired
	private SubscriberDetailsDao subscriberDetailsDao;
	

	@Autowired
	private SubscriberIdDetailsDao subscriberIdDetailsDao;
	
	
	@Autowired
	private UserProvinceDao userProvinceDao;
	
	
	@Autowired
	private UserDistrictDao userDistrictDao;
	
	@Autowired
	private UserIdDetailsDao UserIdDetailsDao;
	
	@Autowired
	UserDetailsDao userDetailsDao;
	
	@Autowired
	private UserVillageDao userVillageDao;
	
	@Autowired
	private TemplatesDao templatesDao;
	

	@Autowired
	private Environment env;
	
	/*@Autowired
	private MessageSource messageSource;*/
	
	@Autowired
	private AesUtil aesUtil;
	

	@SuppressWarnings("unchecked")
	@Override
	public UserSearchResponseDto searchUser(SearchParamDto param, int slot, int startIndex) throws UserException {
		UserSearchResponseDto response = new UserSearchResponseDto();
		// String query = "from Users";
		List<UsersDto> usersDtoList = new ArrayList<UsersDto>();
		List<Users> usersList = null;
		try {
			usersList = (List<Users>) this.userRepositry.searchUser(param, slot, startIndex);
			//usersList = userDaoService.searchUsers(param, slot, startIndex);
			for (Users user : usersList) {
				UsersDto usersDto = new UsersDto();
				usersDto.setUserId(user.getUserId());
				usersDto.setUserName(user.getUserName());
				usersDto.setIsBlocked(user.getIsBlocked());
				usersDto.setCreatedOn(user.getCreatedOn());
				usersDto.setUserTransactionId(user.getUserTransactionId());
				List<RoleDto> roleList=new ArrayList<>();
				for (Role users : user.getRoles()) {
					RoleDto roleDto=new RoleDto();					
					Role role=this.userRepositry.getRoleByUserId(users.getRoleId());
					if(role!=null)
					{
						Role parentRole=this.userRepositry.getRoleByUserId(role.getParentRoleId());
						if(parentRole!=null)
						{
							roleDto.setRoleId(parentRole.getRoleId());
							roleDto.setRoleName(parentRole.getRoleName());
						}						
						
						roleDto.setSubRoleId(role.getRoleId());
						roleDto.setSubRoleName(role.getRoleName());
						
						roleList.add(roleDto);
					}
					
				}
				UserProvince provinceId=null;
				UserDistrict districtId=null;
				UserVillage villageId=null;
				try {
					provinceId=kycDaoService.getUserProvince(user.getUserId());
				} catch (KycDaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				try {
					districtId=kycDaoService.getUserDistrict(user.getUserId());
				} catch (KycDaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					villageId=kycDaoService.getUserVillage(user.getUserId());
				} catch (KycDaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				LocationDto location=new LocationDto();
				List<ProvinceIds> provinceIds =new ArrayList<>();
				ProvinceIds province =new ProvinceIds();
				DistrictIds districts=new DistrictIds();
				VillageIds villages=new VillageIds();
				if(villageId!=null){
				villages.setVillageId(villageId.getVillageId());
				}
				if(districtId!=null){
				districts.setDistrictId(districtId.getDistrictId());
				districts.setVillageIds(villages);
				}
				if(provinceId!=null){
				province.setProvinceId(provinceId.getProvinceId());
				province.setDistricts(districts);
				provinceIds.add(province);
				}
				
				if(provinceIds!=null && provinceIds.size()>0){
					//provinceIds.add(province);
					location.setProvinceIds(provinceIds);
					usersDto.setLocation(location);
				}
				
				
				usersDto.setRoles(roleList);
				//usersDto.setRoles(user.getRoles());
				//String[] ignoreProperties = {roles,};
				//BeanUtils.copyProperties(user, usersDto, ignoreProperties);

				List<UserDetailsDto> userDetailsDtoList = getUserDetails(usersDto, KycConstants.FALSE);
				//usersDto.setUserDetails(userDetailsDtoList);
				usersDto.setPassword(null);

				/*LocationDto locationDto = getLocataion(usersDto.getUserId());
				if (locationDto != null)
					usersDto.setLocation(getLocataion(usersDto.getUserId()));*/

				if (userDetailsDtoList != null && userDetailsDtoList.size() > 0) {
					usersDto.setUserDetail(userDetailsDtoList.get(0));
					usersDto.setUserDetails(userDetailsDtoList);
				}
				usersDtoList.add(usersDto);
			}
			long totalCount = this.userRepositry.getTotalUser(param);
			response.setSlot(slot);
			response.setStartIndex(startIndex);
			response.setTotalRecord(totalCount);
			response.setResult(usersDtoList);
		} catch (UserDaoException e) {
			e.printStackTrace();
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return response;
	}
	@Override
	public List<UserDetailsDto> getUserDetails(UsersDto usersDto, Integer isActive) throws UserException {
		// TODO Auto-generated method stub
		List<UserDetailsDto> userDetailsDtoList = new ArrayList<UserDetailsDto>();
		List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		try {
			userDetailsList = (List<UserDetails>) this.userRepositry.getUserDetails(usersDto.getUserId(), isActive);
			for (UserDetails userDetails : userDetailsList) {
				UserDetailsDto userDetailsDto = new UserDetailsDto();
				BeanUtils.copyProperties(userDetails, userDetailsDto);
				userDetailsDto.setDob(DateUtils.getFormatDate(userDetails.getDob(),"dd-MM-yyyy"));
				userDetailsDto.setAuuid(userDetails.getAuuid());
				userDetailsDto.setCommissionMobileNo(userDetails.getCommissionMobileNo());
				userDetailsDto.setDistributorId(userDetails.getCommissionMobileNo());
				userDetailsDto.setDistributorMsisdn(userDetails.getDistributorMsisdn());
				userDetailsDto.setDistributorName(userDetails.getDistributorName());
				if(userDetails.getPermissableRadius()==null){
					userDetailsDto.setPermissableRadius("0");
				}
				
				/*if(userDetails.getEditInfoAccess()==null || userDetails.getEditInfoAccess()==1){
					userDetailsDto.setEditInfoAccess("active");
				}
				else if(userDetails.getEditInfoAccess()==null || userDetails.getEditInfoAccess()==0){
					userDetailsDto.setEditInfoAccess("inactive");
				}*/
				userDetailsDto.setEditInfoAccess("inactive");
				if(userDetails.getSimSwapAccess()==null || userDetails.getSimSwapAccess()==1){
					userDetailsDto.setSimSwapAccess("active");
				}
				else if(userDetails.getSimSwapAccess()==null || userDetails.getSimSwapAccess()==0){
					userDetailsDto.setSimSwapAccess("inactive");
				}
				List<UserIdDetailsDto>  userIdDetailsDtoList=new ArrayList<>();
				List<UserIdDetails> idDetails=(List<UserIdDetails>) this.userRepositry.getUserIdDetails(userDetails.getUserDetailsId(), isActive);
				if(idDetails!=null && idDetails.size()>0){
					for (UserIdDetails userIdDetails : idDetails) {
						UserIdDetailsDto  userIdDetailsDto=new UserIdDetailsDto();
						userIdDetailsDto.setIdImage(userIdDetails.getIdImageName());
						userIdDetailsDto.setIdPath(userIdDetails.getIdPath());	
						userIdDetailsDtoList.add(userIdDetailsDto);
					}
					
				}
				Set<UserIdDetailsDto>  userIdDetailsDtoSet=new HashSet<>();
				userIdDetailsDtoSet.addAll(userIdDetailsDtoList);
				
				userDetailsDto.setUserIdDetail(userIdDetailsDtoSet);
				userDetailsDtoList.add(userDetailsDto);
				
				
			}
		} catch (UserDaoException e) {
			e.printStackTrace();
		}
		return userDetailsDtoList;
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public LocationDto getLocataion(Integer uid) throws UserException {
		LocationDto locationDto = new LocationDto();
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("userId", uid);
			params.put("isOldRecord", KycConstants.FALSE);
			Location location = (Location) this.kycDaoService.getEntityByCriteria(Location.class, params);
			if (location == null)
				return null;
			BeanUtils.copyProperties(location, locationDto);

			params = new HashMap<>();
			params.put("zoneId", location.getZoneId());
			Zone zone = (Zone) this.kycDaoService.getEntityByCriteria(Zone.class, params);
			locationDto.setZoneName(zone.getZoneName());

			params = new HashMap<>();
			params.put("branchId", location.getBranchId());
			Branch branch = (Branch) this.kycDaoService.getEntityByCriteria(Branch.class, params);
			locationDto.setBranchName(branch.getBranchName());

			if (location.getClusterId() != null) {
				params = new HashMap<>();
				params.put("clusterId", location.getClusterId());
				Cluster_ cluster_ = (Cluster_) this.kycDaoService.getEntityByCriteria(Cluster_.class, params);
				locationDto.setClusterName(cluster_.getClusterName());
			}
			if (location.getSiteId() != null) {
				params = new HashMap<>();
				params.put("id", location.getSiteId());
				Site site = (Site) this.kycDaoService.getEntityByCriteria(Site.class, params);
				locationDto.setSiteName(site.getSiteName());
			}
		} catch (KycDaoException e) {
			e.printStackTrace();
		}
		return locationDto;
	}*/
	
	@Override
	public UsersDto provisionUser(UsersDto usersDto) throws UserException, IOException {
		// UserDto dto = new UserDto();
		String txnId=usersDto.getUserTransactionId();
		Users users = new Users();
		BeanUtils.copyProperties(usersDto, users);
		try {
			List<UserDetails> detailsList = new ArrayList<UserDetails>();
			Users userObj=this.userRepositry.getUserByMsisdn(usersDto.getUserDetail().getMsisdn());
			//UserDetails userDetail=this.userRepositry.getUserDetailByMsisdn(usersDto.getUserDetail().getMsisdn());
			UserDetails userDetail=userDetailsDao.getUserIdDetails(usersDto.getUserDetail().getMsisdn(), KycConstants.FALSE);
			//userDetail.setFirstName(usersDto.getUserDetail().getFirstName());
			UserDetails oldUserDetails=new UserDetails();
			if(userDetail!=null){
				oldUserDetails = userDetail;
				
				BeanUtils.copyProperties(oldUserDetails, userDetail);
				userDetail.setUserDetailsId(null);
				userDetail.setIsOldUserDetails(KycConstants.FALSE);
				userDetail.setStatusFlag(KycConstants.TRUE);

				oldUserDetails.setIsOldUserDetails(KycConstants.TRUE);
				oldUserDetails.setStatusFlag(KycConstants.FALSE);
				
				userDetail.setFirstName(usersDto.getUserDetail().getFirstName());
				userDetail.setLastName(usersDto.getUserDetail().getLastName());
				userDetail.setEmail(usersDto.getUserDetail().getEmail());
				if(usersDto.getUserDetail().getAuuid()==null){
					userDetail.setAuuid(usersDto.getAuuId());
				}
				else{
					userDetail.setAuuid(usersDto.getUserDetail().getAuuid());
				}				
				userDetail.setDob(DateUtils.getDate(usersDto.getUserDetail().getDob(),"dd-MM-yyyy"));
				userDetail.setIdCardNo(usersDto.getUserDetail().getIdCardNo());
				userDetail.setDistributorName(usersDto.getUserDetail().getDistributorName());
				userDetail.setTsmAuuid(usersDto.getUserDetail().getTsmAuuid());
				/*if(usersDto.getUserDetail().getEditInfoAccess().equals("active") || usersDto.getUserDetail().getEditInfoAccess().equals("YES"))
				{
				   userDetail.setEditInfoAccess(1);
				}
				else if(usersDto.getUserDetail().getEditInfoAccess().equals("inactive")|| usersDto.getUserDetail().getEditInfoAccess().equals("NO") || usersDto.getUserDetail().getEditInfoAccess()==null)
				{
				   userDetail.setEditInfoAccess(0);
				}*/
				userDetail.setEditInfoAccess(0);
				if(usersDto.getUserDetail().getSimSwapAccess()!=null){
				if(usersDto.getUserDetail().getSimSwapAccess().equals("active") || usersDto.getUserDetail().getSimSwapAccess().equals("YES"))
				{
				   userDetail.setSimSwapAccess(1);
				}
				else if(usersDto.getUserDetail().getSimSwapAccess().equals("inactive") || usersDto.getUserDetail().getSimSwapAccess().equals("NO") || usersDto.getUserDetail().getSimSwapAccess()==null)
				{
				   userDetail.setSimSwapAccess(0);
				}
				else{
					userDetail.setSimSwapAccess(0);	
				}
				}
				if(usersDto.getUserDetail().getCpContract()!=null){
					userDetail.setCpContract(usersDto.getUserDetail().getCpContract());
				}
				userDetail.setChannelPartnerCellId(usersDto.getUserDetail().getChannelPartnerCellId());
				userDetail.setLatitude(usersDto.getUserDetail().getLatitude());
				userDetail.setLongitude(usersDto.getUserDetail().getLongitude());
				userDetail.setPermissableRadius(usersDto.getUserDetail().getPermissableRadius());
				userDetail.setCommissionMobileNo(usersDto.getUserDetail().getCommissionMobileNo());
				userDetail.setDistributorId(usersDto.getUserDetail().getCommissionMobileNo());
				userDetail.setDistributorMsisdn(usersDto.getUserDetail().getDistributorMsisdn());
				userDetail.setDealerCode(usersDto.getUserDetail().getDealerCode());
				
				
				if (usersDto.getUserDetail().getUserDepartment() != null) {
					List<Department> userDepartment = new ArrayList<>();
					for (DepartmentDto userD : usersDto.getUserDetail().getUserDepartment()) {
						Department department = new Department();
						BeanUtils.copyProperties(userD, department);
						userDepartment.add(department);
					}
					userDetail.setUserDepartment(userDepartment);
				}

				//userDetail.setUsers(users);
				userDetail.setIsOldUserDetails(KycConstants.FALSE);
				if(usersDto.getUserDetails()!=null){
				if(usersDto.getUserDetails().get(0).getUserIdDetail()!=null){
					
					
				
				String timestr = env.getProperty(ConfigConstants.HOST_NAME) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		       String destfilepath = env.getProperty(ConfigConstants.UPLOAD_FOLDER) + env.getProperty(ConfigConstants.USER_DIRECTORY) + timestr + "/"
				+ userObj.getUserTransactionId() + "/";
		       Boolean userImageSaveStatus = false;
		       
				Set<UserIdDetails> userIdDetails = new HashSet<>();
				if(usersDto.getUserDetails()!=null){
					if (usersDto.getUserDetails().get(0).getUserIdDetail() != null)
						for (UserIdDetailsDto userIdDetailsDto : usersDto.getUserDetails().get(0).getUserIdDetail()) {
							UserIdDetails userIdDetailsObj=new UserIdDetails();
							String userImage=userIdDetailsDto.getIdImage();
							String userImageName=userIdDetailsDto.getIdImageName();						
								
							if (userImage != null && userImage.length() > 0) {
								userImageSaveStatus = Utility.saveBase64ToFile(userImage,destfilepath, userImageName + ".jpg");
								userIdDetailsObj.setIdImageName(userImageName);
							}
							
							userIdDetailsObj.setIdPath(timestr);
							userIdDetailsObj.setUserDetails(userDetail);
							userIdDetailsObj.setIsOldIdDetails(KycConstants.FALSE);
							userIdDetailsObj.setUserDetails(userDetail);
							userIdDetails.add(userIdDetailsObj);
						}
				}
			

				userDetail.setUserIdDetail(userIdDetails);
				detailsList.add(userDetail);
				userDetail.setUsers(users);
				
				UserDetails userDetailsObjData=userDetailsDao.getUserIdDetails(usersDto.getUserDetail().getMsisdn(), KycConstants.FALSE);
				userDetailsObjData.setIsOldUserDetails(1);				
				userDetailsDao.saveOrUpdate(userDetailsObjData);
				
				List<UserIdDetails> userIdDetailsObj=userRepositry.getUserIdDetails(userDetailsObjData.getUserDetailsId(), KycConstants.FALSE);				
					for (UserIdDetails userIdDetailsList : userIdDetailsObj) {
						userIdDetailsList.setIsOldIdDetails(KycConstants.TRUE);
						UserIdDetailsDao.saveOrUpdate(userIdDetailsList);
					}
				}
				
				}
				
				
				
				
				
				

			}
			//if (usersDto.getUserDetail() != null)
			else if (userDetail == null)
			{
				UserDetails userDetails = new UserDetails();
				
				BeanUtils.copyProperties(usersDto.getUserDetail(), userDetails);
				userDetails.setDob(DateUtils.getDate(usersDto.getUserDetail().getDob(),"dd-MM-yyyy"));

				if (usersDto.getUserDetail().getUserDepartment() != null || usersDto.getUserDetail().getUserDepartment().size()>0) {
					List<Department> userDepartment = new ArrayList<>();
					for (DepartmentDto userD : usersDto.getUserDetail().getUserDepartment()) {
						Department department = new Department();
						BeanUtils.copyProperties(userD, department);
						userDepartment.add(department);
					}
					userDetails.setUserDepartment(userDepartment);
				}

				userDetails.setUsers(users);
				userDetails.setIsOldUserDetails(KycConstants.FALSE);
				/*if(usersDto.getUserDetail().getEditInfoAccess().equals("active") || usersDto.getUserDetail().getEditInfoAccess().equals("YES"))
				{
					userDetails.setEditInfoAccess(1);
				}
				else if(usersDto.getUserDetail().getEditInfoAccess().equals("inactive") || usersDto.getUserDetail().getEditInfoAccess().equals("NO") || usersDto.getUserDetail().getEditInfoAccess()==null )
				{
					userDetails.setEditInfoAccess(0);
				}*/
				userDetails.setEditInfoAccess(0);
				if(usersDto.getUserDetail().getSimSwapAccess()!=null){
				if(usersDto.getUserDetail().getSimSwapAccess().equals("active") || usersDto.getUserDetail().getSimSwapAccess().equals("YES"))
				{
					userDetails.setSimSwapAccess(1);
				}
				else if(usersDto.getUserDetail().getSimSwapAccess().equals("inactive") || usersDto.getUserDetail().getSimSwapAccess().equals("NO") || usersDto.getUserDetail().getSimSwapAccess()==null)
				{
					userDetails.setSimSwapAccess(0);
				}
				}
				
			   
			   String timestr = env.getProperty(ConfigConstants.HOST_NAME) + "/" + Utility.getCurrent_Year() + "/" + Utility.getCurrent_Month()
				+ "/" + Utility.getCurrent_day() + "/" + System.currentTimeMillis();
		       String destfilepath = env.getProperty(ConfigConstants.UPLOAD_FOLDER) + env.getProperty(ConfigConstants.USER_DIRECTORY) + timestr + "/"
				+ txnId + "/";
		       Boolean userImageSaveStatus = false;
		       
				Set<UserIdDetails> userIdDetails = new HashSet<>();
				if (usersDto.getUserDetails().get(0).getUserIdDetail() != null)
					for (UserIdDetailsDto userIdDetailsDto : usersDto.getUserDetails().get(0).getUserIdDetail()) {
						UserIdDetails userIdDetailsObj=new UserIdDetails();
						String userImage=userIdDetailsDto.getIdImage();
						String userImageName=userIdDetailsDto.getIdImageName();						
							
						if (userImage != null && userImage.length() > 0) {
							userImageSaveStatus = Utility.saveBase64ToFile(userImage,destfilepath, userImageName + ".jpg");
							userIdDetailsObj.setIdImageName(userImageName);
						}
						
						userIdDetailsObj.setIdPath(timestr);
						userIdDetailsObj.setUserDetails(userDetail);
						userIdDetailsObj.setIsOldIdDetails(KycConstants.FALSE);
						userIdDetailsObj.setUserDetails(userDetails);
						
						userIdDetails.add(userIdDetailsObj);
					}

				userDetails.setUserIdDetail(userIdDetails);
				
				
				detailsList.add(userDetails);
			}
			

			if (usersDto.getSubRole() != null) {
				List<Role> roles = new ArrayList<>();
				for (RoleDto dto : usersDto.getSubRole()) {
					Role tempRole = new Role();
					BeanUtils.copyProperties(dto, tempRole);
				 if(tempRole.getParentRoleId()==null || tempRole.getParentRoleId()==0){
					for (RoleDto roleDto : usersDto.getRoles()) {						
						tempRole.setParentRoleId(roleDto.getRoleId());
						if(roleDto.getRoleId()==22 || roleDto.getRoleId()==37 || roleDto.getRoleId()==38 || roleDto.getRoleId()==39
						|| roleDto.getRoleId()==40  || roleDto.getRoleId()==41 || roleDto.getRoleId()==42 || roleDto.getRoleId()== 43)
						{
							tempRole.setMobileAccessFlag(1);
						}
						else{
							tempRole.setMobileAccessFlag(0);	
						}
						
						tempRole.setWebAccessFlag(1);
					}
					
				  }
				 else{
					 tempRole.setMobileAccessFlag(1);
					 tempRole.setWebAccessFlag(1);
					 tempRole.setParentRoleId(dto.getParentRoleId());
				 }
					
					roles.add(tempRole);
				}
				users.setRoles(roles);
			}
			else{
				
				List<Role> roles = new ArrayList<>();
				for (RoleDto dto : usersDto.getRoles()) {
					Role tempRole = new Role();
					BeanUtils.copyProperties(dto, tempRole);
					for (RoleDto roleDto : usersDto.getRoles()) {						
						tempRole.setParentRoleId(roleDto.getRoleId());
						if(roleDto.getRoleId()==22 || roleDto.getRoleId()==37 || roleDto.getRoleId()==38 || roleDto.getRoleId()==39
						|| roleDto.getRoleId()==40  || roleDto.getRoleId()==41 || roleDto.getRoleId()==42 || roleDto.getRoleId()== 43)
						{
							tempRole.setMobileAccessFlag(1);
						}
						else{
							tempRole.setMobileAccessFlag(0);	
						}
						
						tempRole.setWebAccessFlag(1);
					}
					
					roles.add(tempRole);
				}
				users.setRoles(roles);
				
			}
			/*if(usersDto.getSubRoles().getRoleId()!=null)
			{
				List<Role> roles = new ArrayList<>();
				Role role=new Role();
				RoleDto roleDto=usersDto.getSubRoles();
				//role.setRoleId(usersDto.getSubRoles().getParentRoleId());
				//role.setRoleName(usersDto.getSubRoles().getRoleName());
				BeanUtils.copyProperties(roleDto, role);
				roles.add(role);
				users.setRoles(roles);
			}*/
			users.setUserDetails(detailsList);
			if(userDetail!=null){
				users.setUserTransactionId(userObj.getUserTransactionId());
			}
			else{
				users.setUserTransactionId(txnId);
			}
			
			//generate user password			 
			String password = KycConstants.DEFULT_PASSWORD;
			BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(KycConstants.PASSWORD_LENGTH);
			password = cryptPasswordEncoder.encode(password);
			users.setAuuid(usersDto.getUserDetail().getAuuid());
			users.setPassword(password);
			//users.setTempPass(password);
			users.setBulkEnabled("1");
			
			usersDto.setPassword(password);
			if(usersDto.getUserDetail().getAuuid()!=null && usersDto.getUserDetail().getAuuid().length()>0){
				users.setIsPasswordModified(KycConstants.TRUE);
			}
			else{
				users.setIsPasswordModified(KycConstants.FALSE);
			}
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			
			if(users.getUserId()!=null)
			{
				users.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
				if (auth != null) {
					users.setUpdatedBy(auth.getName());
				}
				users.setIsBlocked(1);
				users.setIsDeleted(0);
				
			}
			else
			{
				
				users.setIsBlocked(1);
				users.setIsDeleted(0);
				users.setDeleteFlag(UserConstants.FALSE);
				users.setStatusFlag(UserConstants.TRUE);
				users.setCreatedOn(new Timestamp(System.currentTimeMillis()));
				if (auth != null) {
					users.setCreatedBy(auth.getName());
				}
			}		
			
			
			 //Save user in database.
			users.setRetryAttempt(0);
			users = this.userRepositry.saveOrUpdateUser(users);
			
			
			
			
			if (users == null) {
				throw new UserException(UserExceptionCodes.USER_UNABLE_TO_REGISTERED);
			}
			
			//send sms for successful user creation
			NotificationDto notificationDto = new NotificationDto();
			notificationDto.setMsisdn(users.getUserName());
			
			Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_USER_CREATION, "en", "sms");
			System.out.println("saubhagya : templates1 : "+templates1);
			String templateContent=templates1.getTemplateContent();
			//String smsOutput = MessageFormat.format(templateContent, otp);
			notificationDto.setTemplateContent(templateContent);
			System.out.println("saubhagya : templates1  after: "+templates1);
			Map<String , Object> map = new HashMap<>();
			map.put("msisdn", users.getUserName()+"");
			notificationDto.setMap(map);
			System.out.println("saubhagya : 8 : ");
			try {
				integrationService.notify(notificationDto);
			} catch (IntegrationServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			// Update user location.
			 
			if (users.getUserId() != null)
				updateUserLocation(users, usersDto);
			
			
			 //Updating mapping between subscriber and user
			 
			Long subscriberId = usersDto.getSubscriberId();
			if(subscriberId != null && subscriberId> 0) {
				Subscriber subscriber = new Subscriber();
				subscriber.setSubscriberId(subscriberId);
				SubscriberUser subscriberUser = new SubscriberUser();
				subscriberUser.setUsers(users);
				subscriberUser.setSubscriber(subscriber);
				this.kycDaoService.saveOrUpdateEntity(subscriberUser);
			}
		} catch (UserDaoException  e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		} catch (KycDaoException e) {
			//e.printStackTrace();
		}
		usersDto.setAuuId(users.getAuuid());
		usersDto.setUserId(users.getUserId());
		return usersDto;
	}

	@SuppressWarnings("unchecked")
	private void updateUserLocation(Users users, UsersDto usersDto) {
		if (users.getUserId() != null && usersDto.getLocation() != null) {
			Map<String, Object> parameterMap = new HashMap<>();
			List<Location> list = null;
			try {
				parameterMap.put("userId", users.getUserId());
				list = this.kycDaoService.findByCriteria(Location.class, parameterMap);
			} catch (KycDaoException e1) {
				e1.printStackTrace();
			}
			if (list != null && list.size() > 0) {
				for (Location location : list) {
					location.setIsOldRecord(KycConstants.TRUE);
					try {
						this.kycDaoService.saveOrUpdateEntity(location);
					} catch (KycDaoException e) {
						e.printStackTrace();
					}
				}
			}
			//LocationDto dto = usersDto.getLocation();
			
			//UserLocation location = new UserLocation();
			/*location.setUserId(users.getUserId());
			location.setRegionId(dto.getRegionId);
			location.setTerritoryId(dto.getTerritoryId());
			location.setDistrictId(dto.getDistrictId());*/
			/*location.setUserId(users.getUserId());
			location.setBranchId(dto.getBranchId());
			location.setZoneId(dto.getZoneId());
			location.setClusterId(dto.getClusterId());
			location.setSiteId(dto.getSiteId());
			location.setIsOldRecord(KycConstants.FALSE);
			*/
			
			
			//nested loop for user to user_region mapping
			/*if(usersDto.getLocation().getRegionIds()!=null){
			for (RegionIds regionIds : usersDto.getLocation().getRegionIds()) {
				UserRegion userRegion=new UserRegion();
				userRegion.setUserId(users.getUserId());
				userRegion.setIsOldRecord(KycConstants.FALSE);
				userRegion.setRegionId(regionIds.getRegionId());
				try {
					this.kycDaoService.saveOrUpdateEntity(userRegion);
				} catch (KycDaoException e) {
					e.printStackTrace();
				}*/
				
				//nested loop for user_region to user_territory mapping
			   if(usersDto.getLocation().getProvinceIds()!=null){
				for (ProvinceIds provinceIds : usersDto.getLocation().getProvinceIds()) {
					UserProvince userProvince=userProvinceDao.findByUserId(users.getUserId());//new UserProvince();
					if(userProvince!=null){
						userProvince.setProvinceId(provinceIds.getProvinceId());
						userProvince.setUserId(users.getUserId());
					}
					else{
						userProvince=new UserProvince();
						userProvince.setProvinceId(provinceIds.getProvinceId());
						userProvince.setUserId(users.getUserId());
					}
					
					//userTerritory.setUserRegionId(regionIds.getRegionId());
					try {
						this.kycDaoService.saveOrUpdateEntity(userProvince);
					} catch (KycDaoException e) {
						e.printStackTrace();
					}
					//nested loop for user_territory to user_district mapping
					for (DistrictIds districtIds : usersDto.getLocation().getDistrictIds()) {
						UserDistrict userDistrict=userDistrictDao.findByUserId(users.getUserId());//new UserDistrict();
						if(userDistrict!=null){
							userDistrict.setUserProvinceId(provinceIds.getProvinceId());
							userDistrict.setDistrictId(districtIds.getDistrictId());
							userDistrict.setUserId(users.getUserId());
						}
						else{
							userDistrict=new UserDistrict();
							userDistrict.setUserProvinceId(provinceIds.getProvinceId());
							userDistrict.setDistrictId(districtIds.getDistrictId());
							userDistrict.setUserId(users.getUserId());
						}
						try {
							this.kycDaoService.saveOrUpdateEntity(userDistrict);
						} catch (KycDaoException e) {
							e.printStackTrace();
						}
						for (VillageIds villageIds : usersDto.getLocation().getVillageIds()) {
							UserVillage userVillage=userVillageDao.findByUserId(users.getUserId());//new UserVillage();
							if(userVillage!=null){
								userVillage.setVillageId(villageIds.getVillageId());
								userVillage.setUserDistrictId(districtIds.getDistrictId());
								userVillage.setUserId(users.getUserId());
							}
							else{
								userVillage=new UserVillage();
								userVillage.setVillageId(villageIds.getVillageId());
								userVillage.setUserDistrictId(districtIds.getDistrictId());
								userVillage.setUserId(users.getUserId());
							}
							
							try {
								this.kycDaoService.saveOrUpdateEntity(userVillage);
							} catch (KycDaoException e) {
								e.printStackTrace();
							}
						}
						
					}
					
				}
			}
			
			}
			
			
			
			

		
	}
	
	public boolean provisionAPIAcsess(UsersDto usersDto) throws UserException {
		ClientOauthDetails clientOauthDetails = new ClientOauthDetails();
		clientOauthDetails.setClient_id(usersDto.getUserName());
		clientOauthDetails.setClient_secret(usersDto.getPassword());
		if (usersDto.getSubRole() != null) {
			String roles = "";
			int inc = 0;
			for (RoleDto role : usersDto.getSubRole()) {
				if (inc == 0) {
					roles = "ROLE_" + role.getRoleName();
				} else {
					roles += ", ROLE_" + role.getRoleName();
				}
				inc++;
			}
			clientOauthDetails.setAuthorities(roles);
		}
		
		
		clientOauthDetails.setAccess_token_validity(Integer.parseInt(env.getProperty(OAUTH_ACCESS_TOKEN_VALIDITY)));
		clientOauthDetails.setAuthorized_grant_types(env.getProperty(OAUTH_AUTHORIZED_GRANT_TYPES));
		clientOauthDetails.setAutoapprove(env.getProperty(OAUTH_AUTO_APPROVE));
		clientOauthDetails.setRefresh_token_validity(Integer.parseInt(env.getProperty(OAUTH_REFRESH_TOKEN_VALIDITY)));
		clientOauthDetails.setResource_ids(env.getProperty(OAUTH_RESOURCES_IDS));
		clientOauthDetails.setScope(env.getProperty(OAUTH_SCOPE));
		try {
			this.userRepositry.saveClientUser(clientOauthDetails);
		} catch (UserDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}
	@Override
	public boolean provisionLDAPAcsess(UsersDto usersDto) throws UserException {
		ClientOauthDetails clientOauthDetails = new ClientOauthDetails();
		clientOauthDetails.setClient_id(usersDto.getAuuId());
		BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(KycConstants.PASSWORD_LENGTH);
		String password = cryptPasswordEncoder.encode(usersDto.getAuuId());
		clientOauthDetails.setClient_secret(password);
		if (usersDto.getSubRole() != null) {
			String roles = "";
			int inc = 0;
			for (RoleDto role : usersDto.getSubRole()) {
				if (inc == 0) {
					roles = "ROLE_" + role.getRoleName();
				} else {
					roles += ", ROLE_" + role.getRoleName();
				}
				inc++;
			}
			clientOauthDetails.setAuthorities(roles);
		}
		
		
		clientOauthDetails.setAccess_token_validity(Integer.parseInt(env.getProperty(OAUTH_ACCESS_TOKEN_VALIDITY)));
		clientOauthDetails.setAuthorized_grant_types(env.getProperty(OAUTH_AUTHORIZED_GRANT_TYPES));
		clientOauthDetails.setAutoapprove(env.getProperty(OAUTH_AUTO_APPROVE));
		clientOauthDetails.setRefresh_token_validity(Integer.parseInt(env.getProperty(OAUTH_REFRESH_TOKEN_VALIDITY)));
		clientOauthDetails.setResource_ids(env.getProperty(OAUTH_RESOURCES_IDS));
		clientOauthDetails.setScope(env.getProperty(OAUTH_SCOPE));
		try {
			this.userRepositry.saveClientUser(clientOauthDetails);
		} catch (UserDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public UsersDto getUserByName(String userName) throws UserException {
		// TODO Auto-generated method stub
		UsersDto usersDto = new UsersDto();
		List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
		SubscriberDetails subscriberDetails=null;
		Users user = new Users();
		try {
			user = this.userRepositry.getUserByName(userName);
			if (user == null) {
				//throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
				subscriberDetails=subscriberDetailsDao.getSubscriberDetailsByMsisdn(userName,KycConstants.FALSE,KycConstants.FINAL_STATUS);
				if(subscriberDetails!=null){
					SubscriberIdDetails idDetails=subscriberIdDetailsDao.getSubscriberIdDetailsObj(subscriberDetails.getSubscriberDetailsId(), 0);
					usersDto.setIdCardNo(idDetails.getIdNumber());
				}
				
				usersDto.setUserFlag(0);
			}
			else{
			BeanUtils.copyProperties(user, usersDto);
			userDetailsDtoList = getUserDetails(usersDto, KycConstants.FALSE);
			/*if(userDetailsDtoList.size()>0){
				subscriberDetails=subscriberDetailsDao.getSubscriberDetailsByMsisdn(userName,KycConstants.FALSE,KycConstants.FINAL_STATUS);
				
				userDetailsDtoList.get(0).setIdCardNo(String.valueOf(subscriberDetails.getSubscriberIdDetails().iterator().next().getIdNo()));
				usersDto.setUserDetails(userDetailsDtoList);
			}*/
			
				usersDto.setIdCardNo(userDetailsDtoList.get(0).getIdCardNo());
				usersDto.setUserFlag(1);
			}
			

		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return usersDto;
	}
	
	//@SuppressWarnings("null")
	@Override
	public UsersDto isMsisdnAvailable(String userName) throws UserException {
		// TODO Auto-generated method stub
				UsersDto usersDto = new UsersDto();
				List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
				SubscriberDetails subscriberDetails=null;
				Users user = new Users();
				try {
					user = this.userRepositry.getUserByName(userName);
					if (user == null) {
						throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
						
					}
					else{
					BeanUtils.copyProperties(user, usersDto);
					userDetailsDtoList = getUserDetails(usersDto, KycConstants.FALSE);						
					userDetailsDtoList.get(0).setIdCardNo(String.valueOf(subscriberDetails.getSubscriberIdDetails().iterator().next().getIdNo()));
					usersDto.setUserDetails(userDetailsDtoList);
					}					

				} catch (UserDaoException e) {
					throw new UserException(e.getErrorCode(), e.getMessage(), e);
				}

				return usersDto;
	}
	@Override
	public List<UsersDto> deleteUser(UsersDto userData) throws UserException {
		UsersDto usersDto = new UsersDto();
		List<UsersDto> userDtoList=new ArrayList<>();
		List<Users> userList = new ArrayList<>();
		Users user = new Users();
		try {
			Users userObj=this.userRepositry.getUserByMsisdn(userData.getUserName());
			if(userData.getUserId()==null){
				userObj.setUserId(userObj.getUserId());
			}
			else{
				userObj.setUserId(userData.getUserId());
			}
			userObj.setUserName(userData.getUserName());
			userObj.setIsBlocked(0);
			userObj.setIsDeleted(1);
			userObj.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				userObj.setUpdatedBy(auth.getName());
			}
			user = this.userRepositry.saveOrUpdateUser(userObj);
			if (user == null) {
				throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
			}
			//BeanUtils.copyProperties(user, usersDto);
			SearchParamDto searchParamDto=new SearchParamDto();
			userList = (List<Users>) this.userRepositry.searchUser(searchParamDto, 10, 0);
			for (Users users : userList) {
				UsersDto userDtoObj=new UsersDto();
				BeanUtils.copyProperties(users, userDtoObj);
				userDtoList.add(userDtoObj);
			}			
			

		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return userDtoList;
	}
	@Override
	public UsersDto blockUser(UsersDto userData) throws UserException {
		UsersDto usersDto = new UsersDto();				
		Users user = new Users();
		try {
			Users userObj=this.userRepositry.getUserByMsisdn(userData.getUserName());
			userObj.setUserId(userData.getUserId());
			userObj.setUserName(userData.getUserName());
			if(userData.getIsBlocked()==0){
				userObj.setIsBlocked(1);
			}
			else{
				userObj.setIsBlocked(0);
			}			
			userObj.setIsDeleted(0);
			userObj.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				userObj.setUpdatedBy(auth.getName());
			}
			user = this.userRepositry.saveOrUpdateUser(userObj);
			if (user == null) {
				throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
			}
			BeanUtils.copyProperties(user, usersDto);
					
			

		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return usersDto;
	}
	@Override
	public UsersDto getUserById(int uid) throws UserException {
		UsersDto usersDto = new UsersDto();
		List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
		Users user = new Users();
		try {
			String userName=String.valueOf(uid);
			user = this.userRepositry.getUserByMsisdn(userName);
			if (user == null) {
				user = this.userRepositry.getUserById(uid);
				if(user==null){
					throw new UserException(UserExceptionCodes.USER_UNABLE_TO_REGISTERED);
				}
				
			}
			BeanUtils.copyProperties(user, usersDto);
			userDetailsDtoList = getUserDetails(usersDto, KycConstants.FALSE);
			usersDto.setUserDetails(userDetailsDtoList);
			LocationDto locationDto = getLocataion(uid);
			if (locationDto != null)
				usersDto.setLocation(getLocataion(uid));
		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return usersDto;
	}
	@Override
	public UsersDto updatePassword(int uid, UsersDto usersDto) throws UserException {
		// if(usersDto==null)
				// throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
				Users users = new Users();

				try {
					String userName=String.valueOf(uid);
					users = this.userRepositry.getUserByName(userName);
					if(users==null){
						users = this.userRepositry.getUserById(uid);
					}
					
					
					users.setPassword(usersDto.getPassword());
					users.setIsPasswordModified(usersDto.getIsPasswordModified());
					
					users.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					if (auth != null) {
						users.setUpdatedBy(auth.getName());
					}
					
					users = this.userRepositry.saveOrUpdateUser(users);
					
					
					
				} catch (UserDaoException e) {
					throw new UserException(e.getErrorCode(), e.getMessage(), e);
				}
				// BeanUtils.copyProperties(users, usersDto);
				return usersDto;
	}
	
	@SuppressWarnings("unchecked")
	public LocationDto getLocataion(Integer uid) throws UserException {
		LocationDto locationDto = new LocationDto();
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("userId", uid);
			params.put("isOldRecord", KycConstants.FALSE);
			Location location = (Location) this.kycDaoService.getEntityByCriteria(Location.class, params);
			if (location == null)
				return null;
			BeanUtils.copyProperties(location, locationDto);

			params = new HashMap<>();
			params.put("zoneId", location.getZoneId());
			Zone zone = (Zone) this.kycDaoService.getEntityByCriteria(Zone.class, params);
			locationDto.setZoneName(zone.getZoneName());

			params = new HashMap<>();
			params.put("branchId", location.getBranchId());
			Branch branch = (Branch) this.kycDaoService.getEntityByCriteria(Branch.class, params);
			locationDto.setBranchName(branch.getBranchName());

			if (location.getClusterId() != null) {
				params = new HashMap<>();
				params.put("clusterId", location.getClusterId());
				Cluster_ cluster_ = (Cluster_) this.kycDaoService.getEntityByCriteria(Cluster_.class, params);
				locationDto.setClusterName(cluster_.getClusterName());
			}
			if (location.getSiteId() != null) {
				params = new HashMap<>();
				params.put("id", location.getSiteId());
				Site site = (Site) this.kycDaoService.getEntityByCriteria(Site.class, params);
				locationDto.setSiteName(site.getSiteName());
			}
		} catch (KycDaoException e) {
			e.printStackTrace();
		}
		return locationDto;
	}
	@Override
	public UsersDto authenticateUserByAuuId(String auuid, String password) throws UserException {
		UsersDto usersDto = new UsersDto();		
		Boolean user  = this.integrationService.authenticateUser(auuid, password);
		if (!user) {
			usersDto.setAuuIdFlag(user);
			//throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
		}
		usersDto.setAuuIdFlag(user);
		
		return usersDto;
	}
	@Override
	public UsersDto generatePassword(UsersDto userData) throws UserException, IOException {

		UsersDto usersDto = new UsersDto();				
		Users user = new Users();
		try {
			user = this.userRepositry.getUserByName(userData.getUserName());
			if (user == null) {
				throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
			}
			else if(user.getIsPasswordModified()==0 && user.getAuuid()==null){
				
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Date security_code_timeStamp = new Date(timestamp.getTime());
				LOGGER.info("security_code_timeStamp" +security_code_timeStamp);
				Date otpTimeStamp=null;
				if(user.getOtpCreationTime()==null){
					Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
					otpTimeStamp= new Date(timestamp1.getTime());
				}
				else{
					otpTimeStamp= new Date(user.getOtpCreationTime().getTime());
				}
				long timeDiff = security_code_timeStamp.getTime() - otpTimeStamp.getTime();				
				long diffMinutes = timeDiff / (60 * 1000) % 60;
				LOGGER.info("diffMinute:"+diffMinutes);
				System.out.println("diffMinute:"+diffMinutes);
				int blockingTimePeriod = Integer.parseInt(env.getProperty(SECURITY_CODE_EXPIRY_TIME));				
				
				if (diffMinutes <= blockingTimePeriod &&  user.getOtpCount()!=null &&  user.getOtpCount()<3) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(user.getUserName());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_USER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", user.getUserName()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("otp:"+otp);
					LOGGER.info("otp:"+otp);
					user.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					user.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(user.getOtpCount()==null || user.getOtpCount()==0){
						user.setOtpCount(user.getOtpCount()+1);
					}
					else{
						user.setOtpCount(user.getOtpCount()+1);
					}
					user = this.userRepositry.saveOrUpdateUser(user);
				}
				else if(diffMinutes >= blockingTimePeriod &&  user.getOtpCount()!=null &&  user.getOtpCount()<3) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(user.getUserName());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_USER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", user.getUserName()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("otp:"+otp);
					LOGGER.info("otp:"+otp);
					user.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					user.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(user.getOtpCount()==null || user.getOtpCount()==0){
						user.setOtpCount(1);
					}
					else{
						user.setOtpCount(1);
					}
					user = this.userRepositry.saveOrUpdateUser(user);				
				
				
			   }	
				else if(diffMinutes >= blockingTimePeriod &&  user.getOtpCount()!=null &&  user.getOtpCount()>=3) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(user.getUserName());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_USER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", user.getUserName()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("otp:"+otp);
					LOGGER.info("otp:"+otp);
					user.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					user.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(user.getOtpCount()==null || user.getOtpCount()==0){
						user.setOtpCount(1);
					}
					else{
						user.setOtpCount(1);
					}
					user = this.userRepositry.saveOrUpdateUser(user);
				
				
				
			   }	
				else if(diffMinutes < blockingTimePeriod &&  user.getOtpCount()==null) {
					String otp=MaskUtils.generateSecurityCode();
					
					NotificationDto notificationDto = new NotificationDto();
					notificationDto.setMsisdn(user.getUserName());
					
					Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_USER, "en", "sms");
					System.out.println("saubhagya : templates1 : "+templates1);
					String templateContent=templates1.getTemplateContent();
					String smsOutput = MessageFormat.format(templateContent, otp);
					notificationDto.setTemplateContent(smsOutput);
					System.out.println("saubhagya : templates1  after: "+templates1);
					Map<String , Object> map = new HashMap<>();
					map.put("msisdn", user.getUserName()+"");
					notificationDto.setMap(map);
					System.out.println("saubhagya : 8 : ");
					try {
						integrationService.notify(notificationDto);
					} catch (IntegrationServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					System.out.println("otp:"+otp);
					LOGGER.info("otp:"+otp);
					user.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
					user.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));	
					if(user.getOtpCount()==null || user.getOtpCount()==0){
						user.setOtpCount(1);
					}
					else{
						user.setOtpCount(1);
					}
					user = this.userRepositry.saveOrUpdateUser(user);
				
				
				
			   }	
			else{
				throw new UserException(UserExceptionCodes.SECURITY_CODE_CANNOT_BE_GENERATED);	
			}

			}
			else{
				throw new UserException(UserExceptionCodes.SECURITY_CODE_CANNOT_BE_GENERATED);	
			}
		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return usersDto;
	
	}
	/*@Override
	public UsersDto validateOtp(UsersDto userData) throws UserException {
		UsersDto usersDto = new UsersDto();				
		Users user = new Users();
		try {
			user = this.userRepositry.getUserByName(userData.getUserName());
			if (user == null) {
				throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
			}
			else {
				if (user.getOtp().equalsIgnoreCase(DigestUtils.md5DigestAsHex(userData.getOtp().getBytes())))
					
				{
					
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Date security_code_timeStamp = new Date(timestamp.getTime());
				LOGGER.info("security_code_timeStamp" +security_code_timeStamp);				
				Date otpTimeStamp= new Date(user.getOtpCreationTime().getTime());
				long timeDiff = security_code_timeStamp.getTime() - otpTimeStamp.getTime();				
				long diffMinute = timeDiff / (60 * 1000) % 60;
				LOGGER.info("diffMinute:"+diffMinute);				
				int securityCodeTimeout = Integer.parseInt(messageSource.getMessage(SECURITY_CODE_EXPIRY_TIME, null,Locale.getDefault()));
				if (diffMinute <= securityCodeTimeout) {
					usersDto.setMessage(messageSource.getMessage(SECURITY_CODE_VALIDATED, null, Locale.getDefault()));
					StringBuilder encString=new StringBuilder();
					encString.append(usersDto.getMsisdn());
					encString.append(usersDto.getOtp());
					String finalString=encString.toString();
					usersDto.setMsisdn(aesUtil.encrypt(finalString));
					
					//invalidating otp for 2nd time
					user.setOtp(null);
					user.setOtpCreationTime(null);
					userRepositry.saveOrUpdateUser(user);
				} 
				else {
					usersDto.setMessage(messageSource.getMessage(SECURITY_CODE_EXPIRED, null, Locale.getDefault()));
				}
				
				
				}
				else {
					usersDto.setMessage(messageSource.getMessage(SECURITY_CODE_INVALID, null, Locale.getDefault()));
				}
			
			}			

		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return usersDto;
	}*/
	
	@Override
	public UsersDto validateOtp(UsersDto userData) throws UserException {
		UsersDto usersDto = new UsersDto();				
		Users user = new Users();
		try {
			user = this.userRepositry.getUserByName(userData.getUserName());
			if (user == null) {
				throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
			}
			else {
				if(user.getOtp()!=null){
					
					if (user.getOtp().equalsIgnoreCase(DigestUtils.md5DigestAsHex(userData.getOtp().getBytes())))
						
					{
						
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						Date security_code_timeStamp = new Date(timestamp.getTime());
						
						LOGGER.debug("security_code_timeStamp" +security_code_timeStamp);
						
						Date otpTimeStamp= new Date(user.getOtpCreationTime().getTime());
						long timeDiff = security_code_timeStamp.getTime() - otpTimeStamp.getTime();				
						long diffMinute = timeDiff / (60 * 1000) % 60;
						
						LOGGER.debug("diffMinute:"+diffMinute);
						
						int securityCodeTimeout = Integer.parseInt(env.getProperty(SECURITY_CODE_EXPIRY_TIME));
						if (diffMinute <= securityCodeTimeout) {
							usersDto.setMessage(env.getProperty(SECURITY_CODE_VALIDATED));
							StringBuilder encString=new StringBuilder();
							encString.append(userData.getUserName());
							encString.append(userData.getOtp());
							String finalString=encString.toString();
							usersDto.setMsisdn(aesUtil.encrypt(finalString));
							
							//invalidating otp for 2nd time
							user.setOtp(null);
							user.setOtpCreationTime(null);
							user.setOtpCount(0);
							userRepositry.saveOrUpdateUser(user);

						} 
						else {
							usersDto.setMessage(env.getProperty(SECURITY_CODE_EXPIRED));
						}
						
						
					}
					else {
						usersDto.setMessage(env.getProperty(SECURITY_CODE_INVALID));
					}
				}
				else {
					usersDto.setMessage(env.getProperty(SECURITY_CODE_INVALID));
				}
			
			}			

		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return usersDto;
	}
	@Override
	public Users validateUser(UsersDto userData) throws UserException {
					
		Users user = new Users();
		try {
			user = this.userRepositry.getUserByAuuId(userData.getAuuId());
			if (user == null) {
				user = this.userRepositry.getUserByName(userData.getAuuId());
				/*if(user==null){
					throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
				}	*/			
			}			

		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return user;
	}
	@Override
	public UserDetailsDto forgotPassword(UserDetailsDto userDetailsDto) throws UserException, IOException {
		UserDetailsDto userDetailsObj = new UserDetailsDto();				
		UserDetails userDetails = new UserDetails();
		Users user = new Users();
		try {
			Date dob=DateUtils.getDate(userDetailsDto.getDob(),"dd-MM-yy");
			String idNumber=userDetailsDto.getIdCardNo();
			String msisdn=userDetailsDto.getMsisdn();
			userDetails = this.userRepositry.getUserDetails(dob,idNumber,msisdn);
			if (userDetails == null) {
				throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);
			}
			else {
				user = this.userRepositry.getUserByName(userDetails.getMsisdn());
				String otp=MaskUtils.generateSecurityCode();
				
				NotificationDto notificationDto = new NotificationDto();
				notificationDto.setMsisdn(user.getUserName());
				
				Templates templates1 =templatesDao.getTemplateObj(SMS_OTP_USER, "en", "sms");
				System.out.println("saubhagya : templates1 : "+templates1);
				String templateContent=templates1.getTemplateContent();
				String smsOutput = MessageFormat.format(templateContent, otp);
				notificationDto.setTemplateContent(smsOutput);
				System.out.println("saubhagya : templates1  after: "+templates1);
				Map<String , Object> map = new HashMap<>();
				map.put("msisdn", user.getUserName()+"");
				notificationDto.setMap(map);
				System.out.println("saubhagya : 8 : ");
				try {
					integrationService.notify(notificationDto);
				} catch (IntegrationServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("otp:"+otp);
				LOGGER.info("otp:"+otp);
				user.setOtp(DigestUtils.md5DigestAsHex(otp.getBytes()));
				user.setUpdatedOn(new Timestamp(System.currentTimeMillis()));	
				user.setOtpCreationTime(new Timestamp(System.currentTimeMillis()));
				user = this.userRepositry.saveOrUpdateUser(user);
			
			}			

		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return userDetailsObj;
	}
	@Override
	public UsersDto resetPassword(UsersDto userData) throws UserException {
		UsersDto usersDto = new UsersDto();				
		Users user = new Users();
		boolean notAllowed=false;
		try {
			user = this.userRepositry.getUserByName(userData.getAuuId());
			if (user == null) {					
				throw new UserException(UserExceptionCodes.USER_NOT_AVAILABLE);						
			}
			else if(user.getRoles().get(0).getRoleId()==27 || user.getRoles().get(0).getRoleId()==32){
				
				user.setIsPasswordModified(KycConstants.FALSE);	
				user = this.userRepositry.saveOrUpdateUser(user);
				//usersDto.setMessage(message);
				usersDto.setNotAllowed(notAllowed);
			
			}
			else{
				usersDto.setNotAllowed(true);
				//throw new UserException(UserExceptionCodes.NOT_ALLOWED_TO_CHANGE_PWD);	
				
			}
			
		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return usersDto;
	}
	@Override
	public boolean updateAssignment(UsersDto userData)throws UserException{
		return true;
	}

	/*@Override
	public UsersDto getUserById(int uid) throws UserException {
		UsersDto usersDto = new UsersDto();
		List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
		Users user = new Users();
		try {
			user = this.userRepositry.getUserById(uid);
			if (user == null) {
				throw new UserException(UserExceptionCodes.USER_UNABLE_TO_REGISTERED);
			}
			BeanUtils.copyProperties(user, usersDto);
			userDetailsDtoList = getUserDetails(usersDto, KycConstants.FALSE);
			usersDto.setUserDetails(userDetailsDtoList);
			LocationDto locationDto = getLocataion(uid);
			if (locationDto != null)
				usersDto.setLocation(getLocataion(uid));
		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}

		return usersDto;
	}

		@Override
	public UsersDto updateUser(int uid, UsersDto usersDto) throws UserException {
		// if(usersDto==null)
		// throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		Users users = new Users();
		BeanUtils.copyProperties(usersDto, users);
		try {
			Users temp = new Users();
			temp = this.userRepositry.getUserById(uid);
			if (temp == null) {
				throw new UserException(UserExceptionCodes.USER_UNABLE_TO_REGISTERED);
			}
			List<UserDetails> detailsNew = new ArrayList<UserDetails>();
			if (usersDto.getUserDetail() != null) {
				List<UserDetails> details = this.userRepositry.getUserDetails(uid, null);
				if (details != null) {
					for (UserDetails detail : details) {
						detail.setIsOldUserDetails(KycConstants.TRUE);
						detailsNew.add(detail);
					}
				}
				UserDetails detailsFresh = new UserDetails();
				BeanUtils.copyProperties(usersDto.getUserDetails(), detailsFresh);
				detailsNew.add(detailsFresh);
			}

			if (usersDto.getUserDetail() != null) {
				UserDetails userDetails = new UserDetails();
				BeanUtils.copyProperties(usersDto.getUserDetail(), userDetails);
				userDetails.setUsers(users);
				userDetails.setIsOldUserDetails(KycConstants.FALSE);
				detailsNew.add(userDetails);
			}

			users.setUserDetails(detailsNew);
			users.setRoles(temp.getRoles());
			
			users.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				users.setUpdatedBy(auth.getName());
			}
			
			users = this.userRepositry.saveOrUpdateUser(users);
			if (users != null && users.getUserId() != null) {
				updateUserLocation(users, usersDto);
			}
		} catch (UserDaoException e) {
			throw new UserException(e.getErrorCode(), e.getMessage(), e);
		}
		// BeanUtils.copyProperties(users, usersDto);
		return usersDto;
	}

	

	@Override
	public Integer updateBypass(Integer isByPassflag) throws UserException {
		// TODO Auto-generated method stub
		try {
			VerificationByPass verificationByPass = new VerificationByPass();
			verificationByPass.setBypassStatus(isByPassflag);
			verificationByPass = (VerificationByPass) kycDaoService.saveOrUpdateEntity(verificationByPass);

		} catch (KycDaoException cause) {
			throw new UserException(null, null, cause);
		}
		return isByPassflag;
	}

	@Override
	public Integer getBypassStatus(Integer isByPassflag) throws UserException {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("unchecked")
			List<VerificationByPass> list = kycDaoService.getBypassStatus(isByPassflag);
			if (list != null && list.size() > 0)
				isByPassflag = list.get(0).getBypassStatus();

		} catch (KycDaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isByPassflag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LocationDto getLocataion(Integer uid) throws UserException {
		LocationDto locationDto = new LocationDto();
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("userId", uid);
			params.put("isOldRecord", KycConstants.FALSE);
			Location location = (Location) this.kycDaoService.getEntityByCriteria(Location.class, params);
			if (location == null)
				return null;
			BeanUtils.copyProperties(location, locationDto);

			params = new HashMap<>();
			params.put("zoneId", location.getZoneId());
			Zone zone = (Zone) this.kycDaoService.getEntityByCriteria(Zone.class, params);
			locationDto.setZoneName(zone.getZoneName());

			params = new HashMap<>();
			params.put("branchId", location.getBranchId());
			Branch branch = (Branch) this.kycDaoService.getEntityByCriteria(Branch.class, params);
			locationDto.setBranchName(branch.getBranchName());

			if (location.getClusterId() != null) {
				params = new HashMap<>();
				params.put("clusterId", location.getClusterId());
				Cluster_ cluster_ = (Cluster_) this.kycDaoService.getEntityByCriteria(Cluster_.class, params);
				locationDto.setClusterName(cluster_.getClusterName());
			}
			if (location.getSiteId() != null) {
				params = new HashMap<>();
				params.put("id", location.getSiteId());
				Site site = (Site) this.kycDaoService.getEntityByCriteria(Site.class, params);
				locationDto.setSiteName(site.getSiteName());
			}
		} catch (KycDaoException e) {
			e.printStackTrace();
		}
		return locationDto;
	}

	
	

	@SuppressWarnings("unchecked")
	@Override
	public UsersDto transformSubscriberToUser(SubscriberDto subscriberDto) throws UserException {
		if (subscriberDto == null) {
			return null;
		}
		SubscriberDetailsDto subscriberDetailsDto = null;
		if (subscriberDto.getSubscriberDetails() != null && !subscriberDto.getSubscriberDetails().isEmpty()) {
			subscriberDetailsDto = subscriberDto.getSubscriberDetails().get(0);
		}
		UsersDto usersDto = new UsersDto();
		List<UserDetailsDto> detailsDtos = new ArrayList<>();
		UserDetailsDto detailsDto = new UserDetailsDto();
		detailsDto.setFirstName(subscriberDetailsDto.getFirstName());
		detailsDto.setLastName(subscriberDetailsDto.getLastName());
		detailsDto.setEmail(subscriberDetailsDto.getEmail());
		detailsDtos.add(detailsDto);
		usersDto.setUserDetails(detailsDtos);

		List<RoleDto> dtos = new ArrayList<>();
		String roles = subscriberDto.getSubscriberRoles();
		if (roles != null) {
			String[] roles_array = roles.split(",");
			for (String role : roles_array) {
				HashMap<String, String> parameterMap = new HashMap<>();
				parameterMap.put("userRoleName", role);
				try {
					Role roleNew = (Role) this.userDaoService.getEntityByCriteria(Role.class, parameterMap);
					RoleDto dto = new RoleDto();
					BeanUtils.copyProperties(roleNew, dto);
					dtos.add(dto);
				} catch (UserDaoException e) {
					e.printStackTrace();
				}

			}
			usersDto.setRoles(dtos);
		}
		return provisionUser(usersDto);
	}

	@Override
	public UsersDto updatePassword(int uid, UsersDto usersDto) throws UserException {
		// if(usersDto==null)
				// throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
				Users users = new Users();

				try {

					users = this.userRepositry.getUserById(uid);
					
					users.setPassword(usersDto.getPassword());
					users.setIsPasswordModified(usersDto.getIsPasswordModified());
					
					users.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					if (auth != null) {
						users.setUpdatedBy(auth.getName());
					}
					
					users = this.userRepositry.saveOrUpdateUser(users);
					
					
					
				} catch (UserDaoException e) {
					throw new UserException(e.getErrorCode(), e.getMessage(), e);
				}
				// BeanUtils.copyProperties(users, usersDto);
				return usersDto;
	}*/

}
