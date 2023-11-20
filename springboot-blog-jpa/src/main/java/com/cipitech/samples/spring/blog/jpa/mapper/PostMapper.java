package com.cipitech.samples.spring.blog.jpa.mapper;

import com.cipitech.samples.spring.blog.jpa.domain.Post;
import com.cipitech.samples.spring.blog.jpa.dto.PostDTO;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class PostMapper
{
	public Post toPost(PostDTO dto)
	{
		return Optional.ofNullable(dto).map(dtoObj ->
						Post.builder()
								.id(dtoObj.getId())
								.title(dtoObj.getTitle())
								.description(dtoObj.getDescription())
								.body(dtoObj.getBody())
								.slug(dtoObj.getSlug())
								.createdOn(dtoObj.getCreatedOn())
								.updatedOn(dtoObj.getUpdatedOn())
								.postStatus(dtoObj.getPostStatus())
								.build())
				.orElse(null);
	}

	public PostDTO toPostDTO(Post post)
	{
		return Optional.ofNullable(post).map(postObj ->
						PostDTO.builder()
								.id(postObj.getId())
								.title(postObj.getTitle())
								.description(postObj.getDescription())
								.body(postObj.getBody())
								.slug(postObj.getSlug())
								.createdOn(postObj.getCreatedOn())
								.updatedOn(postObj.getUpdatedOn())
								.postStatus(postObj.getPostStatus())
								.build())
				.orElse(null);
	}
}
