package com.cipitech.samples.spring.blog.reactive.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO
{
//	private String       id;
	private Long       id;
	@NotNull
	@Size(min = 3, max = 50, message = "Title must be minimum 3 characters, and maximum 50 characters long")
	private String        title;
	@NotNull
	private String        authorName;
	@NotNull
	@Size(min = 3, max = 100, message = "Body must be minimum 3 characters, and maximum 100 characters long")
	private String        body;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
