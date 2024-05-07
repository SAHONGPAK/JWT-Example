package com.example.jwt.service;

import org.springframework.stereotype.Service;

import com.example.jwt.dto.RequestSignUpDto;
import com.example.jwt.dto.UserDto;
import com.example.jwt.entity.UserEntity;
import com.example.jwt.exception.InvalidEmailException;
import com.example.jwt.exception.MismatchPasswordException;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.util.FormatUtil;
import com.example.jwt.util.HashUtil;
import com.example.jwt.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final HashUtil hashUtil;
	
	public UserServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, HashUtil hashUtil) {
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
		this.hashUtil = hashUtil;
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

	@Override
	public void checkEmail(String userEmail) throws InvalidEmailException {
		
		// 이메일 양식이 아니거나, 이미 존재하는 이메일인 경우 예외 발생.
		if(!FormatUtil.isValidEmail(userEmail) || userRepository.getEmail(userEmail) != 0) {
			throw new InvalidEmailException();
		}

	}

	@Override
	public void signUp(RequestSignUpDto requestSignUpDto) throws MismatchPasswordException {
		
		// 비밀번호와 비밀번호 확인이 다른 경우 예외 발생.
		if(!requestSignUpDto.getUserPassword().equals(requestSignUpDto.getRetryUserPassword())) {
			throw new MismatchPasswordException();
		}

		// 비밀번호를 해시 후 전달.
		requestSignUpDto.setUserPassword(hashUtil.getDigest(requestSignUpDto.getUserPassword()));
		userRepository.saveUser(requestSignUpDto);
		
	}
	
}
