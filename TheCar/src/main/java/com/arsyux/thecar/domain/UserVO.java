package com.arsyux.thecar.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
	
	private int userid; // 회원 번호 (PK)
	private String username; // 로그인 아이디
	private String password; // 비밀번호
	private String name; // 이름
	private String birth; // 생년월일
	private String gender; // 성별
	private String phone; // 휴대폰 번호
	private String email; // 이메일
	private int point; // 마일리지
	private String role; // 유저 역할(일반, 관리자)
	private Date regdate; // 가입일
	
}
