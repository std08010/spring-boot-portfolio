package com.cipitech.samples.spring.blog.jdbc.service;

import com.cipitech.samples.spring.blog.jdbc.repository.PostRepository;
import com.cipitech.samples.spring.blog.jdbc.repository.PostRepositoryInMemory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("in-memory")
@Service
@RequiredArgsConstructor
public class PostServiceInMemory extends PostService
{
	private final PostRepositoryInMemory postRepository;

	@Override
	public PostRepository getRepository()
	{
		return postRepository;
	}
}
