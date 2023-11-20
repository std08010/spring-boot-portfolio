package com.cipitech.samples.spring.mongo;

import com.cipitech.samples.spring.mongo.domain.User;
import com.cipitech.samples.spring.mongo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@Import(UserService.class)
public class MongoApplicationTests
{
	@Autowired
	private UserService userService;

	@Test
	void findAllUsers()
	{
		List<User> users = userService.findAllUsers();
		assertNotNull(users);
		assertFalse(users.isEmpty());
	}

	@Test
	void findUserByName()
	{
		User user = userService.findByName("george").orElseThrow(() -> new IllegalArgumentException("Cannot find any user with name george"));
		assertNotNull(user);
	}

	@Test
	void createUser()
	{
		User user = new User("george", "george@gmail.com");
		userService.insertUser(user);
		assertTrue(userService.findAllUsers().stream().anyMatch(savedUser -> savedUser.getName().equals(user.getName())));
	}
}
