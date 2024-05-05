package com.example.jwt.dto;

import java.util.Date;

/**
 * 토큰의 정보를 전달하기 위한 클래스.
 */
public class TokenDto {
	private String userEmail;
	private String token;
	private String hashedToken;
	private long issuedAt;
	private long expiration;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getHashedToken() {
		return hashedToken;
	}
	public void setHashedToken(String hashedToken) {
		this.hashedToken = hashedToken;
	}
	public long getIssuedAt() {
		return issuedAt;
	}
	public void setIssuedAt(long issuedAt) {
		this.issuedAt = issuedAt;
	}
	public long getExpiration() {
		return expiration;
	}
	public void setExpiration(long expiration) {
		this.expiration = expiration;
	}
	public Date getDateIssuedAt() {
		return new Date(this.issuedAt);
	}
	public Date getDateExpiration() {
		return new Date(this.expiration);
	}
}
