package com.arsyux.thecar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arsyux.thecar.domain.User;
import com.arsyux.thecar.persistence.UserDAO;

@SpringBootTest
public class UserDAOTest {

	@Autowired
	private UserDAO userDAO;

	// insertUser 테스트
	// insertUser() 메소드와 getUserList() 메소드를 이용하여
	// 회원 등록 전/후의 데이터 수를 비교한다.
	@Test
	void getUserListTest() {
		User user = new User();
		user.setUsername("test");
		user.setPassword("test123");

		int before = userDAO.getUserList().size();
		userDAO.insertUser(user);
		int after = userDAO.getUserList().size();

		assertEquals(before + 1, after);
	}

	// updateUser 테스트
	// 첫번째 User의 비밀번호를 수정한 후 변경되었는지 테스트
	@Test
	void updateUserTest() {
		User before = userDAO.getUser(new User(1, "test", "123"));
		before.setPassword("test111");

		userDAO.updateUser(before);
		User after = userDAO.getUser(before);

		assertEquals(before, after);
	}

	// deleteUser 테스트
	@Test
	void deleteUserTest() {
		List<User> userList = userDAO.getUserList();

		int before = userList.size();
		userDAO.deleteUser(userList.get(userList.size() - 1));
		int after = userDAO.getUserList().size();

		assertEquals(before - 1, after);
	}

}
