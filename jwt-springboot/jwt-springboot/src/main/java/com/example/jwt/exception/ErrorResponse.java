package com.example.jwt.exception;

public class ErrorResponse {
	
	private int httpStatus;
	private String message;
	private StringBuilder sb;

	
	public ErrorResponse(ErrorCode errorCode) {
		this.httpStatus = errorCode.getHttpStatus();
		this.message = errorCode.getMessage();
	}
	
	public static ErrorResponse of(ErrorCode errorCode) {
		return new ErrorResponse(errorCode);
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
