package com.cipitech.samples.spring.mybatis;

import com.cipitech.samples.spring.mybatis.domain.User;
import com.cipitech.samples.spring.mybatis.mappers.UserAnnotationMapper;
import com.cipitech.samples.spring.mybatis.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest//(classes=MyBatisApplication.class)
public class MyBatisApplicationTests
{
	@Autowired
	private UserMapper           userMapper;
	@Autowired
	private UserAnnotationMapper userAnnotationMapper;

	@Test
	public void findAllUsers()
	{
		List<User> users = userMapper.findAllUsers();
		assertNotNull(users);
		assertFalse(users.isEmpty());
	}

	@Test
	public void findUserById()
	{
		User user = userMapper.findUserById(1);
		assertNotNull(user);
	}

	@Test
//	@Disabled
	public void createUser()
	{
		User user = new User("george", "george@gmail.com");
		userMapper.insertUser(user);
		User newUser = userMapper.findUserById(user.getId());
		assertEquals("george", newUser.getName());
		assertEquals("george@gmail.com", newUser.getEmail());
	}
}
