package com.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralException extends Exception {
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> generalExceptionHandler(Exception ex) {
		System.out.println("Ha ocurrido un error: " + ex.getMessage());
		return ResponseEntity.internalServerError().body("Ha ocurrido un error. Por favor intentar más tarde");
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		System.out.println("Ha ocurrido un error: " + ex.getMessage());
		return ResponseEntity.badRequest().body("Hubo un error, por favor intente con un ID válido.");
	}
}
