package com.cipitech.samples.spring.blog.repository;

import com.cipitech.samples.spring.blog.domain.Post;
import com.cipitech.samples.spring.blog.exception.SpringBlogException;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Repository
public class PostRepositoryInMemory
{
	private final Set<Post> posts = new CopyOnWriteArraySet<>();

	public void addPost(Post post)
	{
		posts.add(post);
	}

	public Set<Post> findAllPosts()
	{
		return posts;
	}

	public boolean isEmpty()
	{
		return posts.isEmpty();
	}

	public Post findPost(Integer postId)
	{
		return posts.stream().filter(post -> post.getId().equals(postId)).findFirst().orElseThrow(() -> new SpringBlogException("Cannot find post by id: " + postId));
	}
}
