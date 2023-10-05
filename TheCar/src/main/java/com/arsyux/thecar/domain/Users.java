package com.arsyux.thecar.domain;

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
//@Entity
//@Table(name = "USERS")
public class Users {
	
	// 회원 번호 (PK)
	private int id;
	
	// 로그인 아이디
	private String username;
	
	// 비밀번호
	private String password;
	
}
