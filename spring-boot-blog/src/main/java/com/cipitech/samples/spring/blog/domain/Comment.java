package com.cipitech.samples.spring.blog.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comments")
public class Comment
{
	@Id
	private Integer       id;
	private Integer       postId;
	@NotNull
	@Size(min = 3, max = 50, message = "Title must be minimum 3 characters, and maximum 50 characters long")
	private String        title;
	@NotNull
	private String        authorName;
	@NotNull
	@Size(min = 3, max = 100 , message = "Body must be minimum 3 characters, and maximum 100 characters long")
	private String        body;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
