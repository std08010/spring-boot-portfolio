package com.cipitech.samples.spring.blog.service;

import com.cipitech.samples.spring.blog.repository.PostRepository;
import com.cipitech.samples.spring.blog.repository.PostRepositoryJdbcPlain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("jdbc-plain")
@Service
@RequiredArgsConstructor
public class PostServiceJdbcPlain extends PostService
{
	private final PostRepositoryJdbcPlain postRepository;

	@Override
	public PostRepository getRepository()
	{
		return postRepository;
	}
}
