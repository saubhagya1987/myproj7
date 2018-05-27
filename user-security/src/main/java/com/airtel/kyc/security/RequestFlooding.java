/*package com.agile.yatra.interceptor;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.agile.yatra.enums.YatraResponseCode;
import com.agile.yatra.exception.YatraException;
import com.agile.yatra.utils.CommonUtility;
import com.agile.yatra.utils.YatraConstants;

@Component
public class RequestFlooding {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private YatraConstants yatraConstants;
	
	public void preventFalseRequest(String key) {
		
		if (!CommonUtility.isValidString(key))  return;
		
		if(redisTemplate.hasKey(key)) {
			throw new YatraException(YatraResponseCode.REQUEST_FLOODING);
		} else {
			String subkey = key.substring(yatraConstants.requestKeyStart, yatraConstants.requestKeyEnd);
			redisTemplate.opsForValue().set(subkey, subkey);
			redisTemplate.expire(key, yatraConstants.requestCacheExpiry, TimeUnit.SECONDS);
		}
	}
}*/