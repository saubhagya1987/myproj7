package com.airtel.kyc_controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.kyc.business.ReportService;
import com.airtel.kyc.controller.utils.EncrytptionUtils;
import com.airtel.kyc.helpers.dto.EncryptedJsonDto;
import com.airtel.kyc.helpers.dto.EncryptedResponseDto;
import com.airtel.kyc.helpers.dto.ReportRequestDto;
import com.airtel.kyc.helpers.dto.ReportResponse;
import com.airtel.kyc.helpers.dto.ResponseDto;
import com.airtel.kyc.security.EncryptionRSA;
import com.airtel.kyc.security.web.AesUtil;
import com.airtel.kyc.security.web.WebDecryption;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

//@RequestMapping(value = { "/api" })
@RestController
public class ReportController {
	
	private final static Logger LOGGER = Logger.getLogger(ReportController.class);
	@Autowired
	private ReportService reportService;	
	
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
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/channelPartnerDailyReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReportResponse channelPartnerDailyReport(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {
				    
		@SuppressWarnings("unchecked")
		ReportResponse responseBean = reportService.channelPartnerDailyReport(reportRequestDto);		
		return responseBean;
	}*/
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/channelPartnerDailyReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto channelPartnerDailyReport(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			ReportRequestDto reportRequestDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), ReportRequestDto.class);
			ReportResponse responseBean = reportService.channelPartnerDailyReport(reportRequestDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ReportResponse reportResponse=new ReportResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			reportResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(reportResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
		
	}
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/channelPartnerWiseSubReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReportResponse channelPartnerWiseSubReport(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {
				    
		@SuppressWarnings("unchecked")
		ReportResponse responseBean =reportService.channelPartnerWiseSubReport(reportRequestDto);		
		return responseBean;
	}*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/channelPartnerWiseSubReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto channelPartnerWiseSubReport(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)throws Exception {
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			ReportRequestDto reportRequestDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), ReportRequestDto.class);
			ReportResponse responseBean =reportService.channelPartnerWiseSubReport(reportRequestDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ReportResponse reportResponse=new ReportResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			reportResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(reportResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndDailyReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReportResponse sndDailyReport(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {				    
		@SuppressWarnings("unchecked")
		ReportResponse responseBean = reportService.sndDailyReport(reportRequestDto);		
		return responseBean;
	}*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndDailyReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto sndDailyReport(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)throws Exception {				    
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			ReportRequestDto reportRequestDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), ReportRequestDto.class);
			ReportResponse responseBean = reportService.sndDailyReport(reportRequestDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ReportResponse reportResponse=new ReportResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			reportResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(reportResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndUserPerformance", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReportResponse sndUserPerformance(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {				    
		@SuppressWarnings("unchecked")
		ReportResponse responseBean = reportService.sndUserPerformance(reportRequestDto);		
		return responseBean;
	}*/
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndUserPerformance", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto sndUserPerformance(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)throws Exception {				    
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			ReportRequestDto reportRequestDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), ReportRequestDto.class);
			ReportResponse responseBean = reportService.sndUserPerformance(reportRequestDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ReportResponse reportResponse=new ReportResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			reportResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(reportResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndUserQuality", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReportResponse sndUserQuality(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {				    
		@SuppressWarnings("unchecked")
		ReportResponse responseBean = reportService.sndUserQuality(reportRequestDto);		
		return responseBean;
	}*/
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndUserQuality", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto sndUserQuality(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)throws Exception {				    
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			ReportRequestDto reportRequestDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), ReportRequestDto.class);
			ReportResponse responseBean = reportService.sndUserQuality(reportRequestDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ReportResponse reportResponse=new ReportResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			reportResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(reportResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndChannelActivity", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReportResponse sndChannelActivity(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {				    
		@SuppressWarnings("unchecked")
		ReportResponse responseBean = reportService.sndChannelActivity(reportRequestDto);		
		return responseBean;
	}*/
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndChannelActivity", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto sndChannelActivity(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)throws Exception {				    
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			ReportRequestDto reportRequestDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), ReportRequestDto.class);
			ReportResponse responseBean = reportService.sndChannelActivity(reportRequestDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ReportResponse reportResponse=new ReportResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			reportResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(reportResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndChannelPerformance", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReportResponse sndChannelPerformance(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {				    
		@SuppressWarnings("unchecked")
		ReportResponse responseBean = reportService.sndChannelPerformance(reportRequestDto);		
		return responseBean;
	}*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndChannelPerformance", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto sndChannelPerformance(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)throws Exception {				    
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			ReportRequestDto reportRequestDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), ReportRequestDto.class);
			ReportResponse responseBean = reportService.sndChannelPerformance(reportRequestDto);
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ReportResponse reportResponse=new ReportResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			reportResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(reportResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/tbmDailyReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReportResponse tbmDailyReport(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {				    
		@SuppressWarnings("unchecked")
		ReportResponse responseBean = reportService.tbmDailyReport(reportRequestDto);		
		return responseBean;
	}*/
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/tbmDailyReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody EncryptedResponseDto tbmDailyReport(Authentication authentication,@RequestBody EncryptedJsonDto encryptedJsonDto)throws Exception {				    
		EncryptedResponseDto finalResponse=new EncryptedResponseDto();
		if(encryptedJsonDto.getTokenValue().equals("abc")){
			ReportRequestDto reportRequestDto = new ObjectMapper().readValue(encryptedJsonDto.getEncyptedValue(), ReportRequestDto.class);
			ReportResponse responseBean = reportService.tbmDailyReport(reportRequestDto);			
			String encJson = objectWriter.writeValueAsString(responseBean);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
		}
		
		
		else{
			ReportResponse reportResponse=new ReportResponse<>();
			ResponseDto responseDto=new ResponseDto();
			responseDto.setResponseCode(1000);
			responseDto.setResponseDescription("Unauthorized Request");
			responseDto.setExceptionCode(0);
			reportResponse.setResponseDto(responseDto);
			String encJson = objectWriter.writeValueAsString(reportResponse);
			/*byte[] array=encJson.getBytes();
			String encResponse=new String(Base64.encodeBase64(array), "UTF-8");	
			String finalEnc="xgFcem8mVsq7tnT​"+encResponse;			
			finalResponse.setEncryptedValue(finalEnc);*/	
			String finalEnc=aesUtil.encrypt(encJson);
			finalResponse.setEncryptedValue(finalEnc);
			return finalResponse;
			
		}
	}
	
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/channelPartnerWiseSubReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<ReportResponseDto> channelPartnerWiseSubReport(@RequestBody ReportRequestDto reportRequestDto)throws Exception {
				    
		@SuppressWarnings("unchecked")
		BaseResponse<ReportResponseDto> responseBean = (BaseResponse<ReportResponseDto>)reportService.channelPartnerWiseSubReport(reportRequestDto);		
		return responseBean;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/sndDailyReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<ReportResponseDto> sndDailyReport(@RequestBody ReportRequestDto reportRequestDto)throws Exception {				    
		@SuppressWarnings("unchecked")
		BaseResponse<ReportResponseDto> responseBean = (BaseResponse<ReportResponseDto>)reportService.sndDailyReport(reportRequestDto);		
		return responseBean;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/tbmDailyReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<ReportResponseDto> tbmDailyReport(@RequestBody ReportRequestDto reportRequestDto)throws Exception {				    
		@SuppressWarnings("unchecked")
		BaseResponse<ReportResponseDto> responseBean = (BaseResponse<ReportResponseDto>)reportService.tbmDailyReport(reportRequestDto);		
		return responseBean;
	}*/
	
	
	
	/*@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/dataExecutiveDailyReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto dataExecutiveDailyReport(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {				    
		ResponseDto  reportResponseDto=reportService.dataExecutiveDailyReport(reportRequestDto);		
		return reportResponseDto;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/dataExecutiveWiseSubReport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ResponseDto dataExecutiveWiseSubReport(Authentication authentication,@RequestBody ReportRequestDto reportRequestDto)throws Exception {
				    
		ResponseDto  reportResponseDto=reportService.dataExecutiveWiseSubReport(reportRequestDto);		
		return reportResponseDto;
	}
	*/
	/*public EncryptedJsonDto decryption(EncryptedJsonDto encryptedJsonDto) throws JsonProcessingException {
		EncryptedJsonDto dto=new EncryptedJsonDto();
		String encKey=encryptedJsonDto.getEncyptredKey();
		byte[] valueDecoded= Base64.decodeBase64(encKey );
    	System.out.println("base64String"+valueDecoded);
    	String sdecode= new String(valueDecoded);
    	System.out.println("sdecode"+sdecode);
    	Long time=null;
    	Boolean exceptionFlag=false;
    	try{
    		time=Long.valueOf(sdecode);
    	}
    	catch(Exception e){
    		exceptionFlag=true;
    	}
    	
    	if(!exceptionFlag){
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(time));
		Date serverDate=new Date();
		String formattedDate=DateUtils.getFormatDate(serverDate, "yyyy-MM-dd HH:mm");
		
		
		final long ONE_MINUTE_IN_MILLIS=60000;

		Calendar date1 = Calendar.getInstance();
		long t= date1.getTimeInMillis();
		Date beforeThreeMins=new Date(t - (10 * ONE_MINUTE_IN_MILLIS));
		String beforeThreeMinsObj=DateUtils.getFormatDate(beforeThreeMins, "yyyy-MM-dd HH:mm");
		long  dateDiff=serverDate.getTime()-beforeThreeMins.getTime();
		
		//Date finalDate=DateUtils.getDate(formattedDate, "yyyy-MM-dd HH:mm");
		if(date!=null){
			if(date.equals(formattedDate) || dateDiff<=10){
				String secret=encryptedJsonDto.getEncyptredKey();
				String cipherText=encryptedJsonDto.getEncyptedValue();
				String decryptedData=webDecryption.decrypt(secret, cipherText);
				dto.setEncyptedValue(decryptedData);
				
				String privateKeyPath=messageSource.getMessage("encryption.key.path", null, Locale.getDefault());
				String tokenKey=encryptedJsonDto.getTokenKey();
				String tokenValue=encryptedJsonDto.getTokenValue();
				String decryptedTokenKey=null;
				try {
					decryptedTokenKey = encryptionRSA.decrypt(String.valueOf(tokenKey), privateKeyPath, "", "");
					dto.setTokenKey(decryptedTokenKey);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					String decryptedTokenData=encryptionRSA.dataDecrypt(Base64.decodeBase64(tokenValue), decryptedTokenKey);
					dto.setTokenValue(decryptedTokenData);
					System.out.println("decryptedTokenData: "+decryptedTokenData);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	}
		
		else{
			
		String encryptedKey=encryptedJsonDto.getEncyptredKey();
		String encryptedData=encryptedJsonDto.getEncyptedValue();
		String privateKeyPath=messageSource.getMessage("encryption.key.path", null, Locale.getDefault());
		System.out.println("privateKeyPath: "+privateKeyPath);
		String decryptedKey=null;
		try {
			decryptedKey = encryptionRSA.decrypt(String.valueOf(encryptedKey), privateKeyPath, "", "");
			dto.setEncyptredKey(decryptedKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String decryptedData=encryptionRSA.dataDecrypt(Base64.decodeBase64(encryptedData), decryptedKey);
			dto.setEncyptedValue(decryptedData);
			System.out.println("decryptedData: "+decryptedData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		String tokenKey=encryptedJsonDto.getTokenKey();
		String tokenValue=encryptedJsonDto.getTokenValue();
		String decryptedTokenKey=null;
		try {
			decryptedTokenKey = encryptionRSA.decrypt(String.valueOf(tokenKey), privateKeyPath, "", "");
			dto.setTokenKey(decryptedTokenKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String decryptedTokenData=encryptionRSA.dataDecrypt(Base64.decodeBase64(tokenValue), decryptedTokenKey);
			dto.setTokenValue(decryptedTokenData);
			System.out.println("decryptedTokenData: "+decryptedTokenData);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		return dto;
	}*/


}
