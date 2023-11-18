package com.cipitech.samples.spring.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "posts") //This is not the same Table as in spring-data-jpa (jakarta.persistence.Table)
public class Post
{
	@Id
	private Integer       id;
	private String        title;
	private String        description;
	private String        body;
	private String        slug;
	private PostStatus    postStatus;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	@Transient
	private List<Comment> comments;
}
