package com.arsyux.thecar.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	// 회원 번호 (PK)
	private int id;
	
	// 로그인 아이디
	private String username;
	
	// 비밀번호
	private String password;
	
	// 이름
	private String name;
	
	// 생년월일
	private String birth;

	// 성별
	private String gender; 
	
	// 휴대폰 번호
	private String phone;

	// 이메일
	private String email;
	
	// 마일리지
	private int point;
	
	// 가입일
	private Date regdate;
}
