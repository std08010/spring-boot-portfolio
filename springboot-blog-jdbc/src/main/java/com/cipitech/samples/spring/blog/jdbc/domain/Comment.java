package com.cipitech.samples.spring.blog.jdbc.domain;

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
	private String        title;
	private String        authorName;
	private String        body;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
}
