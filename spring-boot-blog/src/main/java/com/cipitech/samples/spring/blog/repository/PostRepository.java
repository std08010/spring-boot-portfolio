package com.cipitech.samples.spring.blog.repository;

import com.cipitech.samples.spring.blog.domain.Post;

import java.util.Set;

public interface PostRepository
{
	void addPost(Post post);
	Set<Post> findAllPosts();
	Post findById(Integer id);
	boolean isEmpty();
	boolean postExistsWithTitle(String title);
}
