package com.airtel.kyc.business;

import org.springframework.web.multipart.MultipartFile;

import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.ConfigDataDTO;
import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.exception.UserDaoException;

public interface HelperDataManagement {

	//public BaseResponse<?> getRegionBranchMapping() throws BusinessException;
	//public BaseResponse<?> getRegionBranchMapping() throws BusinessException;
	
	public BaseResponse<?> getSiteZoneMapping() throws BusinessException;
	
	public BaseResponse<?> getZones() throws BusinessException;

	/*public BaseResponse<?> getDepartments(int slot,int startIndex) throws BusinessException;*/
	public BaseResponse<?> getDepartments() throws BusinessException;

	public BaseResponse<?> getLocationListById(String nameOfValue, int id) throws BusinessException;
	
	//public BaseResponse<?> getRegions(String nameOfValue, int id) throws BusinessException;

	//public BaseResponse<Object> getTerritoryByRegionId(String territory, int id)throws BusinessException;

	//public BaseResponse<Object> getDistrictByTerritoryId(String district, int id)throws BusinessException;

	//public BaseResponse<Object> getAllRegion()throws BusinessException;

	//public BaseResponse<Object> getTerritoryList(String territory, UsersDto usersDto) throws BusinessException;

	public BaseResponse<Object> getDistrictList(String territory, UsersDto usersDto)throws BusinessException;

	public BaseResponse<Object> getAllRoles()throws BusinessException, UserBusinessException, UserDaoException;

	public BaseResponse<Object> getDistrictList()throws BusinessException;

	public BaseResponse<Object> getDepartmentList() throws BusinessException, UserDaoException;

	public BaseResponse<Object> getAllRoleList()throws BusinessException, UserDaoException, UserBusinessException;

	public BaseResponse<Object> getRoleIdByUserName(UsersDto usersDto)throws BusinessException, UserDaoException, UserBusinessException;

	//public BaseResponse<Object> getParishByDistrictId(String parish, int id)throws BusinessException;

	public BaseResponse<Object> updateConfigData(String configData,MultipartFile file)throws BusinessException;

	public BaseResponse<Object> getConfigData()throws BusinessException;
	
	public BaseResponse<?> getCountries() throws BusinessException;

	public BaseResponse<Object> getProvince(String region, int id)throws BusinessException;

	public BaseResponse<Object> getAllProvince()throws BusinessException;

	public BaseResponse<Object> getDistrictByProvinceId(String district, int id)throws BusinessException;

	public BaseResponse<Object> getVillageByDistrictId(String village, int id)throws BusinessException;

	public BaseResponse<Object> getAllCountriesWIthCountryCode()throws BusinessException;

	public void testsms()throws BusinessException;
}
