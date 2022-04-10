package com.moviedatabase.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler 
	public ResponseEntity<ErrorObject> handleEntryNotFound (EntryNotFound ex) {
	ErrorObject eObj = new ErrorObject();
	eObj.setStatusCode(HttpStatus.NOT_FOUND.value());
	eObj.setMessage(ex.getMessage());
	eObj.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorObject>(eObj, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
    public ResponseEntity<ErrorObject> handleEntryAlreadyExists (EntryAlreadyExists ex) {
	ErrorObject eObj = new ErrorObject();
	eObj.setStatusCode(HttpStatus.NOT_FOUND.value());
	eObj.setMessage(ex.getMessage());
	eObj.setTimeStamp(System.currentTimeMillis());
	
		return new ResponseEntity<ErrorObject>(eObj, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler
    public ResponseEntity<ErrorObject> handleWrongTypeException (WrongTypeException ex) {
	ErrorObject eObj = new ErrorObject();
	eObj.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	eObj.setMessage(ex.getMessage());
	eObj.setTimeStamp(System.currentTimeMillis());
	
		return new ResponseEntity<ErrorObject>(eObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}