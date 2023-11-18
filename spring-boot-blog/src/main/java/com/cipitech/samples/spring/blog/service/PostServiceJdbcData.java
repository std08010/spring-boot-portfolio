package com.cipitech.samples.spring.blog.service;

import com.cipitech.samples.spring.blog.repository.PostRepository;
import com.cipitech.samples.spring.blog.repository.PostRepositoryJdbcData;
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
