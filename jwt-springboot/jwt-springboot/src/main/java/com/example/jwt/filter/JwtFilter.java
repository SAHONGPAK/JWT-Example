package com.example.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.jwt.exception.ErrorCode;
import com.example.jwt.exception.ErrorResponse;
import com.example.jwt.service.AuthService;
import com.example.jwt.util.HeaderUtil;
import com.example.jwt.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	private final AuthService authService;
	
	public JwtFilter(JwtUtil jwtUtil, AuthService authService ) {
		this.jwtUtil = jwtUtil;
		this.authService = authService;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest httpServletRequest) throws ServletException {
		String[] excludeURIList = {
				"/auth/login", "/auth/signUp", "auth/logout",
				"swagger-ui", "api-docs"
		};
		
		for(String excludeURI : excludeURIList) {
			if(httpServletRequest.getRequestURI().contains(excludeURI)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {

		if(httpServletRequest.getMethod().equals("OPTIONS")) {
			httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
			httpServletResponse.setHeader("Access-Control-Allow-Headers", "content-type, authorization");
			httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
			httpServletResponse.setStatus(HttpStatus.OK.value());
			
			return;
		}
		
		
		
		if(httpServletRequest.getRequestURI().contains("/auth/refresh")) {
			
			String refreshToken = HeaderUtil.getRefreshToken(httpServletRequest);
			
			if(refreshToken != null) {
				
				if(!jwtUtil.isValidToken(refreshToken, "RefreshToken") || !authService.isValidToken(refreshToken)) {
					
					httpServletResponse.setStatus(ErrorCode.REFRESH_TOKEN_EXPIRED.getHttpStatus());
					httpServletResponse.setContentType("application/json;charset=UTF-8");
					
					ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.REFRESH_TOKEN_EXPIRED);
					
					PrintWriter printWriter = httpServletResponse.getWriter();
					printWriter.print(errorResponse.toString());
					return;
				}
			}
			
		}
		else {
			
			String accessToken = HeaderUtil.getAccessToken(httpServletRequest);
			
			if(accessToken == null || !jwtUtil.isValidToken(accessToken, "AccessToken") || !authService.isValidToken(accessToken)) {
				httpServletResponse.setStatus(ErrorCode.REFRESH_TOKEN_EXPIRED.getHttpStatus());
				httpServletResponse.setContentType("application/json;charset=UTF-8");
				httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
				
				ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.REFRESH_TOKEN_EXPIRED);
				
				PrintWriter printWriter = httpServletResponse.getWriter();
				printWriter.print(errorResponse.toString());
				return;
			}
		}
		
		
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
