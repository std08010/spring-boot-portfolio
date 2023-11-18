package com.cipitech.samples.spring.jooq.mappers;

import com.cipitech.samples.spring.jooq.domain.User;

import java.util.List;

public interface UserMapper
{
	void insertUser(User user);

	User findUserById(Integer id);

	List<User> findAllUsers();
}
