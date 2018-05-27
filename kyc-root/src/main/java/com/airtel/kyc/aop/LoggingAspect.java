package com.airtel.kyc.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.airtel.kyc.security.EncryptionRSA;
import com.airtel.kyc.security.web.WebDecryption;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author Airtel
 *
 */
@Aspect
@Component
public class LoggingAspect {

	@Autowired
	private ObjectWriter objectWriter;	
	
	@Autowired
	EncryptionRSA encryptionRSA;
	
	@Autowired
	WebDecryption webDecryption;
	

	
	private static Logger logger=Logger.getRootLogger();	
	
	/**
	 * @param joinPoint
	 * @throws JsonProcessingException
	 */
	@Before("execution(* com.airtel.kyc_controller..*.*(..))")	
	public void logBefore(JoinPoint joinPoint) throws JsonProcessingException {	
		System.out.println("one1");
		
		
		logger.info("***start ******");
		logger.info("****Method Name**" + joinPoint.getSignature().getName());
		logger.info("****Method Signature : **" + joinPoint.getSignature());
		//logger.info("****Agruments Passed***"  + objectWriter.writeValueAsString(joinPoint.getArgs()));		
		logger.info("*********");
	}
	
	
	
	/**
	 * @param joinPoint
	 * @param result
	 * @throws JsonProcessingException
	 */
	@AfterReturning(pointcut = "execution(* com.airtel.kyc_controller..*.*(..)) ", returning = "result")	
	public void logAfterReturning(JoinPoint joinPoint, Object result) throws JsonProcessingException {
		System.out.println("two"+2);
		logger.info("*********");
		logger.info("****Method Name**" + joinPoint.getSignature().getName());
		logger.info("****Method Signature : **" + joinPoint.getSignature());
		logger.info("****Method returned value is : " + objectWriter.writeValueAsString(result));		
		logger.info("***End***");

	}
	
	/**
	 * @param joinPoint
	 * @param error
	 */
	@AfterThrowing(pointcut = "execution(*  com.airtel.kyc.service..*(*)) || execution(* com.airtel.user.services..*(*)) ", throwing = "error")	
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		System.out.println("three"+3);
		logger.error("*********");
		logger.error("****Exception arise on Method Name**" + joinPoint.getSignature().getName());
		logger.error("****Exception arise on Class: **" + joinPoint.getSignature());
		logger.error("****Exception : " + error.getStackTrace());
		logger.error("****Exception : " + error.toString());
		logger.error("****Exception : " , error);
		logger.error("***End***");

	}
	
	
}
