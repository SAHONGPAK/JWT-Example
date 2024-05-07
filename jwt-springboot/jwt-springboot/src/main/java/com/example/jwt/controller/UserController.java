package com.example.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.dto.RequestSignUpDto;
import com.example.jwt.dto.UserDto;
import com.example.jwt.exception.InvalidEmailException;
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
	
	
	@GetMapping("/checkEmail/{userEmail}")
	public ResponseEntity<?> checkEmail(@PathVariable String userEmail){
		
		// 클라이언트로부터 전달받은 이메일을 검증.
		userService.checkEmail(userEmail);
		
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody RequestSignUpDto requestSignUpDto) {
		
		// 클라이언트로부터 전달받은 회원가입 정보 전달.
		userService.signUp(requestSignUpDto);
		
		return ResponseEntity.ok().build();
	}
	
}
