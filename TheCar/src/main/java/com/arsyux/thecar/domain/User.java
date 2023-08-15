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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	
	private int id;
	private String username;
	private String password;
	private String email;
	
}
