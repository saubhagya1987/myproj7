package com.airtel.user.security;

public class MYTokenServices extends org.springframework.security.oauth2.provider.token.DefaultTokenServices{
	
	private org.springframework.security.oauth2.provider.token.TokenStore tokenStore;
	private org.springframework.security.oauth2.provider.ClientDetailsService clientDetailsService;
	private org.springframework.security.oauth2.provider.token.TokenEnhancer TokenEnhancer ;
	
	
	public org.springframework.security.oauth2.provider.token.TokenStore getTokenStore() {
		return tokenStore;
	}
	public void setTokenStore(
			org.springframework.security.oauth2.provider.token.TokenStore tokenStore) {
		this.tokenStore = tokenStore;
		super.setTokenStore(tokenStore);
	}
	public org.springframework.security.oauth2.provider.ClientDetailsService getClientDetailsService() {
		return clientDetailsService;
	}
	public void setClientDetailsService(
			org.springframework.security.oauth2.provider.ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
		super.setClientDetailsService(clientDetailsService);
	}
	
	public org.springframework.security.oauth2.provider.token.TokenEnhancer getTokenEnhancer() {
		return TokenEnhancer;
	}
	public void setTokenEnhancer(
			org.springframework.security.oauth2.provider.token.TokenEnhancer tokenEnhancer) {
		TokenEnhancer = tokenEnhancer;
		super.setTokenEnhancer(tokenEnhancer);
	}
	public MYTokenServices(){
		super();
	}
}
