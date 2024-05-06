package com.example.jwt.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

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

	public static String getAccessToken(HttpServletRequest httpServletRequest) {
		
		String authorization = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
		
		if(authorization != null & authorization.startsWith(TOKEN_PREFIX)) {
			return authorization.substring(TOKEN_PREFIX.length());
		}
		
		return null;
	}

	public static String getRefreshToken(HttpServletRequest httpServletRequest) {
		
		Cookie[] cookieList = httpServletRequest.getCookies();
		
		if(cookieList == null) {
			return null;
		}
		
		for(Cookie cookie: cookieList) {
			if(cookie.getName().equals(REFRESH_COOKIE)) {
				return cookie.getValue();
			}
		}
		
		return null;
	}

}
