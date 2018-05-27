package com.airtel.kyc.business.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.airtel.kyc.business.HelperDataManagement;
import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.service.HelperDataManagementService;
import com.airtel.kyc.constants.AppConstants;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.enumData.ConfigDataEnum;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.ConfigDataDTO;
import com.airtel.kyc.helpers.dto.CountryDto;
import com.airtel.kyc.helpers.dto.NotificationDto;
import com.airtel.kyc.helpers.dto.ResponseDto;
import com.airtel.kyc.integration.exception.IntegrationServiceException;
import com.airtel.kyc.integration.service.IntegrationService;
import com.airtel.kyc.repository.entity.ConfigData;
import com.airtel.kyc.utils.KycResponseUtils;
import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.exception.UserDaoException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HelperDataManagementImpl implements HelperDataManagement, KycConstants {



	@Autowired	
	private HelperDataManagementService helperDataManagementService;
	
	@Autowired
	IntegrationService integration;
	
	@Autowired
	private AppConstants appConstants;

	@Override
	public BaseResponse<?> getSiteZoneMapping() throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = this.helperDataManagementService.getSitesMapping();
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<?> getZones() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse<?> getLocationListById(String nameOfValue, int parentId) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = null;
		if (ZONE.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getZones();
		} else if (BRANCH.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getBranchByZoneId(parentId);
		} else if (CLUSTER.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getClusterByBranchId(parentId);
		} else if (SITE.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getSitesByClusterId(parentId);
		}
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	//public BaseResponse<?> getDepartments(int slot,int startIndex) throws BusinessException {
	public BaseResponse<?> getDepartments() throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		//List<DepartmentDto> departments = this.helperDataManagementService.getDepartments(slot,startIndex);
		List<DepartmentDto> departments = this.helperDataManagementService.getDepartments();
		if (departments == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched departments");
			baseResponse.setBody(departments);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	/*@Override
	public BaseResponse<?> getRegionBranchMapping() throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = this.helperDataManagementService.getRegionDistrictWithCountry();
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched departments");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	public static void main(String[] BCryptPasswordEncod11) {
		
	}*/

	/*@Override
	public BaseResponse<?> getRegions(String nameOfValue, int parentId) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = null;
		if (REGION.equalsIgnoreCase(nameOfValue)) {
			//map = this.helperDataManagementService.getZones();
			map = this.helperDataManagementService.getRegion();
		} else if (TERRITORY.equalsIgnoreCase(nameOfValue)) {
			//map = this.helperDataManagementService.getBranchByZoneId(parentId);
			map = this.helperDataManagementService.getTerritoryByRegionId(parentId);
		} else if (DISTRICT.equalsIgnoreCase(nameOfValue)) {
			//map = this.helperDataManagementService.getClusterByBranchId(parentId);
			map = this.helperDataManagementService.getDistrictByTerritoryId(parentId);
		} 
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}*/

	/*@Override
	public BaseResponse<Object> getTerritoryByRegionId(String nameOfValue, int parentId) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = null;
		if (REGION.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getRegion();
		} else if (TERRITORY.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getTerritoryByRegionId(parentId);
		} else if (DISTRICT.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getDistrictByTerritoryId(parentId);
		} 
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}*/

	/*@Override
	public BaseResponse<Object> getDistrictByTerritoryId(String nameOfValue, int parentId) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = null;
		if (REGION.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getRegion();
		} else if (TERRITORY.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getTerritoryByRegionId(parentId);
		} else if (DISTRICT.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getDistrictByTerritoryId(parentId);
		} 
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}*/

	/*@Override
	public BaseResponse<Object> getAllRegion() throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = this.helperDataManagementService.getAllRegion();
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}*/

	/*@Override
	public BaseResponse<Object> getTerritoryList(String territory, UsersDto usersDto) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();		
		Map<String, Object> map = this.helperDataManagementService.getTerritoryList(usersDto);
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched departments");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}*/

	@Override
	public BaseResponse<Object> getDistrictList(String territory, UsersDto usersDto) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();		
		Map<String, Object> map = this.helperDataManagementService.getDistrictList(usersDto);
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched departments");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<Object> getAllRoles() throws BusinessException, UserBusinessException, UserDaoException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = this.helperDataManagementService.getAllRoles();
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}
	

	@Override
	public BaseResponse<Object> getDistrictList() throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = this.helperDataManagementService.getDistrictList();
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<Object> getDepartmentList() throws BusinessException, UserDaoException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = this.helperDataManagementService.getDepartmentList();
		
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	
	@Override
	public BaseResponse<Object> getAllRoleList() throws BusinessException, UserDaoException, UserBusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = this.helperDataManagementService.getAllRoleList();
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<Object> getRoleIdByUserName(UsersDto usersDto) throws BusinessException, UserDaoException, UserBusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = this.helperDataManagementService.getRoleIdByUserName(usersDto);
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	/*@Override
	public BaseResponse<Object> getParishByDistrictId(String nameOfValue, int parentId) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = null;
		if (PARISH.equalsIgnoreCase(nameOfValue)) {
			map = this.helperDataManagementService.getParishByDistrictId(parentId);
		} 
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}
*/
	public BaseResponse<Object> updateConfigData(String configDataStr,MultipartFile file){
		
		ConfigDataDTO configDataDTO = new ConfigDataDTO();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			configDataDTO = mapper.readValue(configDataStr, ConfigDataDTO.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		List<ConfigData> listConfigData = new ArrayList<ConfigData>();	
		
		ConfigData configData = new ConfigData();
		configData.setConfigDataId(ConfigDataEnum.EMAIL_AM_BARRING.getConfigDataId());
		configData.setConfigDataName(ConfigDataEnum.EMAIL_AM_BARRING.toString());
		configData.setConfigDataValue(configDataDTO.getAmBarring());
		listConfigData.add(configData);
		
		configData = new ConfigData();
		configData.setConfigDataId(ConfigDataEnum.EMAIL_CP_SUPPORT.getConfigDataId());
		configData.setConfigDataName(ConfigDataEnum.EMAIL_CP_SUPPORT.toString());
		configData.setConfigDataValue(configDataDTO.getCpSupport());
		listConfigData.add(configData);

		configData = new ConfigData();
		configData.setConfigDataId(ConfigDataEnum.DAYS_TO_HOTLINE_NREG.getConfigDataId());
		configData.setConfigDataName(ConfigDataEnum.DAYS_TO_HOTLINE_NREG.toString());
		configData.setConfigDataValue(configDataDTO.getHotlineNonRgeCustomer());
		listConfigData.add(configData);

		configData = new ConfigData();
		configData.setConfigDataId(ConfigDataEnum.DAYS_TO_BARRING_NREG.getConfigDataId());
		configData.setConfigDataName(ConfigDataEnum.DAYS_TO_BARRING_NREG.toString());
		configData.setConfigDataValue(configDataDTO.getBarringNonRgeCustomer());
		listConfigData.add(configData);

		configData = new ConfigData();
		configData.setConfigDataId(ConfigDataEnum.AM_LIMIT_SIMSWAP.getConfigDataId());
		configData.setConfigDataName(ConfigDataEnum.AM_LIMIT_SIMSWAP.toString());
		configData.setConfigDataValue(configDataDTO.getLimitSimSwapDenial());
		listConfigData.add(configData);

		configData = new ConfigData();
		configData.setConfigDataId(ConfigDataEnum.NO_OF_CONNECTION.getConfigDataId());
		configData.setConfigDataName(ConfigDataEnum.NO_OF_CONNECTION.toString());
		configData.setConfigDataValue(configDataDTO.getConnectionsAllowed());
		listConfigData.add(configData);

		configData = new ConfigData();
		configData.setConfigDataId(ConfigDataEnum.DAYS_TO_REJECT_NREG.getConfigDataId());
		configData.setConfigDataName(ConfigDataEnum.DAYS_TO_REJECT_NREG.toString());
		configData.setConfigDataValue(configDataDTO.getRejectBarredGKYC());
		listConfigData.add(configData);
		
		configData = new ConfigData();
		configData.setConfigDataId(ConfigDataEnum.NO_OF_DIGITS_MATCH_ID_SIMSWAP.getConfigDataId());
		configData.setConfigDataName(ConfigDataEnum.NO_OF_DIGITS_MATCH_ID_SIMSWAP.toString());
		configData.setConfigDataValue(configDataDTO.getNoOfDigitsMatchIdSimSwap());
		listConfigData.add(configData);
		
		configData = new ConfigData();
		configData.setConfigDataId(ConfigDataEnum.NO_OF_TIMES_GKYC_STATUS_CHANGED.getConfigDataId());
		configData.setConfigDataName(ConfigDataEnum.NO_OF_TIMES_GKYC_STATUS_CHANGED.toString());
		configData.setConfigDataValue(configDataDTO.getNoOfTimesGkycStatusChanged());
		listConfigData.add(configData);

		try{
			this.helperDataManagementService.updateConfigData(listConfigData);
			if(file!=null && file.getSize()>-1){
				String filePath = appConstants.blackListFilepath+File.separator+"_new"+File.separator+file.getOriginalFilename();
				byte[] bytes = file.getBytes();
				File csvFile = new File(filePath);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(csvFile));
				stream.write(bytes);
				stream.close();
			}
		} catch (BusinessException cause) {
			responseDto = KycResponseUtils.getKycResponse(cause.getExceptionCode(), cause.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		responseDto = KycResponseUtils.getKycResponse(HttpStatus.OK, "Data save sucessfully");
	
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}
	
	@SuppressWarnings("rawtypes")
	public BaseResponse<Object> getConfigData()throws BusinessException{
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		List<ConfigData>  listConfigData = this.helperDataManagementService.getConfigData();
		if (listConfigData == null || listConfigData.size()<=0) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			Map<String,Object> map = new HashMap<String,Object>();
			for(ConfigData configData:listConfigData){
				if(ConfigDataEnum.EMAIL_AM_BARRING.toString().equals(configData.getConfigDataName())){
					map.put("amBarring", configData.getConfigDataValue());
				}
				else if(ConfigDataEnum.EMAIL_CP_SUPPORT.toString().equals(configData.getConfigDataName())){
					map.put("cpSupport", configData.getConfigDataValue());
				}
				else if(ConfigDataEnum.DAYS_TO_HOTLINE_NREG.toString().equals(configData.getConfigDataName())){
					map.put("hotlineNonRgeCustomer", configData.getConfigDataValue());
				}
				else if(ConfigDataEnum.DAYS_TO_BARRING_NREG.toString().equals(configData.getConfigDataName())){
					map.put("barringNonRgeCustomer", configData.getConfigDataValue());
				}
				else if(ConfigDataEnum.AM_LIMIT_SIMSWAP.toString().equals(configData.getConfigDataName())){
					map.put("limitSimSwapDenial", configData.getConfigDataValue());
				}
				else if(ConfigDataEnum.NO_OF_CONNECTION.toString().equals(configData.getConfigDataName())){
					map.put("connectionsAllowed", configData.getConfigDataValue());
				}
				else if(ConfigDataEnum.DAYS_TO_REJECT_NREG.toString().equals(configData.getConfigDataName())){
					map.put("rejectBarredGKYC", configData.getConfigDataValue());
				}
				
				else if(ConfigDataEnum.NO_OF_DIGITS_MATCH_ID_SIMSWAP.toString().equals(configData.getConfigDataName())){
					map.put("noOfDigitsMatchIdSimSwap", configData.getConfigDataValue());
				}
				
				else if(ConfigDataEnum.NO_OF_TIMES_GKYC_STATUS_CHANGED.toString().equals(configData.getConfigDataName())){
					map.put("noOfTimesGkycStatusChanged", configData.getConfigDataValue());
				}
			}
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<?> getCountries() throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();		
		List<CountryDto> departments = this.helperDataManagementService.getCountries();
		if (departments == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched departments");
			baseResponse.setBody(departments);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<Object> getProvince(String nameOfValue, int parentId) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = null;
		if (PROVINCE.equalsIgnoreCase(nameOfValue)) {			
			map = this.helperDataManagementService.getProvince();
		} 
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<Object> getAllProvince() throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = this.helperDataManagementService.getAllProvince();
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<Object> getDistrictByProvinceId(String nameOfValue, int parentId) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = null;
		
		map = this.helperDataManagementService.getDistrictByProvinceId(parentId);
		
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<Object> getVillageByDistrictId(String nameOfValue, int parentId) throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		Map<String, Object> map = null;		
		map = this.helperDataManagementService.getVillageByDistrictId(parentId);		 
		if (map == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched data");
			baseResponse.setBody(map);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public BaseResponse<Object> getAllCountriesWIthCountryCode() throws BusinessException {
		BaseResponse<Object> baseResponse = new BaseResponse<>();
		ResponseDto responseDto = new ResponseDto();
		//List<DepartmentDto> departments = this.helperDataManagementService.getDepartments(slot,startIndex);
		List<CountryDto> countries = this.helperDataManagementService.getAllCountriesWIthCountryCode();
		if (countries == null) {
			responseDto.setResponseCode(203);
			responseDto.setResponseDescription("No content fetched from system.");
			baseResponse.setResponseDto(responseDto);
		} else {
			responseDto.setResponseCode(200);
			responseDto.setResponseDescription("successfully fetched departments");
			CountryDto country =new CountryDto();
			country.setCountryList(countries);
			baseResponse.setBody(country);
		}
		baseResponse.setResponseDto(responseDto);
		return baseResponse;
	}

	@Override
	public void testsms() throws BusinessException {
		
		NotificationDto notificationDto=new NotificationDto();
		notificationDto.setMsisdn("260978980635");
		notificationDto.setTemplateContent("Hi this is test messsage from kyc zambia");
		try {
			integration.notify(notificationDto);
		} catch (IntegrationServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}