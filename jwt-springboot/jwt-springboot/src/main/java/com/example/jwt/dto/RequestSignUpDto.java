package com.example.jwt.dto;

/**
 * 회원가입에 필요한 데이터를 전달하는 클래스.
 */
public class RequestSignUpDto {
	private String userEmail;
	private String userName;
	private String userPassword;
	private String retryUserPassword;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getRetryUserPassword() {
		return retryUserPassword;
	}
	public void setRetryUserPassword(String retryUserPassword) {
		this.retryUserPassword = retryUserPassword;
	}
}
