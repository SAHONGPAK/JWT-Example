package com.example.jwt.dto;

/**
 * 클라이언트에게 전송하는 사용자 데이터 클래스 (myPage)
 */
public class UserDto {
	private String userEmail;
	private String userName;
	
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
}
