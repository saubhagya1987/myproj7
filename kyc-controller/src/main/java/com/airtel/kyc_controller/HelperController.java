package com.airtel.kyc_controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.airtel.kyc.business.HelperDataManagement;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.controller.utils.EncrytptionUtils;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.EncryptedJsonDto;
import com.airtel.kyc.helpers.dto.EncryptedResponseDto;
import com.airtel.kyc.helpers.dto.HelperDto;
import com.airtel.kyc.helpers.dto.ResponseDto;
import com.airtel.kyc.security.EncryptionRSA;
import com.airtel.kyc.security.web.AesUtil;
import com.airtel.kyc.security.web.WebDecryption;
import com.airtel.user.helper.dto.UsersDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Controller
@RequestMapping(value = { "/api/helper" })
@SuppressWarnings("unchecked")
public class HelperController {
	private static Logger logger = Logger.getLogger(HelperController.class);
	private static boolean isDebugEnabled = logger.isDebugEnabled();
	@Autowired
	private HelperDataManagement locationManagement;
	
	@Autowired
	EncryptionRSA encryptionRSA;
	
	@Autowired
	WebDecryption webDecryption;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	EncrytptionUtils encrytptionUtils;
	
	@Autowired
	private ObjectWriter objectWriter;	
	
	@Autowired
	AesUtil aesUtil;

	/*@RequestMapping(value = "/location/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getALLZoneToSiteMapping(Authentication authentication) throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getSiteZoneMapping();
	}*/

	@RequestMapping(value = "/location/all", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getALLZoneToSiteMapping(/*Authentication authentication,*/@RequestBody EncryptedJsonDto encryptedJsonDto) throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			BaseResponse<Object> baseResponse=(BaseResponse<Object>)  this.locationManagement.getSiteZoneMapping();
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}			
		else{
			BaseResponse<Object> userResponse=new BaseResponse<Object>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/	
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
	}
	/*@RequestMapping(value = "/location/zone/{id}/branch", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getBranchesByZone(Authentication authentication, @PathVariable int id)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getLocationListById(KycConstants.BRANCH, id);
	}*/
	@RequestMapping(value = "/location/zone/id/branch", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getBranchesByZone(/*Authentication authentication,*/@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			HelperDto helperDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), HelperDto.class);
			BaseResponse<Object> baseResponse=(BaseResponse<Object>)this.locationManagement.getLocationListById(KycConstants.BRANCH, helperDto.getId());
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			BaseResponse<Object> userResponse=new BaseResponse<Object>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "/location/branch/{id}/cluster", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getClusterByBranch(Authentication authentication, @PathVariable int id)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getLocationListById(KycConstants.CLUSTER, id);
	}*/
	@RequestMapping(value = "/location/branch/id/cluster", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getClusterByBranch(/*Authentication authentication,*/@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			HelperDto helperDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), HelperDto.class);
			BaseResponse<Object> baseResponse=(BaseResponse<Object>)this.locationManagement.getLocationListById(KycConstants.CLUSTER, helperDto.getId());
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<Object> userResponse=new BaseResponse<Object>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "/location/cluster/{id}/sites", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getSitesByCluster(Authentication authentication, @PathVariable int id)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getLocationListById(KycConstants.SITE, id);
	}*/
	
	@RequestMapping(value = "/location/cluster/id/sites", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getSitesByCluster(/*Authentication authentication,*/ @RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			HelperDto helperDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), HelperDto.class);
			BaseResponse<Object> baseResponse=(BaseResponse<Object>)this.locationManagement.getLocationListById(KycConstants.SITE, helperDto.getId());
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}		
		else{
			BaseResponse<Object> userResponse=new BaseResponse<Object>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	
	/*@RequestMapping(value = "/location/zone", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getZones(Authentication authentication)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getLocationListById(KycConstants.ZONE, 0);
	}*/
	
	@RequestMapping(value = "/location/zone", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getZones(/*Authentication authentication,*/ @RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){			
			BaseResponse<Object> baseResponse=(BaseResponse<Object>)this.locationManagement.getLocationListById(KycConstants.ZONE, 0);
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}		
		else{
			BaseResponse<Object> userResponse=new BaseResponse<Object>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@RequestMapping(value = "/departments", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getDepartments(Authentication authentication,@PathVariable int slot,@PathVariable int startIndex)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getDepartments(slot,startIndex);
	}*/
	@RequestMapping(value = "/departments", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getDepartments(Authentication authentication)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getDepartments();
	}
	
	@RequestMapping(value = "/getAllCountriesWIthCountryCode", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getAllCountriesWIthCountryCode(Authentication authentication)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getAllCountriesWIthCountryCode();
	}
	
	/*@RequestMapping(value = "/location/counrty/region", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getALLRegionTodistrictSiteMapping(Authentication authentication) throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getRegionBranchMapping();
	}*/
	
	//new
	/*@RequestMapping(value = "/location/region", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getRegion(Authentication authentication)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getRegions(KycConstants.REGION, 0);
	}*/
	//new
	/*@RequestMapping(value = "/location/region/{id}/territory", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getTerritoryByRegionId(Authentication authentication, @PathVariable int id)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getTerritoryByRegionId(KycConstants.TERRITORY, id);
	}*/
	//new
	/*@RequestMapping(value = "/location/territory/{id}/district", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getDistrictByTerritoryId(Authentication authentication, @PathVariable int id)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getDistrictByTerritoryId(KycConstants.DISTRICT, id);
	}*/
	
	/*@RequestMapping(value = "/location/district/{id}/parish", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getParishByDistrictId(Authentication authentication, @PathVariable int id)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getParishByDistrictId(KycConstants.PARISH, id);
	}
	*/
	//new 
	/*@RequestMapping(value = "/location/getAllRegion", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getAllRegion(Authentication authentication)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getAllRegion();
	}*/
	//new
	/*@RequestMapping(value = "/location/region/getTerritoryList", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getTerritoryList(Authentication authentication,@RequestBody UsersDto usersDto)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getTerritoryList(KycConstants.TERRITORY,usersDto);
	}*/
	
	/*@RequestMapping(value = "/location/region/getDistrictList", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getDistrictList(Authentication authentication,@RequestBody UsersDto usersDto)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getDistrictList(KycConstants.TERRITORY,usersDto);
	}*/
	
	/*@RequestMapping(value = "/users/getAllRolesWithSubRoles", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getAllRolesWithSubRoles(Authentication authentication)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getAllRoles();
	}*/
	
	@RequestMapping(value = "/users/getAllRolesWithSubRoles", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getAllRolesWithSubRoles(/*Authentication authentication,*/@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			BaseResponse<Object> baseResponse=(BaseResponse<Object>)this.locationManagement.getAllRoles();
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			if(isDebugEnabled){
				logger.debug("finalEnc"+finalEnc);				
			}
			else{
				logger.info("finalEnc"+finalEnc);
			}
			
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<Object> userResponse=new BaseResponse<Object>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	@RequestMapping(value = "/location/district", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getDistrictList()
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getDistrictList();
	}
	
	@RequestMapping(value = "/location/departments", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getDepartmentList(Authentication authentication)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getDepartmentList();
	}
	@RequestMapping(value = "/getAllRoles", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getAllRoles()
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getAllRoleList();
	}
	
	/*@RequestMapping(value = "/getAllRoles", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getAllRoles(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			BaseResponse<Object> baseResponse=(BaseResponse<Object>) this.locationManagement.getAllRoleList();
			String encJson = objectWriter.writeValueAsString(baseResponse);
			byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<Object> userResponse=new BaseResponse<Object>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}*/
	
	/*@RequestMapping(value = "/getRoleIdByUserName/{userName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getRoleIdByUserName(Authentication authentication,@PathVariable String userName)
			throws Exception {
		UsersDto usersDto=new UsersDto();
		usersDto.setUserName(userName);
		return (BaseResponse<Object>) this.locationManagement.getRoleIdByUserName(usersDto);
	}*/
	
	@RequestMapping(value = "/getRoleIdByUserName/userName", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getRoleIdByUserName(/*Authentication authentication,*/@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			//HelperDto helperDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), HelperDto.class);
			UsersDto usersDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), UsersDto.class);			
			BaseResponse<Object> baseResponse=(BaseResponse<Object>)this.locationManagement.getRoleIdByUserName(usersDto);			
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<Object> userResponse=new BaseResponse<Object>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	

	@RequestMapping(value = "/updateConfigData", method = RequestMethod.POST, produces = "application/json",consumes = "multipart/*")
	public @ResponseBody BaseResponse<Object> updateConfigData(@RequestParam(value="configData") String configData,
			@RequestParam(value="file",required=false) MultipartFile file)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.updateConfigData(configData,file);
	}
	
	/*@RequestMapping(value = "/getConfigData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getConfigData()
			throws Exception {
		System.out.println("Hello");
		return (BaseResponse<Object>) this.locationManagement.getConfigData();
	}*/
	
	@RequestMapping(value = "/getConfigData", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto getConfigData(@RequestBody EncryptedJsonDto encryptedJsonDto)
			throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			BaseResponse<Object> baseResponse=(BaseResponse<Object>)this.locationManagement.getConfigData();		
			String encJson = objectWriter.writeValueAsString(baseResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		else{
			BaseResponse<Object> userResponse=new BaseResponse<Object>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			userResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(userResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	@RequestMapping(value = "/countries", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getCountries(Authentication authentication)throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getCountries();
	}
	
	@RequestMapping(value = "/location/province", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getProvince(Authentication authentication)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getProvince(KycConstants.PROVINCE, 0);
	}
	
	@RequestMapping(value = "/location/province/getDistrictList", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getDistrictList(Authentication authentication,@RequestBody UsersDto usersDto)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getDistrictList(KycConstants.PROVINCE,usersDto);
	}
	
	@RequestMapping(value = "/location/getAllProvince", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getAllProvince(Authentication authentication)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getAllProvince();
	}
	
	@RequestMapping(value = "/location/province/{id}/district", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getDistrictByProvinceId(Authentication authentication, @PathVariable int id)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getDistrictByProvinceId(KycConstants.DISTRICT, id);
	}
	
	@RequestMapping(value = "/location/district/{id}/village", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getVillageByDistrictId(Authentication authentication, @PathVariable int id)
			throws Exception {
		return (BaseResponse<Object>) this.locationManagement.getVillageByDistrictId(KycConstants.VILLAGE, id);
	}

}
