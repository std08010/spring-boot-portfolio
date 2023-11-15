package com.cipitech.samples.spring.blog.service;

import com.cipitech.samples.spring.blog.domain.Post;
import com.cipitech.samples.spring.blog.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.Set;

public abstract class PostService
{
	public abstract PostRepository getRepository();

	public Post findById(Integer postId)
	{
		return getRepository().findById(postId);
	}

	public boolean postExistsWithTitle(String title)
	{
		return getRepository().postExistsWithTitle(title);
	}

	public boolean isEmpty()
	{
		return getRepository().isEmpty();
	}

	public Set<Post> findAllPosts()
	{
		return getRepository().findAllPosts();
	}

	public void addPost(Post post)
	{
		post.setCreatedOn(LocalDateTime.now());
		post.setUpdatedOn(LocalDateTime.now());
		getRepository().addPost(post);
	}
}
