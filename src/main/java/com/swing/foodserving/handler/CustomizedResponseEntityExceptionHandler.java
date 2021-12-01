package com.swing.foodserving.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
	  List<String> errorList=new ArrayList<String>();
	  Iterator<ObjectError> it=ex.getBindingResult().getAllErrors().iterator();
	  while(it.hasNext()) {
		  errorList.add(it.next().getDefaultMessage());
	  }
	  return null;
	  //return new ResponseEntity<Object>(Wrapper.responseValidationFailed(errorList, "Validation Failed!"), HttpStatus.OK);
  } 
}