package com.example.jwt.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	
	REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED.value(), "비정상 토큰입니다."),
	NOT_FOUND_USER(HttpStatus.BAD_REQUEST.value(), "사용자를 찾을 수 없습니다.");
	
	private int httpStatus;
	private String message;
	
	ErrorCode(int httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
