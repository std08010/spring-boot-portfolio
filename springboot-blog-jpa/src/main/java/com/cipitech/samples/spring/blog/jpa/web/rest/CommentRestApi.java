package com.cipitech.samples.spring.blog.jpa.web.rest;

import com.cipitech.samples.spring.blog.jpa.dto.CommentDTO;
import com.cipitech.samples.spring.blog.jpa.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/comments")
@RequiredArgsConstructor
public class CommentRestApi
{
	private final CommentService commentService;

	@GetMapping("/post/{slug}")
	public List<CommentDTO> findAllCommentsByPost(@PathVariable String slug)
	{
		return commentService.findByPost(slug);
	}

	@PostMapping("/post/{slug}")
	public void createComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable String slug)
	{
		commentService.create(commentDTO, slug);
	}
}
