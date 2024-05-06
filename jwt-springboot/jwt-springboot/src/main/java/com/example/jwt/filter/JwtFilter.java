package com.example.jwt.filter;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Override
	protected boolean shouldNotFilter(HttpServletRequest httpServletRequest) throws ServletException {

		String[] excludeURIList = {
				"/auth/login", "/auth/signUp", "auth/logout",
				"swagger-ui", "api-docs"
		};
		
		for(String excludeURI : excludeURIList) {
			if(excludeURI.equals(httpServletRequest.getRequestURI())) {
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
			httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
			httpServletResponse.setHeader("Access-Control-Allow-Headers", "authorization");
			httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
			httpServletResponse.setStatus(HttpStatus.OK.value());
			
			return;
		}
		
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
