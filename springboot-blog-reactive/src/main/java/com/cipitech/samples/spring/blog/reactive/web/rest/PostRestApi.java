package com.cipitech.samples.spring.blog.reactive.web.rest;

import com.cipitech.samples.spring.blog.reactive.dto.PostDTO;
import com.cipitech.samples.spring.blog.reactive.exception.rest.ErrorDetails;
import com.cipitech.samples.spring.blog.reactive.exception.rest.PostDeletionException;
import com.cipitech.samples.spring.blog.reactive.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/rest/posts")
@RequiredArgsConstructor
public class PostRestApi
{
	private final PostService postService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<PostDTO> listPosts()
	{
		return postService.findAllPosts();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<PostDTO> createPost(@RequestBody @Valid PostDTO post)
	{
		return postService.addPost(post);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Mono<PostDTO> updatePost(@RequestBody @Valid PostDTO post)
	{
		return postService.updatePost(post);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //The common practice on delete is to return 204
	public Mono<Void> deletePost(@PathVariable Long id)
	{
		try
		{
			return postService.deletePost(id);
		}
		catch (Exception e)
		{
			throw new PostDeletionException("Post with id=" + id + " can't be deleted. " + e.getMessage());
		}
	}

	@GetMapping("/{slug}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<PostDTO> findPostBySlug(@PathVariable String slug)
	{
		return postService.findBySlug(slug);
	}

	@ExceptionHandler(PostDeletionException.class)
	public ResponseEntity<?> handlePostDeletionException(PostDeletionException e)
	{
		return new ResponseEntity<>(new ErrorDetails(e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
