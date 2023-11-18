package com.cipitech.samples.spring.blog.jdbc.dto;

import com.cipitech.samples.spring.blog.jdbc.domain.Post;
import com.cipitech.samples.spring.blog.jdbc.domain.PostStatus;
import com.cipitech.samples.spring.blog.jdbc.validation.BlogPostTitleAlreadyExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@BlogPostTitleAlreadyExists/*(message = "Title Already Exists. Please try something else.")*/
public class PostDTO
{
	@NotNull
	//The message already exists in the messages.properties, so there is no need to define it here.
	@Size(min = 3, max = 50)
	private String        title;
	@NotNull
	@Size(min = 3, max = 500)
	private String        description;
	@NotNull
	@Size(min = 3, max = 5000/*, message = "Body must be minimum 3 characters, and maximum 5000 characters long"*/)
	private String        body;
	private String        slug;
	private PostStatus    postStatus;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private MultipartFile coverPhoto;

	public static Post toPost(PostDTO dto)
	{
		return Optional.ofNullable(dto).map(dtoObj ->
						Post.builder()
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

	public static PostDTO toPostDTO(Post post)
	{
		return Optional.ofNullable(post).map(postObj ->
						PostDTO.builder()
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
