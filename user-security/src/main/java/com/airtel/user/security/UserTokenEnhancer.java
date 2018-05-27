package com.airtel.user.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.constant.UserConstants;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.services.UserService;

public class UserTokenEnhancer implements TokenEnhancer {

	@Autowired
	private UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication authentication) {
		final Map<String, Object> additionalInfo = new HashMap<String, Object>();
		additionalInfo.put("roles", authentication.getAuthorities());
		String userName = authentication.getName();
		UsersDto userDto = null;
		try {
			userDto = userService.loadUserByAuuid(userName);
			if(userDto==null){
				userDto = userService.loadUserByName(userName);	
			}

		} catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		HashMap<String, Object> parameterMap = new HashMap<>();
		List<RoleDto> childRoleList = new ArrayList<>();

		if (userDto != null && userDto.getRoles() != null) {
			List<RoleDto> roles = userDto.getRoles();

			for (RoleDto roleDto : roles) {

				String parentRole = roleDto.getUserRoleName();
				if (parentRole != null && parentRole.length() > 0)
					try {
						parameterMap.put("parentRoleName", parentRole);
						parameterMap.put("statusFlag", UserConstants.TRUE);

						List<RoleDto> dtos = userService.getRole(parameterMap);
						childRoleList.addAll(dtos);

					} catch (UserBusinessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		}

		if (userDto != null && userDto.getBulkEnabled().equals("1"))
			additionalInfo.put("hasBulkRights", true);
		else
			additionalInfo.put("hasBulkRights", false);

		additionalInfo.put("userName", userName);
		additionalInfo.put("userId", userDto.getUserId());
		additionalInfo.put("deviceId", userDto.getDeviceId());
		additionalInfo.put("isPasswordModified", userDto.getIsPasswordModified());
		additionalInfo.put("roleId", userDto.getRoles().get(0).getRoleId());
		additionalInfo.put("childRoleList", childRoleList);
		System.out.println("IsPasswordModified():----"+userDto.getIsPasswordModified());
		System.out.println("roleid:----"+userDto.getRoles().get(0).getRoleId());
		String name=userName;
		
		System.out.println("about to enter");
		if (userDto.getUserDetails() != null && userDto.getUserDetails().size() >0) {
			 name =  userDto.getUserDetails().get(0).getFirstName();
		}
		additionalInfo.put("name", name);
		
		((DefaultOAuth2AccessToken) token).setAdditionalInformation(additionalInfo);
		return token;
	}

}
