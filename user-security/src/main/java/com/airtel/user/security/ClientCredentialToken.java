package com.airtel.user.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.airtel.africa.ssf.service.SSFService;
import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.constant.UserConstants;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.exception.UserDaoException;
import com.airtel.user.services.UserService;

public class ClientCredentialToken extends ClientCredentialsTokenEndpointFilter {

	@Autowired
	private JdbcClientDetailsService clientDetails;

	@Autowired
	private UserService userService;
	
	
	
	@Autowired
	private SSFService ssFservice;
	//private IntegrationService integrationService;
	
	@SuppressWarnings("unused")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String grant_type = request.getParameter("grant_type");
		String refresh_token = request.getParameter("refresh_token");
		String client_id = request.getParameter("client_id");
		String client_secret = request.getParameter("client_secret");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String ldapPassword = request.getParameter("ldapPassword");
		
		String login_from = request.getParameter("login_from");
		
		
		
		/*String appVersion = request.getParameter("appVersion");
		
		if(UserConstants.MOBILE.equalsIgnoreCase(login_from))
			try {
				boolean flag=userService.checkAppVersion(appVersion);
				
				if(!flag)
					throw new AuthenticationCredentialsNotFoundException("APPVERSION miss match.");
							
			} catch (UserBusinessException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		*/
				
		
		if (grant_type != null && grant_type.equals("password")) {
			
			boolean isAdmin = false;
			
			UsersDto user=null;
			Boolean checkLdap  =false;
			try{
				 checkLdap  =this.ssFservice.authenticateUser(username, ldapPassword);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
			if (checkLdap) {
				
				try {
					user = userService.loadUserByAuuid(username);
					//user.setPassword(password);
					//user.setAuuId(username);
					//userService.updateUser(user);
					//userService.updateClientOuathDeatils(user);
				} catch (UserBusinessException  e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				client_secret=client_id;
				password=client_id;
				
				//request.setAttribute("password", password);
				
			}
			else
			{
				if(!checkLdap){
				try {
					user = userService.loadUserByName(username);
					
				} catch (UserBusinessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				}
			}
			
			List<RoleDto> roles = null;
			if (user != null) {
				
				/*if(user.getIsActive()==0)
					throw new UsernameNotFoundException("User is not active. Contact to Admin");
					*/
				roles = user.getRoles();
				for (RoleDto userRole : roles) {
					
					if(UserConstants.MOBILE.equalsIgnoreCase(login_from) && userRole.getMobileAccessFlag()==UserConstants.FALSE)
					{
						throw new AuthenticationCredentialsNotFoundException("User is not authorized on Mobile");
					}
					
					if(UserConstants.WEB.equalsIgnoreCase(login_from) && userRole.getWebAccessFlag()==UserConstants.FALSE)
					{
						throw new AuthenticationCredentialsNotFoundException("User is not authorized on WEB.");
					}
					
					String roleName=userRole.getRoleName();
					
					if (!roleName.equals("FSE"))
						isAdmin = true;
					
				}
			}
			else
			{
				throw new UsernameNotFoundException("Invalid User Name.");
			}
			
			
			

			if (isAdmin && !checkLdap) {
				ClientDetails cd = null;
				try {
					cd = clientDetails.loadClientByClientId(client_id);
				} catch (NoSuchClientException e) {

				}
				if (cd != null) {
					
					Authentication auth = this.getAuthenticationManager()
							.authenticate(new UsernamePasswordAuthenticationToken(client_id, client_secret,
									buildUserAuthority(roles)));
					return auth;
					
					/*if (user != null && user.getDeviceId() != null && user.getDeviceId().equals(cd.getClientId())) {
						System.out.println("CASE1:Admin Doing SET UP OR LOGIN AS FSE");
						@SuppressWarnings("unchecked")
						Authentication auth = this.getAuthenticationManager()
								.authenticate(new UsernamePasswordAuthenticationToken(client_id, client_secret,
										buildUserAuthority(roles)));
						return auth;

					} else {
						// Set setAuths = new HashSet();
						// setAuths.add(new
						// SimpleGrantedAuthority("ROLE_BLOCK"));
						System.out.println("CASE2:ADMIN FOUND,CLIENT FOUND BUT ADMIN!=CLIENT");
						throw new AuthenticationCredentialsNotFoundException("NOT AUTHORIZED TO USE THIS DEVICE!");
						// Authentication auth
						// =this.getAuthenticationManager().authenticate( new
						// UsernamePasswordAuthenticationToken(client_id,client_secret,setAuths));
						// return auth;
					}*/
					
				} else {
					// Set setAuths = new HashSet();
					// setAuths.add(new SimpleGrantedAuthority("ROLE_BLOCK"));
					System.out.println("CASE2:Admin CLIENT DETAILS NOT FOUND!DB ISSUE");
					// Authentication auth
					// =this.getAuthenticationManager().authenticate( new
					// UsernamePasswordAuthenticationToken(client_id,client_secret,setAuths));
					// return auth;
					throw new AuthenticationCredentialsNotFoundException("NOT AUTHORIZED TO USE");
				}

			} else {
				ClientDetails cd = null;
				try {
					cd = clientDetails.loadClientByClientId(client_id);
				} catch (NoSuchClientException e) {

				}
				if (cd != null) {
					//request.setAttribute("password", username);
					/*UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
					 token.setDetails(new WebAuthenticationDetails(request));
					 Authentication authentication = this.getAuthenticationManager().authenticate(token);
					SecurityContextHolder.getContext().setAuthentication(authentication);
*/
					
					
					Authentication auth = this.getAuthenticationManager()
							.authenticate(new UsernamePasswordAuthenticationToken(cd.getClientId(),
									client_secret, cd.getAuthorities()));
					return auth;
					
					/*if (user != null) {
						if (user.getDeviceId() != null && user.getDeviceId().equals(cd.getClientId())) {
							System.out.println("CASE4:USER USING HIS DEVICE");
							Authentication auth = this.getAuthenticationManager()
									.authenticate(new UsernamePasswordAuthenticationToken(cd.getClientId(),
											client_secret, cd.getAuthorities()));
							return auth;
						} else {
							// Set setAuths = new HashSet();
							// setAuths.add(new
							// SimpleGrantedAuthority("ROLE_BLOCK"));
							System.out.println("CASE5:USER USING OTHER DEVICE");
							// Authentication auth
							// =this.getAuthenticationManager().authenticate(
							// new
							// UsernamePasswordAuthenticationToken(cd.getClientId(),client_secret,setAuths));
							// return auth;
							throw new AuthenticationCredentialsNotFoundException("NOT AUTHORIZED TO USE THIS DEVICE!");
						}
					} else {
						System.out.println("CASE7:USER NOT REGISTERED");
						// Authentication auth
						// =this.getAuthenticationManager().authenticate( new
						// UsernamePasswordAuthenticationToken(cd.getClientId(),client_secret,cd.getAuthorities()));
						// return auth;
						throw new AuthenticationCredentialsNotFoundException("UNREGISTERED USER!");
					}*/
				} else {
					System.out.println("CASE2:FSE CLIENT DETAILS NOT FOUND!DB ISSUE");
					throw new AuthenticationCredentialsNotFoundException("NOT AUTHORIZED TO USE");

				}
			}
			/*
			 * }else{ Set setAuths = new HashSet(); setAuths.add(new
			 * SimpleGrantedAuthority("ROLE_BLOCK"));
			 * System.out.println("CASE6:USER USING OTHER DEVICE");
			 * Authentication auth
			 * =this.getAuthenticationManager().authenticate( new
			 * UsernamePasswordAuthenticationToken(client_id,client_secret,
			 * setAuths)); return auth; }
			 */
		} else {
			ClientDetails cd = null;
			try {
				cd = clientDetails.loadClientByClientId(client_id);
			} catch (NoSuchClientException e) {
				System.out.println("CASE10:Unknown Client");
				throw e;
			}
			if (cd != null) {
				// OAuth2AccessToken
				// token=tokenServices.refreshAccessToken(refresh_token, new
				// TokenRequest(null, client_id, cd.getScope(), grant_type));
				Authentication auth = this.getAuthenticationManager().authenticate(
						new UsernamePasswordAuthenticationToken(client_id, client_secret, cd.getAuthorities()));
				return auth;
			} else {
				// Set setAuths = new HashSet();
				// setAuths.add(new SimpleGrantedAuthority("ROLE_BLOCK"));
				System.out.println("CASE11:Unknown Client");
				throw new AuthenticationCredentialsNotFoundException("Unknown Client!");
			}
		}

	}

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {

		String grantType=request.getParameter("grant_type");
		if (grantType.equalsIgnoreCase("password"))
			return true;
		else
			return false;
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