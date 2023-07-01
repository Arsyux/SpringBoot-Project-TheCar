package com.arsyux.thecar.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User {

	// PK값
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// 로그인 ID
	@Column(nullable = false, length = 50, unique = true)
	private String username;
	
	// 로그인 Password
	@Column(nullable = false, length = 100)
	private String password;

	// 사용자 이름
	@Column(nullable = false, length = 50)
	private String realname;

	// 생년월일
	@Column(nullable = false, length = 8)
	private String birthDate;
	
	// 사용자 성별
	@Column(nullable = false, length = 5)
	private String gender;
	
	// 휴대폰 번호
	@Column(nullable = false, length = 11, unique = true)
	private String phone;
	
	// 포인트
	@Column(nullable = false)
	private int point;
	
	// 등급
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	// 인증
	@Enumerated(EnumType.STRING)
	private OAuthType oauth;

	// 가입 날짜
	@CreationTimestamp
	private Timestamp createDate;
	
}