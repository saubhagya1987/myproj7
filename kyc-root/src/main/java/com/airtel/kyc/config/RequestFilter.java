package com.airtel.kyc.config;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.airtel.kyc.helpers.dto.EncryptedJsonDto;
import com.airtel.kyc.security.EncryptionRSA;
import com.airtel.kyc.security.web.RSAUtils;
import com.airtel.kyc.security.web.WebDecryption;
import com.airtel.kyc.utils.DateUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Component("requestFilter")
public class RequestFilter implements Filter {
	@Autowired
	private EncryptionRSA encryptionRSA;
	@Autowired
	private WebDecryption webDecryption;
	/*@Autowired
	private MessageSource messageSource;*/
	
	@Autowired
	private Environment env;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//System.out.println("-- In MyFilter --");
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();

		if (uri.startsWith("/kyczm/api/user/validateUser")) {
			//System.out.println("URI: " + req.getRequestURI());

			try {
				MyRequestWrapper myRequestWrapper = new MyRequestWrapper((HttpServletRequest) request,
						(HttpServletResponse) response);
				if (myRequestWrapper.getBody() != null) {
					chain.doFilter(myRequestWrapper, response);
				}
			} catch (Exception e) {
				chain.doFilter(request, response);
			}
		}

		else if (uri.startsWith("/kyczm/client/invalidateToken/")
				|| uri.startsWith("/kyczm/api/user/getUserImage/")
				|| uri.startsWith("/kyczm/api/subscriber/getTotalCount")
				|| uri.startsWith("/kyczm/api/subscriber/getSubscriberImage1")
				|| uri.startsWith("/kyczm/api/subscriber/getSubscriberImage")
				|| uri.startsWith("/kyczm/api/users/getAllRolesWithSubRoles")
				|| uri.startsWith("/kyczm/api/helper/location/region/getTerritoryList")
				|| uri.startsWith("/kyczm/api/helper/location/region/getDistrictList")
				|| uri.startsWith("/kyczm/api/helper/getAllCountriesWIthCountryCode")
				|| uri.startsWith("/kyczm/api/helper/location/departments")
				|| uri.startsWith("/kyczm/api/helper/departments")
				|| uri.startsWith("/kyczm/api/helper/location/region")
				|| uri.startsWith("/kyczm/api/helper/location/district")
				|| uri.startsWith("/kyczm/api/helper/updateConfigData")
				|| uri.startsWith("/kyczm/api/helper/getAllRoles")
				|| uri.startsWith("/kyczm/api/helper/location/getAllProvince")				
				|| uri.startsWith("/kyczm/oauth/token")
				|| uri.startsWith("/kyczm/api/gkyc/getGkycStatus")	
				|| uri.startsWith("/kyczm/api/subscriber/biData")
				|| uri.startsWith("/kyczm/api/subscriber/bulk/register")
				|| uri.startsWith("/kyczm/api/user/bulk/register")
				|| uri.startsWith("/kyczm/api/subscriber/validateSIM")) {
			chain.doFilter(request, response);
		} else {
			//System.out.println("URI: " + req.getRequestURI());
			// System.out.println(req.getReader().readLine().);

			// String body = myRequestWrapper.getBody();

			try {
				MyRequestWrapper myRequestWrapper = new MyRequestWrapper((HttpServletRequest) request,
						(HttpServletResponse) response);
				if (myRequestWrapper.getBody() != null) {
					chain.doFilter(myRequestWrapper, response);
				}
				/*
				 * else{ HttpServletResponse resp= (HttpServletResponse)
				 * response;
				 * resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				 * resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				 * "Bad parameters passed") ; }
				 */
			} catch (Exception e) {
				chain.doFilter(request, response);
			}
		}

	}

	@Override
	public void destroy() {
	}

	public class MyRequestWrapper extends HttpServletRequestWrapper {
		private String body;
		InputStream inputStream = null;

		public MyRequestWrapper(HttpServletRequest request, HttpServletResponse response) throws Exception {
			super(request);

			try {
				String json = null;
				try {
					json = request.getReader().readLine().toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//System.out.println("json" + json);
				if (json.length() > 1) {

					Gson gson = new Gson();

					EncryptedJsonDto encryptedJsonDto = (EncryptedJsonDto) gson.fromJson(json, EncryptedJsonDto.class);

					//System.out.println("one" + 1);
					String encKey = encryptedJsonDto.getEncyptredKey();
					String decryptedKey1 = null;
					Boolean mobileFlag = false;
					Boolean exceptionFlag = false;
					Long time = null;
					try {
						decryptedKey1 = RSAUtils.decrypt(encKey,
								"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALPTuwcl5CR06jOMNfgxAlAm8q30Dz3zJJ/Ar75csLrr+44HKgXwvfxJoWciw3FR7CRi3ksKrgCE1wP7QHQkB+L3uKHpXd8jIFIzNLREYS7P97EOHGwm8/O0qD0+ndRPu1FNZ2IjgzvbiSkkP1jnf4qzvF3dVr65IS02Dua93gQRAgMBAAECgYAhAwp/im4yXyArbF7v/Bc/nZbJZldx+PPNO/NHT/7FodKhRSFqy8Ik0WOG6nPUlsRx/ZBvWDuPLtPGEFbJu1+S9PtJ2Ba59KmgA4932ZhGQ8V+soB7uMV0XZSyLWpWive2t4jJUgdr/5TLzofGhapjP86lttOFZVmwmfS/2t2lTQJBAOi+tDYeqXDPar3LS8yDgNrCgQBzTtVmIElV6E8NzuS6qE2Gu+DvDbLKW/evx/nq51t7oHKSuDLUe/70lwnliIMCQQDFy3XUxPxiuPvGiTBw4kzHUMEmjW02ohR3aAp1ip4KitPI9/k/5zqrt9Bb3xqBK1VQFY4Veh755zIKwE7RSBTbAkEAgKTn9wH7pd7/oOg9fU0ya7gWlakmac2JCv/w58E6GjMR08glEK8RY0F2k/EVF44/UtGCXdG6/l10K/gFg/LbAQJAbx8+ZLoCnSgV81VRzKmmvvekfBdKez4Vx3vBHIYzAfM6xpZiBr/Om3iaa3LtPhJtTJ+/gltombeEyrt9zF/1rwJBAI2DO/GNksr6a2z7KSETePTGpZhpYTpA7/EQ8JOpl4GJj/keO4jR83EoqNQ3xiZsTqjG9X8UUa6glWhvR9Fclzc=");
					} catch (Exception e) {
						mobileFlag = true;
					}
					if (!mobileFlag) {
						byte[] valueDecoded = Base64.decodeBase64(decryptedKey1);
						//System.out.println("base64String" + valueDecoded);
						String sdecode = new String(valueDecoded);
						//System.out.println("sdecode" + sdecode);

						try {
							time = Long.valueOf(sdecode);
						} catch (Exception e) {

							exceptionFlag = true;
							e.printStackTrace();

						}
					}
					if (!exceptionFlag && !mobileFlag) {
						String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(time));
						Date serverDate = new Date();
						String formattedDate = DateUtils.getFormatDate(serverDate, "yyyy-MM-dd HH:mm");
						// Date finalDate=DateUtils.getDate(formattedDate,
						// "yyyy-MM-dd HH:mm");
						// if(date!=null){
						// if(date.equals(formattedDate)){
						String secret = encryptedJsonDto.getEncyptredKey();
						String decryptedKey = RSAUtils.decrypt(secret,
								"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALPTuwcl5CR06jOMNfgxAlAm8q30Dz3zJJ/Ar75csLrr+44HKgXwvfxJoWciw3FR7CRi3ksKrgCE1wP7QHQkB+L3uKHpXd8jIFIzNLREYS7P97EOHGwm8/O0qD0+ndRPu1FNZ2IjgzvbiSkkP1jnf4qzvF3dVr65IS02Dua93gQRAgMBAAECgYAhAwp/im4yXyArbF7v/Bc/nZbJZldx+PPNO/NHT/7FodKhRSFqy8Ik0WOG6nPUlsRx/ZBvWDuPLtPGEFbJu1+S9PtJ2Ba59KmgA4932ZhGQ8V+soB7uMV0XZSyLWpWive2t4jJUgdr/5TLzofGhapjP86lttOFZVmwmfS/2t2lTQJBAOi+tDYeqXDPar3LS8yDgNrCgQBzTtVmIElV6E8NzuS6qE2Gu+DvDbLKW/evx/nq51t7oHKSuDLUe/70lwnliIMCQQDFy3XUxPxiuPvGiTBw4kzHUMEmjW02ohR3aAp1ip4KitPI9/k/5zqrt9Bb3xqBK1VQFY4Veh755zIKwE7RSBTbAkEAgKTn9wH7pd7/oOg9fU0ya7gWlakmac2JCv/w58E6GjMR08glEK8RY0F2k/EVF44/UtGCXdG6/l10K/gFg/LbAQJAbx8+ZLoCnSgV81VRzKmmvvekfBdKez4Vx3vBHIYzAfM6xpZiBr/Om3iaa3LtPhJtTJ+/gltombeEyrt9zF/1rwJBAI2DO/GNksr6a2z7KSETePTGpZhpYTpA7/EQ8JOpl4GJj/keO4jR83EoqNQ3xiZsTqjG9X8UUa6glWhvR9Fclzc=");
						String cipherText = encryptedJsonDto.getEncyptedValue();
						String decryptedData = webDecryption.decrypt(decryptedKey, cipherText);
						encryptedJsonDto.setEncyptedValue(decryptedData);
						String privateKeyPath = env.getProperty("encryption.key.path");
						String tokenKey = encryptedJsonDto.getTokenKey();
						String tokenValue = encryptedJsonDto.getTokenValue();
						String decryptedTokenKey = null;
						try {
							decryptedTokenKey = encryptionRSA.decrypt(String.valueOf(tokenKey), privateKeyPath, "", "");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (GeneralSecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							String decryptedTokenData = encryptionRSA.dataDecrypt(Base64.decodeBase64(tokenValue),
									decryptedTokenKey);
							encryptedJsonDto.setTokenValue(decryptedTokenData);
							//System.out.println("decryptedTokenData: " + decryptedTokenData);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (GeneralSecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// }
						// }
					} else if (!exceptionFlag && mobileFlag) {
						String encryptedKey = encryptedJsonDto.getEncyptredKey();
						String encryptedData = encryptedJsonDto.getEncyptedValue();
						String privateKeyPath = env.getProperty("encryption.key.path");
						//System.out.println("privateKeyPath: " + privateKeyPath);
						String decryptedKey = null;
						try {
							decryptedKey = encryptionRSA.decrypt(String.valueOf(encryptedKey), privateKeyPath, "", "");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (GeneralSecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							String decryptedData = encryptionRSA.dataDecrypt(Base64.decodeBase64(encryptedData),
									decryptedKey);
							encryptedJsonDto.setEncyptedValue(decryptedData);
							//System.out.println("decryptedData: " + decryptedData);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (GeneralSecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String tokenKey = encryptedJsonDto.getTokenKey();
						String tokenValue = encryptedJsonDto.getTokenValue();
						String decryptedTokenKey = null;
						try {
							decryptedTokenKey = encryptionRSA.decrypt(String.valueOf(tokenKey), privateKeyPath, "", "");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (GeneralSecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							String decryptedTokenData = encryptionRSA.dataDecrypt(Base64.decodeBase64(tokenValue),
									decryptedTokenKey);
							encryptedJsonDto.setTokenValue(decryptedTokenData);
							//System.out.println("decryptedTokenData: " + decryptedTokenData);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (GeneralSecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					ObjectMapper mapper = new ObjectMapper();
					//System.out.println("encryptedJsonDto:" + mapper.writeValueAsString(encryptedJsonDto));
					body = mapper.writeValueAsString(encryptedJsonDto);
					//System.out.println("body:" + body);
				} else {
					HttpServletResponse resp = (HttpServletResponse) response;
					resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bad parameters passed");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			ServletInputStream servletInputStream = null;
			if (body != null) {
				final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
				servletInputStream = new ServletInputStream() {
					public int read() throws IOException {
						return byteArrayInputStream.read();
					}

					@Override
					public boolean isFinished() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public boolean isReady() {
						// TODO Auto-generated method stub
						return false;
					}

					@Override
					public void setReadListener(ReadListener arg0) {
						// TODO Auto-generated method stub
					}
				};
			}

			return servletInputStream;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			return new BufferedReader(new InputStreamReader(this.getInputStream()));
		}

		public String getBody() {
			return this.body;
		}
	}

}