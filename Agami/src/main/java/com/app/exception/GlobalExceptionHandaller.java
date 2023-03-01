package com.app.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandaller {
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDeatils> AdminExceptionHandaller(AdminException ex, WebRequest re){
		ErrorDeatils err=new ErrorDeatils();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescp(re.getDescription(false));
		return new ResponseEntity<ErrorDeatils>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDeatils> UserExceptionHandaller(UserException ex, WebRequest re){
		ErrorDeatils err=new ErrorDeatils();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescp(re.getDescription(false));
		return new ResponseEntity<ErrorDeatils>(err,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDeatils> ExceptionHandaller(Exception ex, WebRequest re){
		ErrorDeatils err=new ErrorDeatils();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescp(re.getDescription(false));
		return new ResponseEntity<ErrorDeatils>(err,HttpStatus.BAD_REQUEST);
	}
	
	
}	
