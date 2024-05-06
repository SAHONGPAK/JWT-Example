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

	@Select("SELECT * FROM user WHERE email=#{userEmail} AND password=#{userPassword}")
	UserEntity selectByRequestLoginDto(RequestLoginDto requestLoginDto);

	@Insert("INSERT INTO token_status VALUES(#{userEmail}, #{hashedToken}, #{dateExpiration}, 1)")
	int insertTokenDto(TokenDto tokenDto);

	@Update("UPDATE token_status SET valid=0 WHERE user_email=#{userEmail} AND valid=1")
	void updateValidTokenToInvalidByUserEmail(String userEmail);

	@Select("SELECT * from user WHERE email=#{userEmail}")
	UserEntity selectByUserEmail(String userEmail);

	@Update("UPDATE token_status SET valid=0 WHERE user_email=#{userEmail} AND valid=1 AND hashed_token!=#{hashedRefreshToken}")
	void updateValidTokenToInvalidByUserEmailAndRefreshToken(String userEmail, String hashedRefreshToken);

	
	@Select("SELECT COUNT(*) FROM token_status WHERE hashed_token=#{hashedToken} AND valid=1")
	boolean selectValidStatusByToken(String hashedToken);
}
