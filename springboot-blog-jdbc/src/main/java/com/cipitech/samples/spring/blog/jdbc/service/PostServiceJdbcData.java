package com.cipitech.samples.spring.blog.jdbc.service;

import com.cipitech.samples.spring.blog.jdbc.repository.PostRepositoryJdbcData;
import com.cipitech.samples.spring.blog.jdbc.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("jdbc-data")
@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceJdbcData extends PostService
{
	private final PostRepositoryJdbcData postRepository;

	@Override
	public PostRepository getRepository()
	{
		return postRepository;
	}
}
