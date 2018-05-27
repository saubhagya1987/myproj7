/*package com.airtel.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.user.business.UserBusinessService;
import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.ResponseBeanDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.persistence.exception.UserDaoException;

@RestController
public class TestRoleController {
	@Autowired
	private UserBusinessService userBusinessService;
	
	
	
	@RequestMapping(value = "users/getAllRolesWithSubRoles", method = RequestMethod.GET)
	public  ResponseBeanDto<List<RoleDto>> getAllRolesWithSubRoles(Authentication authentication) throws UserBusinessException, UserDaoException  {
		return (ResponseBeanDto<List<RoleDto>>) userBusinessService.getAllRoles();
	}
	
	

}
*/