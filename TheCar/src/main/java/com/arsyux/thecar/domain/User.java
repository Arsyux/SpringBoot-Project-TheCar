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

// @Data - @Getter, @Setter, @ToString, @EqualAndHashCode, @AllArgsConstructor 포함
// @NoArgs Constructor - 기본 생성자 생성
// @AllArgsConstructor - 모든 멤버 변수 초기화하는 생성자 생성
// @Builder - 빌더 패턴이 적용된 builder() 메소드 생성
// @Entity
// 특정 클래스를 엔티티로 설정할 때 사용함.
// JPA는 엔티티 클래스로부터 생성된 객체를 기반으로 데이터베이스 연동을 처리함
// @Table
// 엔티티 클래스와 테이블을 매핑할 때 사용하며, @Table을 생략하면 엔티티 클래스와 동일한 이름의 테이블이 자동으로 매핑된다.
// 회원 테이블 이름이 USER가 아닌 USERS이므로 이를 매핑하였음.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 50, unique = true)
	private String username;

	@Column(length = 100)
	private String password;

	@Column(nullable = false, length = 100)
	private String email;

	@Enumerated(EnumType.STRING)
	private RoleType role;

	@CreationTimestamp
	private Timestamp createDate;
	
	@Enumerated(EnumType.STRING)
	private OAuthType oauth;
	
}