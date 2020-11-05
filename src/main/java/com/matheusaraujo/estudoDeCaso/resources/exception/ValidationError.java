package com.matheusaraujo.estudoDeCaso.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Integer status, String mensagem, Long timeStamp) {
		super(status, mensagem, timeStamp);		
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
	
	

}
