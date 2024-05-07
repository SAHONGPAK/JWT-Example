package com.example.jwt.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.jwt.dto.RequestSignUpDto;
import com.example.jwt.entity.UserEntity;

@Mapper
public interface UserMapper {

	/**
	 * 마이 페이지 요청 시 전달하는 유저 객체.
	 */
	@Select("SELECT * FROM user WHERE email=#{userEmail}")
	UserEntity selectByUserEmail(String userEmail);

	
	/**
	 * 동일한 이메일이 존재하는지 확인.
	 */
	@Select("SELECT COUNT(*) FROM user WHERE email=#{userEmail}")
	int selectCountByUserEmail(String userEmail);

	
	/**
	 * 회원가입.
	 */
	@Insert("INSERT INTO user VALUE (#{userEmail}, #{userPassword}, #{userName}, \"일반\")")
	void insertRequestSignUpDto(RequestSignUpDto requestSignUpDto);

}
