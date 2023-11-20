package com.cipitech.samples.spring.blog.jpa.repository;

import com.cipitech.samples.spring.blog.jpa.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>
{
	boolean existsPostByTitle(String title);

	Optional<Post> findBySlug(String slug);
}
