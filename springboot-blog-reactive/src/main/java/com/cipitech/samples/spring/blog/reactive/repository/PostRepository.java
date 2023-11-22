package com.cipitech.samples.spring.blog.reactive.repository;

import com.cipitech.samples.spring.blog.reactive.domain.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepository
//		extends ReactiveCrudRepository<Post, String>
		extends ReactiveCrudRepository<Post, Long>
{
	Mono<Boolean> existsPostByTitle(String title);

	Mono<Post> findBySlug(String slug);
}
