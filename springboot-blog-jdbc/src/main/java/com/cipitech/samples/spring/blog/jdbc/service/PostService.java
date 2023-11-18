package com.cipitech.samples.spring.blog.jdbc.service;

import com.cipitech.samples.spring.blog.jdbc.dto.PostDTO;
import com.cipitech.samples.spring.blog.jdbc.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class PostService
{
	public abstract PostRepository getRepository();

	public PostDTO findByPostID(Integer postId)
	{
		return PostDTO.toPostDTO(getRepository().findByPostID(postId));
	}

	public boolean postExistsWithTitle(String title)
	{
		return getRepository().postExistsWithTitle(title);
	}

	public boolean isEmpty()
	{
		return getRepository().isEmpty();
	}

	public Set<PostDTO> findAllPosts()
	{
		return getRepository().findAllPosts().stream().map(PostDTO::toPostDTO).collect(Collectors.toSet());
	}

	public void addPost(PostDTO postDTO)
	{
		postDTO.setCreatedOn(LocalDateTime.now());
		postDTO.setUpdatedOn(LocalDateTime.now());
		getRepository().addPost(PostDTO.toPost(postDTO));
	}

	public void updatePost(PostDTO postDTO)
	{
		getRepository().updatePost(PostDTO.toPost(postDTO));
	}

	public void deletePost(Integer id)
	{
		getRepository().deletePost(id);
	}
}
