package com.example.jwt.exception;

public class ErrorResponse {
	
	private int httpStatus;
	private String message;
	private StringBuilder sb = new StringBuilder();

	
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
	
	/**
	 * JwtFilter에서 응답하기위한 포맷 설정.
	 */
	public String toString() {
		sb.append("{").append("\n")
			.append("\"httpStatus\": ").append(this.httpStatus).append("\n")
			.append("\"message\": ").append("\"").append(this.message).append("\"").append("\n")
			.append("}");
		
		return sb.toString();
	}
}
