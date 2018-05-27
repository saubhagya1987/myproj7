package com.airtel.kyc_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.kyc.business.HelperDataManagement;
import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.SystemException;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.helpers.dto.TestRequestDto;
import com.airtel.kyc.helpers.dto.TestResponseDto;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

@RestController
public class TestController {
	/*@Autowired
	TestService testService;*/
	
	@Autowired
	private HelperDataManagement locationManagement;
	
	@Autowired
	private ObjectWriter objectWriter;	

	
	@Autowired
	Gson gson;
	
	@RequestMapping(value = "/testRequest", method = RequestMethod.POST)
	public TestResponseDto getEvents(@RequestBody String requestDto)throws BusinessException,SystemException { 
		System.out.println("test controller");
		TestRequestDto dto=gson.fromJson(requestDto, TestRequestDto.class);
		TestResponseDto responseDto=new TestResponseDto();
		responseDto.setUserName(dto.getUserName());
		return responseDto;
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public TestResponseDto save(@RequestBody TestRequestDto requestDto)throws BusinessException,SystemException { 
		System.out.println("test controller");
		TestResponseDto responseDto=new TestResponseDto();
		//testService.save(requestDto);
		responseDto.setStatus(HttpStatus.OK.name());
		return responseDto;
		
    }
	
	@RequestMapping(value = "/testRequest1", method = RequestMethod.POST)
	public TestResponseDto testRequest1(@RequestBody SubscriberDto requestDto)throws BusinessException,SystemException { 
		System.out.println("test controller");
		TestResponseDto responseDto=new TestResponseDto();
		responseDto.setUserName(requestDto.getMsisdn());
		return responseDto;
    }
	
	@RequestMapping(value = "/api/testsms", method = RequestMethod.GET)
	public TestResponseDto testsms()throws BusinessException,SystemException { 
		System.out.println("test controller");
		locationManagement.testsms();
		return null;
    }
	
	/*@RequestMapping(value = "/location/region/getTerritoryList", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody BaseResponse<Object> getTerritoryList(@RequestBody UsersDto usersDto)
			throws Exception {
		System.out.println(usersDto.getUserId());
		return (BaseResponse<Object>) this.locationManagement.getTerritoryList(KycConstants.TERRITORY,usersDto);
	}*/

}
