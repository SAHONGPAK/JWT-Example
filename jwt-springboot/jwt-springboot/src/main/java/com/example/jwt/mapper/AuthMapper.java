package com.example.jwt.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.jwt.dto.RequestLoginDto;
import com.example.jwt.dto.TokenDto;
import com.example.jwt.entity.UserEntity;

@Mapper
public interface AuthMapper {

	/**
	 * 클라이언트로부터 전달받은 사용자 이메일과 패스워드 기반으로 해당하는 사용자 객체 조회.
	 */
	@Select("SELECT * FROM user WHERE email=#{userEmail} AND password=#{userPassword}")
	UserEntity selectByRequestLoginDto(RequestLoginDto requestLoginDto);

	
	/**
	 * 로그인 진행 시 발급한 Token을 저장.
	 */
	@Insert("INSERT INTO token_status VALUES(#{userEmail}, #{hashedToken}, #{dateExpiration}, 1)")
	int insertTokenDto(TokenDto tokenDto);

	
	/**
	 * 로그아웃 시 활성화 상태인 토큰을 모두 비활성화 처리.
	 */
	@Update("UPDATE token_status SET valid=0 WHERE user_email=#{userEmail} AND valid=1")
	void updateValidTokenToInvalidByUserEmail(String userEmail);

	
	/**
	 * 토큰의 userEmail을 바탕으로 사용자 객체 조회.
	 */
	@Select("SELECT * from user WHERE email=#{userEmail}")
	UserEntity selectByUserEmail(String userEmail);

	
	/**
	 * 새로운 토큰 발급 시, 기존의 토큰을 모두 비활성화 처리. (RefreshToken은 제외)
	 */
	@Update("UPDATE token_status SET valid=0 WHERE user_email=#{userEmail} AND valid=1 AND hashed_token!=#{hashedRefreshToken}")
	void updateValidTokenToInvalidByUserEmailAndRefreshToken(String userEmail, String hashedRefreshToken);

	
	/**
	 * 해당 토큰이 활성화 토큰인지 조회.
	 */
	@Select("SELECT COUNT(*) FROM token_status WHERE hashed_token=#{hashedToken} AND valid=1")
	boolean selectValidStatusByToken(String hashedToken);
}
