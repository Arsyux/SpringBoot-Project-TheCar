package com.arsyux.thecar;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
