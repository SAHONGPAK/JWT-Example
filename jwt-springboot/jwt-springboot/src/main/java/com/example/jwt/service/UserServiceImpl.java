package com.example.jwt.service;

import org.springframework.stereotype.Service;

import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	
	public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public UserDto myPage(String accessToken) {
		
		String userEmail = jwtUtil.getUserEmail(accessToken, "AccessToken");
		
		UserEntity userEntity = userRepository.getUser(userEmail);
		
		UserDto userDto = new UserDto();
		userDto.setUserEmail(userEntity.getEmail());
		userDto.setUserName(userEntity.getName());
		
		return userDto;
	}
	
}
