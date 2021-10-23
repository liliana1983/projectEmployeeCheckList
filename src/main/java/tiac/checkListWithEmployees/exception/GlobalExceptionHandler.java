package tiac.checkListWithEmployees.exception;

import java.io.FileNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import tiac.checkListWithEmployees.Util.RestError;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?>resourceNotFaundException(ResourceNotFoundException e,ServletWebRequest request){
		return new ResponseEntity<>(new RestError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<?>nullPointerException(NullPointerException e,ServletWebRequest request){
		return new ResponseEntity<>(new RestError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<?>fileNotFoundException(FileNotFoundException e,ServletWebRequest request){
		return new ResponseEntity<>(new RestError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?>sqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e,ServletWebRequest request){
		return new ResponseEntity<>(new RestError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ObjectAlreadyExistsException.class)
	public ResponseEntity<?>objectAlreadyExistsException(ObjectAlreadyExistsException e,ServletWebRequest request){
		return new ResponseEntity<>(new RestError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> noSuchEelementException(NoSuchElementException e, ServletWebRequest request){
		return new ResponseEntity<>(new RestError(HttpStatus.NOT_FOUND.value(),e.getMessage()),HttpStatus.NOT_FOUND);
	}
}
