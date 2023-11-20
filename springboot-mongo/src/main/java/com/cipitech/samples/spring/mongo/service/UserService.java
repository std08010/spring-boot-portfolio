package com.cipitech.samples.spring.mongo.service;

import com.cipitech.samples.spring.mongo.domain.User;
import com.cipitech.samples.spring.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService
{
	private final UserRepository userRepository;

	public Optional<User> findUserById(String id)
	{
		return userRepository.findById(id);
	}

	public void insertUser(User user)
	{
		userRepository.save(user);
	}

	public List<User> findAllUsers()
	{
		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	public void deleteUser(String userId)
	{
		userRepository.deleteById(userId);
	}

	public void deleteUser(User user)
	{
		userRepository.delete(user);
	}

	public boolean isEmpty()
	{
		return !(userRepository.count() > 0);
	}

	public Optional<User> findByName(String name)
	{
		return userRepository.findByName(name);
	}
}
