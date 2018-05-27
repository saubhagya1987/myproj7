package com.airtel.kyc.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.ClassPathResource;
 
@EnableCaching
@Configuration
@ComponentScan(basePackages = "com.airtel")
public class AppConfig {
 
    //EhCache based CacheManager, most commonly used in Enterprise applications.
  /* @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }
	
 
    @Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factory.setShared(true);
        return factory;
    }*/
     
    
	
	@Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }
	
	@Bean
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factory.setShared(true);
        return factory;
    }
	
	
	/*@Bean
    public CacheManager cacheManager() {
		URL myUrl = getClass().getResource("ehcache.xml"); 
		XmlConfiguration xmlConfig = new XmlConfiguration(myUrl); 
		 CacheManager myCacheManager = CacheManagerBuilder
				  .newCacheManager(xmlConfig);
		 return myCacheManager;
    }*/
	
	
	
	
}
