package com.cipitech.samples.spring.mybatis.mappers;

import com.cipitech.samples.spring.mybatis.domain.User;

import java.util.List;

public interface UserMapper
{
	void insertUser(User user);

	User findUserById(Integer id);

	List<User> findAllUsers();
}
