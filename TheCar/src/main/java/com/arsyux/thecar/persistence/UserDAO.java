package com.arsyux.thecar.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.thecar.domain.User;

@Repository
public class UserDAO {

	// DAO (Data Access Object)는 데이터베이스의 Data에 접근하기 위한 객체임
	// Database에 접근하기 위한 로직 & 비지니스 로직을 분리하기위해 사용함

	// 스프링과 마이바티스를 연동하기 위해서는 SqlSessionFactoryBean 객체와 SqlSessionTemplate 객체가 필요하다.
	// 따라서 그에따른 환경 설정 클래스가 필요한데, 스프링 부트는 이 두 객체에 대한 자동설정 클래스를 기본적으로 포함한다.
	// DAO 클래스에서 Mybatis를 이용하기 위해서는 @Autowired 어노테이션으로 SqlSessionTemplate객체를 주입해야함.
	// SqlSessionTemplate 객체에 대한 의존성이 처리되면 해당 객체가 제공하는 메소드를 이용하여 기능을 처리할 수 있음.
	@Autowired
	private SqlSessionTemplate mybatis;

	// 실행할 SQL은 UpserMapper에 등록한 메소드명으로 식별함.
	public void insertUser(User user) {
		mybatis.insert("insertUser", user);
	}

	public void updateUser(User user) {
		mybatis.update("updateUser", user);
	}

	public void deleteUser(User user) {
		mybatis.delete("deleteUser", user);
	}

	public User getUser(User user) {
		return mybatis.selectOne("getUser", user);
	}

	public List<User> getUserList() {
		return mybatis.selectList("getUserList");
	}

}
