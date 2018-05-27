package com.airtel.user.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.util.PasswordEncoder;
import com.airtel.user.services.UserService;

public class CustomAuthentication implements AuthenticationProvider {

	private static Logger logger = Logger.getLogger(CustomAuthentication.class);

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {
			UserDetails user = loadUser(authentication.getName(), String.valueOf(authentication.getCredentials()));
			
			if (user != null) {
				if (PasswordEncoder.isMatched(String.valueOf(authentication.getCredentials()), user.getPassword())
						|| user.getPassword().equals(String.valueOf(authentication.getCredentials()))) {
					Md5PasswordEncoder md5 = new Md5PasswordEncoder();
					md5.setEncodeHashAsBase64(false);
					String encodedPwd = md5.encodePassword(String.valueOf(authentication.getCredentials()), null);					
					Authentication auth = new UsernamePasswordAuthenticationToken(
							authentication.getName().toLowerCase(), encodedPwd, user.getAuthorities());
					return auth;
				} else {
					throw new UsernameNotFoundException("Invalid Username or password");
				}
			} else {
				throw new UsernameNotFoundException("Invalid Username or password!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationCredentialsNotFoundException("Invalid username/password");
		} catch (Throwable e) {
			e.printStackTrace();
			throw new AuthenticationCredentialsNotFoundException("Invalid username/password");
		}

	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return arg0.equals(UsernamePasswordAuthenticationToken.class);
	}

	@SuppressWarnings("rawtypes")
	private UserDetails loadUser(String username, String pwd) throws UsernameNotFoundException {
		UsersDto user = null;
		org.springframework.security.core.userdetails.UserDetails userDetails=null;
		try {
			user = userService.loadUserByName(username);
			
			
			if (user != null) {
				
				/*if(user.getAuuIdFlag())
					user.setPassword(username);*/
				
				 List authorities = buildUserAuthority(user.getRoles());
				  return buildUserForAuthentication(user, authorities);
			}
			else
				new UsernameNotFoundException("Invalid Username");
			
		}catch (UserBusinessException e) {
			// TODO Auto-generated catch block
			
		}
		
		return userDetails;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private User buildUserForAuthentication(UsersDto user, List authorities) {
		return new User(user.getUserName(), user.getPassword(), true, true, true, true, authorities);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List buildUserAuthority(List<RoleDto> userRoles) {
		Set setAuths = new HashSet();
		// Build user's authorities
		for (RoleDto userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRoleName()));
		}
		List Result = new ArrayList(setAuths);
		return Result;
	}

}
