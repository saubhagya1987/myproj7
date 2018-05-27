package com.airtel.kyc.business.service;

import java.util.List;
import java.util.Map;

import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.BranchDto;
import com.airtel.kyc.helpers.dto.ClusterDto;
import com.airtel.kyc.helpers.dto.CommissionRuleDto;
import com.airtel.kyc.helpers.dto.ConfigDataDTO;
import com.airtel.kyc.helpers.dto.CountryDto;
import com.airtel.kyc.helpers.dto.HelpAndSupportDto;
import com.airtel.kyc.helpers.dto.ZoneDto;
import com.airtel.kyc.repository.entity.ConfigData;
import com.airtel.kyc.repository.entity.Templates;
import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.LocationDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.exception.UserDaoException;

public interface HelperDataManagementService {

	public Map<String, Object> getSitesMapping() throws BusinessException;

	public Map<String, Object> getZones() throws BusinessException;

	public Map<String, Object> getBranchByZoneId(int zoneId) throws BusinessException;

	public Map<String, Object> getClusterByBranchId(int branchId) throws BusinessException;

	public Map<String, Object> getSitesByClusterId(int clusterId) throws BusinessException;

	//public List<DepartmentDto> getDepartments(int slot,int startIndex) throws BusinessException;
	
	public List<DepartmentDto> getDepartments() throws BusinessException;
	
	//public Map<String, Object> getRegionDistrictWithCountry() throws BusinessException;

	//public Map<String, Object> getRegion() throws BusinessException;

	//public Map<String, Object> getTerritoryByRegionId(int parentId)throws BusinessException;

	//public Map<String, Object> getDistrictByTerritoryId(int parentId)throws BusinessException;

	//public Map<String, Object> getAllRegion()throws BusinessException;

	//public Map<String, Object> getTerritoryList(UsersDto usersDto)throws BusinessException;

	public Map<String, Object> getDistrictList(UsersDto usersDto)throws BusinessException;	

	//public Map<String, Object> getAllRoleWithSubRole()throws BusinessException, UserDaoException, UserBusinessException;
	
	public RoleDto getRoleByName(String roleName) throws UserException ;
	
	public RoleDto getRoleBySubRoleName(String roleName) throws UserException ;
	
	
	public LocationDto getLocation(Long subId) ;
	
	public Map<String, Object> getAllRoles()throws BusinessException, UserBusinessException, UserDaoException;	
	
    
	
	public ZoneDto getZoneByName(String zoneName) throws UserException ;
	
	public DepartmentDto getDepartmentByName(String departmentName) throws UserException ;
	
	public BranchDto getBranchByName(String branchName) throws UserException ;
	
	public ClusterDto getClusterByName(String clusterName) throws UserException ;
	
	public boolean updateLocation(LocationDto dto, Integer userId, Long subId) throws BusinessException;

	public Map<String, Object> getDistrictList()throws BusinessException;

	public Map<String, Object> getDepartmentList()throws BusinessException, UserDaoException;
	
	public Templates getTemplates(String locale, String templateName, String chanel) ;

	public Map<String, Object> getAllRoleList()throws BusinessException, UserBusinessException, UserDaoException;

	public CommissionRuleDto saveCommissionRule(CommissionRuleDto commissionRuleDto)throws BusinessException;

	public CommissionRuleDto getCommissionRule(CommissionRuleDto commissionRuleDto)throws BusinessException;

	public Map<String, Object> getRoleIdByUserName(UsersDto usersDto)throws BusinessException, UserDaoException, UserBusinessException;

	//public Map<String, Object> getParishByDistrictId(int parentId)throws BusinessException;
	
	public BaseResponse<Object> updateConfigData(List<ConfigData> listConfigData)throws BusinessException;
	
	public List<ConfigData> getConfigData()throws BusinessException;

	public HelpAndSupportDto saveAndSendHelpAndSupportData(HelpAndSupportDto helpAndSupport)throws BusinessException;
	
	public List<CountryDto> getCountries() throws BusinessException;

	public Map<String, Object> getProvince()throws BusinessException;

	public Map<String, Object> getAllProvince()throws BusinessException;

	public Map<String, Object> getDistrictByProvinceId(int parentId)throws BusinessException;

	public Map<String, Object> getVillageByDistrictId(int parentId)throws BusinessException;

	public List<CountryDto> getAllCountriesWIthCountryCode()throws BusinessException;
}
