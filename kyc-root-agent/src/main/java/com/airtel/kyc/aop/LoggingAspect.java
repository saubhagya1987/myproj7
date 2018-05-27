package com.airtel.kyc.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

@Aspect
@Component
public class LoggingAspect {

	@Autowired
	private ObjectWriter objectWriter;
	
	private static Logger logger=Logger.getRootLogger();
		
	@Before("execution(* com.airtel.kyc.controllers..*.*(..))")
	public void logBefore(JoinPoint joinPoint) throws JsonProcessingException {

		
		logger.info("***start ******");
		logger.info("****Method Name**" + joinPoint.getSignature().getName());
		logger.info("****Method Signature : **" + joinPoint.getSignature());
		logger.info("****Agruments Passed***"  + objectWriter.writeValueAsString(joinPoint.getArgs()));
		logger.info("*********");
	}
	
	@AfterReturning(pointcut = "execution(* com.airtel.kyc.controllers..*.*(..)) ", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) throws JsonProcessingException {

		logger.info("*********");
		logger.info("****Method Name**" + joinPoint.getSignature().getName());
		logger.info("****Method Signature : **" + joinPoint.getSignature());
		logger.info("****Method returned value is : " + objectWriter.writeValueAsString(result));
		logger.info("***End***");
	}
	
	@AfterThrowing(pointcut = "execution(*  com.airtel.kyc.business..*(*)) || execution(* com.airtel.kyc.service..*(*)) ", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

		logger.error("*********");
		logger.error("****Exception arise on Method Name**" + joinPoint.getSignature().getName());
		logger.error("****Exception arise on Class: **" + joinPoint.getSignature());
		logger.error("****Exception : " + error.getStackTrace());
		logger.error("****Exception : " + error.toString());
		logger.error("****Exception : " , error);
		logger.error("***End***");

	}	
}
