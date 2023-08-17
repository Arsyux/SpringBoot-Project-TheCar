package com.arsyux.thecar.persistence;


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
	
}
