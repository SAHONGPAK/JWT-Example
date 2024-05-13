package com.example.jwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry
			.addMapping("/**") // 허용하려는 API 요청 경로
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용하려는 HTTP Method 설정 (OPTIONS는 Preflight 설정)
			.allowedOrigins("http://localhost:5173/") // 허용하려는 클라이언트 측 주소
			.allowCredentials(true) // HttpOnly Cookie를 사용하기 위한 설정
			.exposedHeaders("Access-Control-Allow-Headers")
			.exposedHeaders("Authorization") // 클라이언트에서 해당 Header의 값을 확인할 수 있도록 설정.
			.maxAge(3600); // Preflight Cache 설정
		
		WebMvcConfigurer.super.addCorsMappings(registry);
	}
}
