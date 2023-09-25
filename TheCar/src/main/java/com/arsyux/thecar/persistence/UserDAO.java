package com.arsyux.thecar.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.User;

// 마이바티스를 적용하기 위한 절차
// 마이바티스 라이브러리 추가
// 테이블 생성
// 데이터 소스 설정
// 도메인 클래스 작성
// 매퍼(Mapper) 작성
// 데이터 접근 객체(Data Access Object, DAO) 작성
// DAO에 대한 테스트케이스 작성
//
// 비즈니스 컴포넌트의 실질적인 데이터베이스 연동은 DAO 클래스에서 담당한다.
@Repository
public class UserDAO {

	// 스프링과 마이바티스를 연동하기 위해서는 SqlSessionFactoryBean 객체와 SqlSessionTemplate 객체가 필요하다.
	// 따라서 SqlSessionFactoryBean 객체와 SqlSessionTemplate 객체를 생성하는 환경 설정 클래스를 작성해야하는데,
	// 스프링 부트는 이 두 객체에 대한 자동설정 클래스를 기본적으로 포함한다.
	// 즉, 프로젝트가 실행되는 순간 해당 자동설정 클래스가 동작하여 SqlSessionFactoryBean 객체와 SqlSessionTemplate 객체를 자동으로 생성한다.
	
	// DAO 클래스에서 마이바티스를 이용하기 위해서는 @Autowired 어노테이션으로 SqlSessionTemplate 객체를 주입해야한다.
	// SqlSessionTemplate에 대한 의존성이 처리되면 SqlSessionTemplate 객체가 제공하는 insert(), update(), delete() 메소드를 이용하여
	// 등록, 수정, 삭제 기능을 처리할 수 있고, selectOne() 메소드와 selectList() 메소드를 사용하여 상세 조회 목록 조회도 구현할 수 있다.
	// 이때 실행한 SQL은 UserMapper에 등록한 메소드명으로 식별한다.
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public User getUser(User user) {
		return mybatis.selectOne("getUser", user);
	}
	
	public List<User> getUserList(){
		return mybatis.selectList("getUserList");
	}
	
	public void insertUser(User user) {
		mybatis.insert("insertUser", user);
	}
	
	public void updateUser(User user) {
		mybatis.update("updateUser", user);
	}
	
	public void deleteUser(User user) {
		mybatis.delete("deleteUser", user);
	}
	
}
