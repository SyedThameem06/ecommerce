/**
 * 
 * Author Syed Thameem
 * 
 * File Name: ExceptionManager
 * File Description: Controller Advice for entire application.
 * 
 */
package com.abc.eshop.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.abc.eshop.dto.response.GlobalResponse;
import com.abc.eshop.service.MessageByLocaleService;
import com.abc.eshop.util.Constants;

@ControllerAdvice
public class ExceptionManager extends ResponseEntityExceptionHandler{
	Logger logger = LoggerFactory.getLogger(ExceptionManager.class);
	@Autowired
	MessageByLocaleService localResolver;
	
	@ExceptionHandler(GlobalException.class)
	public ResponseEntity<Object> genericException(GlobalException res) {
		GlobalResponse response = new GlobalResponse(res.getResponseCode() != 0 ? res.getResponseCode() : -1, 
													res.getErrorCode() != null ? res.getErrorCode() : Constants.internalErrorCode, 
													res.getErrorCode() != null ? localResolver.getErrorMessage(res.getErrorCode()) : localResolver.getErrorMessage(Constants.internalErrorCode));
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> genericException(Exception e) {
		logger.error("Error Occured : {}",e);
		e.printStackTrace();
		
		GlobalResponse response = new GlobalResponse(-1,Constants.internalErrorCode,localResolver.getErrorMessage(Constants.internalErrorCode)) ;
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
