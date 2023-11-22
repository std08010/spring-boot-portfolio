package com.cipitech.samples.spring.blog.reactive.repository;

import com.cipitech.samples.spring.blog.reactive.domain.Comment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Mono;

@CrossOrigin
@Repository
public interface CommentRepository
//		extends ReactiveCrudRepository<Comment, String>
		extends ReactiveCrudRepository<Comment, Long>
{
	Mono<Boolean> existsCommentByTitle(String title);
}
