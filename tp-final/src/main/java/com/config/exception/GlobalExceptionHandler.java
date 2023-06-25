package com.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.logging.*;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> todosErrores(Exception ex, WebRequest req) {
		logger.info(ex.getMessage());
		return new ResponseEntity("Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		System.out.println("Ha ocurrido un error: " + ex.getMessage());
		return ResponseEntity.badRequest().body("Hubo un error, por favor intente con un ID válido.");
	}
}
