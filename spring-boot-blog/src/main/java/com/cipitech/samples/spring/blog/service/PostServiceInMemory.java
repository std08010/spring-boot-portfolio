package com.cipitech.samples.spring.blog.service;

import com.cipitech.samples.spring.blog.domain.Post;
import com.cipitech.samples.spring.blog.exception.SpringBlogException;
import com.cipitech.samples.spring.blog.repository.PostRepositoryInMemory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PostServiceInMemory
{
	private final PostRepositoryInMemory postRepository;

	public void addPost(Post post)
	{
		postRepository.addPost(post);
	}

	public Set<Post> findAllPosts()
	{
		return postRepository.findAllPosts();
	}

	public boolean isEmpty()
	{
		return postRepository.isEmpty();
	}

	public boolean postExistsWithTitle(String title)
	{
		return postRepository.findAllPosts().stream().anyMatch(post -> post.getTitle().equals(title));
	}

	public Post findPost(Integer postId)
	{
		return postRepository.findPost(postId);
	}
}
