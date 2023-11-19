package com.cipitech.samples.spring.blog.jpa.repository;

import com.cipitech.samples.spring.blog.jpa.domain.Post;

import java.util.Set;

public interface PostRepository
{
	void addPost(Post post);

	Set<Post> findAllPosts();

	Post findByPostID(Integer id);

	boolean isEmpty();

	boolean existsPostByTitle(String title);

	void updatePost(Post post);

	void deletePost(Integer id);
}
