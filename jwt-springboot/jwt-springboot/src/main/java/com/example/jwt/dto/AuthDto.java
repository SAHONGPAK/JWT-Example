package com.example.jwt.dto;

/**
 * AccessToken, RefreshToken, MaxAge를 전달하기위한 클래스.
 */
public class AuthDto {
	private String accessToken;
	private String refreshToken;
	private long maxAge;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public long getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(long maxAge) {
		this.maxAge = maxAge;
	}
}
