package com.cipitech.samples.spring.blog.service;

import com.cipitech.samples.spring.blog.domain.Post;
import com.cipitech.samples.spring.blog.repository.PostRepository;
import com.cipitech.samples.spring.blog.repository.PostRepositoryInMemory;
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

	public void updatePost(Post post)
	{
		postRepository.updatePost(post);
	}

	public void deletePost(Integer id)
	{
		postRepository.deletePost(id);
	}
}
