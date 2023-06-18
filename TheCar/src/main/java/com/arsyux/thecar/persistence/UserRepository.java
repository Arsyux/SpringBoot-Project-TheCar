package com.arsyux.thecar.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	// JpaRepository<T, ID>에서 T는 엔티티 클래스 타입이고, ID는 엔티티 클래스에 설정한 식별자의 타입이다.
	// @Repository는 생략할 수 있다.
	
	// 아이디 중복 확인
	// JPA를 이용하여 목록 기능을 구현할 때는 일반적으로 자바 퍼시스턴트 쿼리 언어(JPQL)
	// 를 사용한다. JPQL은 SQL과 비슷하지만 JPA 전용의 쿼리 언어로서 검색 대상이
	// 테이블이 아닌 엔티티 객체라는 점에서 일반적인 SQL과 다르다.
	Optional<User> findByUsername(String username);

}