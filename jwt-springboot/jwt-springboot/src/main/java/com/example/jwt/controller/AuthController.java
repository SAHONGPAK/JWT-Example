package com.example.jwt.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.dto.AuthDto;
import com.example.jwt.dto.RequestLoginDto;
import com.example.jwt.dto.TokenDto;
import com.example.jwt.service.AuthService;
import com.example.jwt.util.HeaderUtil;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 * Controller는 데이터를 전달받고, Service에 넘겨주고,
 * 그 결과를 받아 클라이언트에 전송하는 역할만 진행한다.
 * 
 */

@RestController // REST Service를 위한 Controller 임을 명시.
@RequestMapping("/auth") // /auth와 일치하는 모든 URI는 이 Controller가 제어함.
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody RequestLoginDto requestLoginDto) {
		
		// 1. 클라이언트로부터 전달받은 requestLoginDto 바탕으로,
		//	  AccessToken과 RefreshToken을 생성한다.
		AuthDto authDto = authService.login(requestLoginDto);
		
		// 2. AcessToken은 HTTP Header의 Authorization으로 전달한다.
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HeaderUtil.getAuthorizationHeaderName(), HeaderUtil.getTokenPrefix() + authDto.getAccessToken());
		
		// 3. RefreshToken은 HttpOnly Cookie로 전달한다.
		//	  SPA(Single Page Application)에서는 기본적으로 장기간 사용하는 토큰을 권장하지 않는다.
		//    https://cheatsheetseries.owasp.org/cheatsheets/JSON_Web_Token_for_Java_Cheat_Sheet.html#token-sidejacking
		ResponseCookie responseCookie = ResponseCookie
				.from(HeaderUtil.getRefreshCookieName(), authDto.getRefreshToken())
				.domain("localhost") // 어떤 사이트에서 쿠키를 사용할 수 있도록 허용할 지 설정.
				.path("/") // 위 사이트에서 쿠키를 허용할 경로를 설정.
				.httpOnly(true) // HTTP 통신을 위해서만 사용하도록 설정.
				.secure(true) // Set-Cookie 설정.
				.maxAge(authDto.getMaxAge()) // RefreshToken과 동일한 만료 시간으로 설정.
				.sameSite("None") // 동일한 사이트에서 사용할 수 있도록 설정 None: 동일한 사이트가 아니어도 된다.
				.build();

		return ResponseEntity.ok()
				.headers(httpHeaders).header(HttpHeaders.SET_COOKIE, responseCookie.toString())
				.build();
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
		
		// 1. HTTP Header의 Authorization (AccessToken)을 추출.
		String accessToken = HeaderUtil.getAccessToken(httpServletRequest);

		// 2. logout 진행.
		authService.logout(accessToken);
		
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/refresh")
	public ResponseEntity<?> refresh(HttpServletRequest httpServletRequest) {
		
		String refreshToken = HeaderUtil.getRefreshToken(httpServletRequest);
		
		TokenDto tokenDto = authService.reGenerateToken(refreshToken);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HeaderUtil.getAuthorizationHeaderName(), HeaderUtil.getTokenPrefix() + tokenDto.getToken());
		
		return ResponseEntity.ok()
				.headers(httpHeaders)
				.build();
	}
}
