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
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
			.allowedOrigins("http://localhost:5173")
			.allowCredentials(true)
			.exposedHeaders("Authorization")
			.maxAge(3600);
		
		WebMvcConfigurer.super.addCorsMappings(registry);
	}
}
