package com.cipitech.samples.spring.blog.reactive.web.rest;

import com.cipitech.samples.spring.blog.reactive.dto.CommentDTO;
import com.cipitech.samples.spring.blog.reactive.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/rest/comments")
@RequiredArgsConstructor
public class CommentRestApi
{
	private final CommentService commentService;

	@GetMapping("/post/{slug}")
	public Flux<CommentDTO> findAllCommentsByPost(@PathVariable String slug)
	{
		return commentService.findByPost(slug);
	}

	@PostMapping("/post/{slug}")
	public Mono<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable String slug)
	{
		return commentService.create(commentDTO, slug);
	}
}
