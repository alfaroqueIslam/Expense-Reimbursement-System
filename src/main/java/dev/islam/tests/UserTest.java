package dev.islam.tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import dev.islam.models.User;
import dev.islam.services.UserService;

public class UserTest {
	
	UserService us = new UserService();
	
	@Test
	public void loginTest() {
		User u = us.findUserByUsernameAndPassword("test@gmail.com", "password");
		int expected = 7;
		int actual = u.getUserId();
		assertEquals(expected, actual, 7);
	}
	

	@Test
	public void getUserTest() {
		User u = us.findUserById(7);
		String expected = "test@gmail.com";
		String actual = u.getUserName();
		assertEquals(expected, actual, "test@gmail.com");
	}

}
