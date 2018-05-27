/*package com.airtel.kyc.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.util.StringUtils;

public class OauthEntryPoint extends OAuth2AuthenticationEntryPoint{
	private String typeName = OAuth2AccessToken.BEARER_TYPE;
	
	private String realmName = "oauth";
	@Override
	protected ResponseEntity<OAuth2Exception> enhanceResponse(ResponseEntity<OAuth2Exception> response, Exception exception) {
		
		
			HttpHeaders headers = response.getHeaders();
			String existing = null;
			if (headers.containsKey("WWW-Authenticate")) {
				existing = extractTypePrefix(headers.getFirst("WWW-Authenticate"));
			}
			StringBuilder builder = new StringBuilder();
			builder.append(typeName+" ");
			builder.append("realm=\"" + realmName + "\"");
			if (existing!=null) {
				builder.append(", "+existing);
			}
			HttpHeaders update = new HttpHeaders();
			update.putAll(response.getHeaders());
			update.set("WWW-Authenticate", builder.toString());
			return new ResponseEntity<OAuth2Exception>(response.getBody(), update, response.getStatusCode());
		}
	
	private String extractTypePrefix(String header) {
				String existing = header;
				String[] tokens = existing.split(" +");
				if (tokens.length > 1 && !tokens[0].endsWith(",")) {
					existing = StringUtils.arrayToDelimitedString(tokens, " ").substring(existing.indexOf(" ") + 1);
				}
				return existing;
			}

}
*/