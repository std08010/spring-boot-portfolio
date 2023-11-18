package com.cipitech.samples.spring.blog.service;

import com.cipitech.samples.spring.blog.repository.PostRepository;
import com.cipitech.samples.spring.blog.repository.PostRepositoryJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"jdbc-plain", "jdbc-boot"})
@Service
@RequiredArgsConstructor
public class PostServiceJdbc extends PostService
{
	private final PostRepositoryJdbc postRepository;

	@Override
	public PostRepository getRepository()
	{
		return postRepository;
	}
}
