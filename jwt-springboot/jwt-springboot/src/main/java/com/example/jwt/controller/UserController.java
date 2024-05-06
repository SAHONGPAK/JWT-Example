package com.example.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.dto.UserDto;
import com.example.jwt.service.UserService;
import com.example.jwt.util.HeaderUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/myPage")
	public ResponseEntity<?> myPage(HttpServletRequest httpServletRequest) {
		
		String accessToken = HeaderUtil.getAccessToken(httpServletRequest);
		
		UserDto userDto = userService.myPage(accessToken);
		
		return ResponseEntity.ok().body(userDto);
	}
	
}
