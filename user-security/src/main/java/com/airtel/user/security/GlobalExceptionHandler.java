/**
 * 
 */
package com.airtel.user.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airtel.user.helper.dto.ResponseBeanDto;
import com.airtel.user.helper.util.BaseMessageSourceAware;


/**
 * @author B0097799
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseMessageSourceAware{
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody ResponseBeanDto defaultErrorHandler(HttpServletRequest req, Exception e){
		ResponseBeanDto rb = new ResponseBeanDto();
		e.printStackTrace();
		rb.setStatusDescription("Something Went wrong. Please try again later.");
		return rb;
	}

}
