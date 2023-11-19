package com.cipitech.samples.spring.blog.jpa.repository;

import com.cipitech.samples.spring.blog.jpa.domain.Post;
import com.cipitech.samples.spring.blog.jpa.exception.SpringBlogException;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;


@Repository
@Profile("jpa-data") // Since this is scanned by EnableJpaRepositories it can have a profile.
public interface PostRepositoryJpaData extends JpaRepository<Post, Integer>, PostRepository
{
	@Override
	default void addPost(Post post)
	{
		save(post);
	}

	@Override
	default Set<Post> findAllPosts()
	{
		return new HashSet<>(findAll(Sort.by(Sort.Direction.ASC, "title")));
	}

	@Override
	default Post findByPostID(Integer id)
	{
		return findById(id).orElseThrow(() -> new SpringBlogException("Cannot find post by id: " + id));
	}

	@Override
	default boolean isEmpty()
	{
		return !(count() > 0);
	}

	boolean existsPostByTitle(String title);

	@Override
	default void updatePost(Post post)
	{
		save(post);
	}

	@Override
	default void deletePost(Integer id)
	{
		deleteById(id);
	}
}
