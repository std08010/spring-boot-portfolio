package com.cipitech.samples.spring.blog.repository;

import com.cipitech.samples.spring.blog.domain.Post;
import com.cipitech.samples.spring.blog.exception.SpringBlogException;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.*;

/**
 * Interfaces can extend multiple Interfaces.
 * Interfaces can implement functions and override then by using the "default" keyword
 */

@Profile("jdbc-data") // Since this is scanned by EnableJpaRepositories it can have a profile.
public interface PostRepositoryJdbcData extends CrudRepository<Post, Integer>, PostRepository
{
	@Override
	default void addPost(Post post)
	{
		save(post);
	}

	@Override
	default Set<Post> findAllPosts()
	{
		Set<Post> target = new HashSet<>();
		findAll().forEach(target::add);
		return target;
	}

	@Override
	default Post findByPostID(Integer id)
	{
		return findById(id).orElseThrow(() -> new SpringBlogException("Cannot find post by id: " + id));
	}

	@Override
	default boolean isEmpty()
	{
		long count = count();
		return !(count > 0);
	}

	@Query("select * from posts where title= :title")
	Optional<Post> findByTitle(@Param("title") String title);

	@Override
	default boolean postExistsWithTitle(String title)
	{
		return findByTitle(title).isPresent();
	}

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
