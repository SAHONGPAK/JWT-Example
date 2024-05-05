package com.example.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.jwt.exception.ErrorCode;
import com.example.jwt.exception.ErrorResponse;
import com.example.jwt.exception.NotFoundUserException;

/**
 * Controller에서 발생하는 예외를 처리하는 클래스.
 */
@RestControllerAdvice(annotations=RestController.class)
public class ControllerExceptionHandler {
	
	// 예외가 발생하는 경우 클라이언트에게 해당 예외를 전달.
	// 예외 전달 시 동일한 양식으로 전달.
	
	@ExceptionHandler(value = {NotFoundUserException.class})
	public ResponseEntity<?> notFoundUserHandler(NullPointerException e) {
		
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.NOT_FOUND_USER);
		
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
}
