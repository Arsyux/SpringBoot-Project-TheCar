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

	// Warning 해결: pom.xml의 Dependencies에서 Lombok 설정을 삭제했다가 셋업 후 다시 설정하였음.

	// @Id
	// 기본키에 대응하는 식별자 변수를 의미함
	// @GeneratedValue
	// 식별자 변수에 자동으로 증가된 값을 할당한다.
	// YAML 파일에 설정한 Dialect 클래스에 따라 식별자 값 전략이 자동으로 결정됨.
	// 예를 들어, OracleDialect는 시퀀스(Sequence) 전략이 적용되고, H2Dialect는 아이덴티티(identity) 전략이 적용됨.
	// 1부터 시작하여 자동으로 1씩 증가하도록 증가 전략 설정
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 회원 일련번호

	@Column(nullable = false, length = 50, unique = true)
	private String username; // 로그인 아이디

	@Column(length = 100)
	private String password; // 비밀 번호

	@Column(nullable = false, length = 100)
	private String email; // 이메일

	@Enumerated(EnumType.STRING)
	private RoleType role;

	@CreationTimestamp
	private Timestamp createDate;// 현재 시간이 기본값으로 등록되도록 설정
	
	// EnumType.STRING으로 설정하면 enum타입의 데이터를 문자열로 저장한다.
	// 즉, OauthType의 oauth 변수의 값이 OAuthType.KAKAO로 지정되면 KAKAO라는 문자열을 할당하는 것.
	// 반면, EnumType.ORDINAL로 설정하면 enum에 등록된 순서에 해당하는 숫자를 할당한다.
	@Enumerated(EnumType.STRING)
	private OAuthType oauth;
	
}