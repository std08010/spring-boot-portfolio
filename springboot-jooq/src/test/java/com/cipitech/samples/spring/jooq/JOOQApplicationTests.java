package com.cipitech.samples.spring.jooq;

import com.cipitech.samples.spring.jooq.domain.User;
import com.cipitech.samples.spring.jooq.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JooqTest
@Import(UserRepository.class)
public class JOOQApplicationTests
{
	@Autowired
	private UserRepository userRepository;

	@Test
	void findAllUsers()
	{
		List<User> users = userRepository.findAllUsers();
		assertNotNull(users);
		assertFalse(users.isEmpty());
	}

	@Test
	void findUserById()
	{
		User user = userRepository.findUserById(1).orElseThrow(() -> new IllegalArgumentException("Cannot find any user with id 1"));
		assertNotNull(user);
	}

	@Test
	void createUser()
	{
		User user = new User("george", "george@gmail.com");
		userRepository.insertUser(user);
		assertTrue(userRepository.findAllUsers().stream().anyMatch(savedUser -> savedUser.getName().equals(user.getName())));
	}
}
