package com.arsyux.thecar.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	// 회원 번호 (PK)
	private int id;
	
	// 로그인 아이디
	private String username;
	
	// 비밀번호
	private String password;
	
}
