package com.vinicius.rinhabackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

	@ControllerAdvice
	public class ResourceExceptionHandler {

		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<HttpStatus> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}	
		
		@ExceptionHandler(ResourceAlreadyExists.class)
		public ResponseEntity<HttpStatus> resourceAlreadyExists(ResourceAlreadyExists e, HttpServletRequest request) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}	
		
		@ExceptionHandler(CustomValidationException.class)
		public ResponseEntity<HttpStatus> validationError(CustomValidationException e, HttpServletRequest request) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		@ExceptionHandler(CustomEmptyException.class)
		public ResponseEntity<HttpStatus> validateEmpty(CustomEmptyException e, HttpServletRequest request) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<HttpStatus> argumentValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		@ExceptionHandler(MissingServletRequestParameterException.class)
		public ResponseEntity<HttpStatus> QueryParamValidation(MissingServletRequestParameterException e, HttpServletRequest request) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		@ExceptionHandler(HttpMessageNotReadableException.class)
		public ResponseEntity<HttpStatus> validateHttp(HttpMessageNotReadableException e, HttpServletRequest request) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		

}
