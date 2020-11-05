package com.matheusaraujo.estudoDeCaso.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.matheusaraujo.estudoDeCaso.service.exceptions.ObjectNotFoundException;
import com.matheusaraujo.estudoDeCaso.service.exceptions.DataIntegrityViolationException;

@ControllerAdvice
public class ResourceExceptionhandler {
	
	//Tratando a exceção: ObjectNotFound
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest http){
		StandartError erro = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandartError> dataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest http){
		StandartError erro = new StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandartError> validation(MethodArgumentNotValidException e, HttpServletRequest http){
		
		
		ValidationError erro = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		for(FieldError field: e.getBindingResult().getFieldErrors()) {
			erro.addError(field.getField(), field.getDefaultMessage());
		}		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
