package com.cipitech.samples.spring.blog.reactive.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "posts")
public class Post
{
	@Id
	private String id;

	private String title;
	private String description;
	private String body;
	private String slug;

	@Field(name = "post_status")
	private PostStatus postStatus;

	@Field(name = "created_on")
	private LocalDateTime createdOn;

	@Field(name = "updated_on")
	private LocalDateTime updatedOn;

	private List<Comment> comments;
}
