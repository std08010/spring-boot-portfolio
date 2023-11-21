package com.cipitech.samples.spring.blog.reactive.service;

import com.cipitech.samples.spring.blog.reactive.domain.Comment;
import com.cipitech.samples.spring.blog.reactive.domain.Post;
import com.cipitech.samples.spring.blog.reactive.dto.CommentDTO;
import com.cipitech.samples.spring.blog.reactive.mapper.CommentMapper;
import com.cipitech.samples.spring.blog.reactive.repository.CommentRepository;
import com.cipitech.samples.spring.blog.reactive.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService
{
	private final PostRepository    postRepository;
	private final CommentRepository commentRepository;
	private final CommentMapper     commentMapper;

	public Flux<CommentDTO> findByPost(String slug)
	{
		return getPostBySlug(slug).flatMapMany(post -> Flux.fromStream(post.getComments().stream().map(commentMapper::toCommentDTO)));
	}

	public Mono<CommentDTO> create(CommentDTO commentDTO, String slug)
	{
		return getPostBySlug(slug).flatMap(postBySlug -> {
			Comment comment = commentMapper.toComment(commentDTO);
			comment.setCreatedOn(LocalDateTime.now());
			comment.setUpdatedOn(LocalDateTime.now());
			comment.setPost(postBySlug);
			return commentRepository.save(comment).map(savedComment -> {
				postBySlug.getComments().add(savedComment);
				postRepository.save(postBySlug);

				return commentMapper.toCommentDTO(savedComment);
			});
		});
	}

	private Mono<Post> getPostBySlug(String slug)
	{
		return postRepository.findBySlug(slug);
	}
}
