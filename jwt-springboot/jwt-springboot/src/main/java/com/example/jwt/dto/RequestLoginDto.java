package com.example.jwt.dto;

/**
 * 클라이언트에서 전송되는 로그인 데이터를 받기위한 클래스.
 */
public class RequestLoginDto {
	private String userEmail;
	private String userPassword;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
