/**
 * 
 */
package com.airtel.user.security.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.airtel.user.helper.dto.ResponseBeanDto;
import com.airtel.user.helper.dto.StatusBean;

/**
 * @author B0073698
 *
 */
@Controller
@RequestMapping(value = "/client")
public class InvalidateTokenController {

	private final Log logger = LogFactory.getLog(InvalidateTokenController.class);

		//@Autowired
		//private DefaultTokenServices tokenServices;


	 	@Autowired
	    private TokenStore tokenStore;

	    @RequestMapping(value = "/invalidateToken/{tokenValue}", method = RequestMethod.GET)
	    @ResponseStatus(HttpStatus.OK)
	    public ResponseEntity<ResponseBeanDto> invalidateAccessToken(Authentication authentication, /*@RequestParam Map<String, String> parameters*/
	    		@PathVariable String tokenValue) {
	      
	    	/* String authHeader = request.getHeader("authorization");
	        if (authHeader != null) {
	            String tokenValue = authHeader.replace("bearer", "").trim();
	            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
	            tokenStore.removeAccessToken(accessToken);
	        }*/
	    	
	    	
	    	String methodName = "invalidateAccessToken::";
			logger.debug(methodName + "starts");

			try {
				/*if(authentication instanceof OAuth2Authentication){
					OAuth2AccessToken token = tokenStore.getAccessToken((OAuth2Authentication)authentication);
					tokenStore.removeAccessToken(token);
				}*/
				if(tokenValue!=null){
				boolean token=revokeToken(tokenValue);	
				}
				else {
					return getResponse(new StatusBean("000", "Logout failed"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				return getResponse(new StatusBean("000", "Logout failed"));
				
			} catch (Throwable t) {
				t.printStackTrace();
				return getResponse(new StatusBean("000", "Logout failed"));
			}

			logger.debug(methodName + "ends");
			
			return getResponse(new StatusBean("200", "Logout Success"));
	    }
	
	private ResponseEntity<ResponseBeanDto> getResponse(StatusBean errorBean) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cache-Control", "no-store");
		headers.set("Pragma", "no-cache");
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseBeanDto rb = new ResponseBeanDto();
		rb.setStatusCode(200);
		rb.setStatusDescription("Success");
		return new ResponseEntity<ResponseBeanDto>(rb, headers, HttpStatus.OK);
	}
	
	public boolean revokeToken(String tokenValue) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue+"LOGOUT");
        if (accessToken == null) {
            return false;
        }
        if (accessToken.getRefreshToken() != null) {
            tokenStore.removeRefreshToken(accessToken.getRefreshToken());
        }
        tokenStore.removeAccessToken(accessToken);
        return true;
    }


}
