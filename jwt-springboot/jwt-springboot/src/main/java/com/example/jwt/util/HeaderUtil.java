package com.example.jwt.util;

public class HeaderUtil {
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String REFRESH_COOKIE = "RefreshToken";
	private static final String TOKEN_PREFIX = "Bearer ";

	public static String getAuthorizationHeaderName() {
		return AUTHORIZATION_HEADER;
	}

	public static String getTokenPrefix() {
		return TOKEN_PREFIX;
	}

	public static String getRefreshCookieName() {
		return REFRESH_COOKIE;
	}

}
