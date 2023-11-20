package com.cipitech.samples.spring.blog.jpa.web.rest;

import com.cipitech.samples.spring.blog.jpa.dto.PostDTO;
import com.cipitech.samples.spring.blog.jpa.exception.rest.ErrorDetails;
import com.cipitech.samples.spring.blog.jpa.exception.rest.PostDeletionException;
import com.cipitech.samples.spring.blog.jpa.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public List<PostDTO> listPosts()
	{
		return postService.findAllPosts();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PostDTO createPost(@RequestBody PostDTO post)
	{
		return postService.addPost(post);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public PostDTO updatePost(@RequestBody PostDTO post)
	{
		return postService.updatePost(post);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //The common practice on delete is to return 204
	public void deletePost(@PathVariable Integer id)
	{
		try
		{
			postService.deletePost(id);
		}
		catch (Exception e)
		{
			throw new PostDeletionException("Post with id=" + id + " can't be deleted. " + e.getMessage());
		}
	}

	@GetMapping("/{slug}")
	@ResponseStatus(HttpStatus.OK)
	public PostDTO findPostBySlug(@PathVariable String slug)
	{
		return postService.findBySlug(slug);
	}

	@ExceptionHandler(PostDeletionException.class)
	public ResponseEntity<?> handlePostDeletionException(PostDeletionException e)
	{
		return new ResponseEntity<>(new ErrorDetails(e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
