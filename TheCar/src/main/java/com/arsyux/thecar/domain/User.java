package com.arsyux.thecar.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// REST 컨트롤러의 메소드는 값 객체(Value Object)나 도메인 클래스를 매개변수와 반환값으로 사용할 수 있다.
// User클래스에 적용된 lombok 어노테이션은 타음과 같다.
// @Data - @Getter, @Setter, @ToString, @EqualsAndHashCode, @AllArgsConstructor 포함
// @NoArgsConstructor - 기본 생성자 생성
// @AllArgsConstructor - 모든 멤버 변수를 초기화하는 생성자 생성
// @Builder - 빌더 패턴이 적용된 builder() 메소드 생성
// @Entity - 특정 클래스를 엔티티로 설정할 때 사용한다. JPA는 엔티티 클래스로부터 생성된 객체를 기반으로 데이터베이스 연동을 처리한다.
// @Table - 엔티티 클래스와 테이블을 매핑할 때 사용하며, @Table을 생략하면 엔티티 클래스와 동일한 이름의 테이블이 자동으로 매핑된다.
// 회원 테이블 이름이 USER가 아닌 USERS이므로 @Table을 통해 매핑해준다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User {
	
	// @Id - 식별자 변수를 지정할 때 사용하며, 테이블의 기본키(Primary Key, PK)에 대응하는 변수를 의미한다.
	// 식별자 변수가 지정되지 않은 엔티티 클래스를 사용하면 JPA는 에러를 발생시킨다.
	// @GeneratedValue - 식별자 변수에 자동으로 증가된 값을 할당한다.
	// YAML 파일에 설정한 Dialect 클래스에 따라 식별자 값 전략이 자동으로 결정된다.
	// 예를 들어, OracleDialect는 시퀀스(sequence) 전략이 적용되고 H2Dialect는 아이덴티티(identity)전략이 적용된다.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 회원 번호
	
	// 로그인 아이디
	@Column(nullable = false, length = 50, unique = true)
	private String username;
	
	// 비밀번호
	@Column(length = 100)
	private String password;
	
	// 이메일
	@Column(nullable = false, length = 100)
	private String email;
	
	// 회원 분류
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	// 생성 날짜
	@CreationTimestamp
	private Timestamp createDate;
}
