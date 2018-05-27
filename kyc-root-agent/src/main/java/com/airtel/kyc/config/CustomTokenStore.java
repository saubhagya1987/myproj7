package com.airtel.kyc.config;



import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

public class CustomTokenStore extends JdbcTokenStore{
	Logger LOG=Logger.getLogger(CustomTokenStore.class);
	private static final String DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT = "select token_id, token from oauth_access_token where token_id = ?";
	
	private String selectAccessTokenSql = DEFAULT_ACCESS_TOKEN_SELECT_STATEMENT;
	
	public CustomTokenStore(DataSource dataSource) {
		super(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		// TODO Auto-generated constructor stub
	}





	private final JdbcTemplate jdbcTemplate;
	
	
	public OAuth2AccessToken readAccessToken(String tokenValue) {
				OAuth2AccessToken accessToken = null;
				String logOut = "";
				if(tokenValue.contains("LOGOUT")){
					logOut = "LOGOUT";
					tokenValue = tokenValue.replaceAll("LOGOUT", "");
				}
				    System.out.println("tokenValue"+tokenValue);
				    String base64String=tokenValue;
		        	byte[] valueDecoded= Base64.decodeBase64(base64String );
		        	System.out.println("base64String"+base64String);
		        	String sdecode= new String(valueDecoded);
		        	System.out.println("sdecode"+sdecode);
		
				try {
					if(tokenValue!=null){
						
						if("LOGOUT".equals(logOut)){
						//Getting User ID
							String token=extractTokenKey(tokenValue);
							String query = "select user_id from users u inner join oauth_access_token a on a.user_name=u.user_name or a.user_name=u.auuid where token_id=?"; 
					        Object[] inputs = new Object[] {token};
					        String userName = jdbcTemplate.queryForObject(query, inputs, String.class);
					        
					        if(userName!=null && !"".equals(userName)){
						        //Getting subscriber details id
								String query1 = "select subscriber_details_id from subscriber_work_flow where action='PENDING' and user_id=?"; 
						        Object[] inputs1 = new Object[] {userName};
						        String subDetailsId = jdbcTemplate.queryForObject(query1, inputs1, String.class);
						        
						        if(subDetailsId!=null && !"".equals(subDetailsId)){
							        //Getting work flow id
									String query2 = "select subscriber_work_flow_id from subscriber_work_flow where action='PENDING' and user_id=?"; 
							        Object[] inputs2 = new Object[] {userName};
							        String subWorkFlowId = jdbcTemplate.queryForObject(query2, inputs2, String.class);
						        
							        //Delete commaond on subscriber work flow
							        jdbcTemplate.update("delete from subscriber_work_flow where subscriber_work_flow_id = ?",subWorkFlowId);
							        //Update on subscriber details
							        jdbcTemplate.update("update subscriber_details set assignment_flag = null where subscriber_details_id = ?",subDetailsId);
							        
							        //Update on assignment
							        jdbcTemplate.update("update assignment set count = 0 where user_id = ?",userName);
						        }
						        //Update users last login
						        jdbcTemplate.update("update users set last_login = SYSDATE-1 where user_id = ?",userName);
					        }
						}
						System.out.println("if block");
						accessToken = jdbcTemplate.queryForObject(selectAccessTokenSql, new RowMapper<OAuth2AccessToken>() {
							public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
								return deserializeAccessToken(rs.getBytes(2));
							}
						}, extractTokenKey(sdecode));	
						
						/*System.out.println("if block");
						accessToken = jdbcTemplate.queryForObject(selectAccessTokenSql, new RowMapper<OAuth2AccessToken>() {
							public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
								return deserializeAccessToken(rs.getBytes(2));
							}
						}, extractTokenKey(tokenValue));*/
						
					}
					else{
						System.out.println("else block");
						accessToken = jdbcTemplate.queryForObject(selectAccessTokenSql, new RowMapper<OAuth2AccessToken>() {
							public OAuth2AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
								return deserializeAccessToken(rs.getBytes(2));
							}
						}, extractTokenKey(tokenValue));
						
					}
					
				}
				catch (EmptyResultDataAccessException e) {
					removeAccessToken(tokenValue);
					if (LOG.isInfoEnabled()) {
						LOG.info("Failed to find access token for token " + sdecode);
					}
				}
				catch (IllegalArgumentException e) {
					LOG.warn("Failed to deserialize access token for " + sdecode);
					removeAccessToken(tokenValue);
				}
		
				return accessToken;
			}

}