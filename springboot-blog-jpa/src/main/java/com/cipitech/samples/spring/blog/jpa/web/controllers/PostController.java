package com.cipitech.samples.spring.blog.jpa.web.controllers;

import com.cipitech.samples.spring.blog.jpa.domain.PostStatus;
import com.cipitech.samples.spring.blog.jpa.dto.PostDTO;
import com.cipitech.samples.spring.blog.jpa.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController
{
	private final PostService postService;

	@GetMapping
	public String postPage(Model model)
	{
		if (postService.isEmpty())
		{
			PostDTO post = new PostDTO();
			post.setTitle("Hello Spring Boot");
			post.setDescription("Spring Boot");
			post.setBody("Spring Boot is Awesome");
			post.setPostStatus(PostStatus.DRAFT);
			postService.addPost(post);

			post = new PostDTO();
			post.setTitle("Hello Spring Boot 3");
			post.setDescription("Spring Boot 3");
			post.setBody("Spring Boot 3 is Awesome");
			post.setPostStatus(PostStatus.PUBLISHED);
			postService.addPost(post);
		}

		model.addAttribute("posts", postService.findAllPosts());

		return "post";
	}

	@GetMapping("/add")
	public String addPostPage(Model model)
	{
		model.addAttribute("post", new PostDTO());
		return "addPost";
	}

	@PostMapping
	public String addPost(@ModelAttribute("post") @Valid PostDTO post, Errors errors)
	{
		if (errors.hasErrors())
		{
			return "addPost";
		}

		postService.addPost(post);

		//We have to redirect as we don't want to go to the ViewResolver
		return "redirect:/posts";
	}

	@GetMapping("/{id}")
	public String onePostPage(Model model, @PathVariable Integer id)
	{
		model.addAttribute("post", postService.findByPostID(id));
		return "onePost";
	}

	@GetMapping("/update/{id}")
	public String updatePost(Model model, @PathVariable Integer id)
	{
		model.addAttribute("post", postService.findByPostID(id));
		return "updatePost";
	}

	//This will only handle exceptions thrown from inside the controller, not globally
//	@ExceptionHandler(SpringBlogException.class)
//	public ModelAndView handleSpringBlogException(SpringBlogException e)
//	{
//		log.error("SpringBlogException occurred: " + e.getMessage());
//
//		ModelAndView model = new ModelAndView("error");
//		model.addObject("exception", e);
//		return model;
//	}
}
