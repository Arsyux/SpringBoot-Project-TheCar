package com.arsyux.thecar.persistence;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.User;


// 리포지터리는 DAO 클래스와 동일한 개념으로, 마이바티스처럼 개발자가 인터페이스만 정의하면 JPA가 자동으로 구현 클래스를 만들어 준다.
// 스프링 부트가 제공하는 리포지터리의 상속구조는 다음과 같다.
// JpaRepository -> PagingAndSortingRepository -> CrudRepository -> Repository
// 일반적으로 CrudRepository나 JpaRepository를 상속한다.
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	// JpaRepository의 제네릭 타입중 첫 번째는 엔티티 클래스 타입이고, 두 번째는 엔티티 클래스에 설정한 식별자의 타입이다.
	// 인터페이스 선언부에 추가한 @Repository 어노테이션은 생략할 수 있다.
	
	// JpaRepository 인터페이스가 제공하는 메소드 중에 자주 사용되는 것들
	// long count()                  - 모든 엔티티 개수 반환
	// void delete(Object key)       - 식별키에 해당하는 엔티티 삭제
	// void deleteAll()              - 모든 엔티티 삭제
	// boolean exists(Object key)    - 식별키에 해당하는 엔티티 존재 여부 확인
	// List<T> findAll()             - 모든 엔티티 목록 반환
	// Optional findById(Object key) - 식별키에 해당하는 단인 엔티티 반환
	// save(Entity)                  - 엔티티를 등록 또는 수정
	
	// findById() 메소드는 특정 키에 해당하는 엔티티 하나를 검색하는 메소드로서 반환 타입이 Object가 아닌
	// java.Util.Optional이다. 따라서 특정 타입의 객체를 반환할 때는 Optional의 get() 메소드를 사용해야 한다.
	// save() 메소드도 중요한데, 이는 save() 메소드 하나로 엔티티 등록과 수정작업을 모두 처리하기 때문이다.
	// save() 메소드는 인자로 전달된 엔티티에 식별자 값이 설정되어 있으면 수정 기능으로, 반대인 경우에는 등록 기능으로 동작한다.
	
	// JPA를 이용하여 목록 기능을 구현할 때는 일반적으로 자바 퍼시스턴트 쿼리 언어(Java Persistence Query Language, JPQL)를 사용한다.
	// JPQL은 SQL과 비슷하지만 JPA 전용의 쿼리 언어로서 검색 대상이 테이블이 아닌 엔티티 객체라는 점에서 일반적인 SQL과 다르다.
	// 다음은 특정 회원의 아이디로 상세 조회를 처리하는 쿼리를 일반적인 SQL과 JPQL로 나타낸 것이다.
	// SQL
	// SELECT ID, USERNAME, PASSWORD, EMAIL FROM USER WHERE USERNAME = ? ORDER BY ID DESC;
	// JPQL
	// SELECT u.ID, u.USERNAME, u.PASSWORD, u.EMAIL FROM USER u WHERE u.USERNAME = ? ORDER BY u.ID DESC;
	// JPQL의 문법과 구조는 기존의 SQL과 비슷하다.
	// 스프링에서는 이러한 JPQL을 좀 더 쉽게 사용할 수 있도록, 메소드명을 기반으로 JQPL을 생성하는 쿼리 메소드를 제공한다.
	// 쿼리메소드: find + 엔티티명 + By + 변수명
	// 예를 들어 findUserByUsername(String searchKeyword)는 User엔티티로 생성된 객체 중에서 username 변수의 값이
	// searchKeyword와 동일한 객체만 조회한다.
	// 이때 엔티티명 부분은 생략할 수 있다. 따라서 findUserByUsername() 메소드와 findByUsername() 메소드는 같은
	// JPQL을 생성한다. 이 경우 선언된 제네릭 타입이 자동으로 리포지터리 인터페이스에 적용된다.
	// SELECT * FROM USER WHERE USERNAME = ?;
	Optional<User> findByUsername(String username);
	
}
