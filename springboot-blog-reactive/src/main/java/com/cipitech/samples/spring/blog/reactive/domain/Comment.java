package com.cipitech.samples.spring.blog.reactive.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Document(collection = "comments")
@Table(name = "comments")
public class Comment
{
	@Id
//	private String       id;
	private Long id;

	private String        title;
	private String        body;

//	@Field(name = "author_name")
	private String        authorName;

//	@Field(name = "created_on")
	private LocalDateTime createdOn;

//	@Field(name = "updated_on")
	private LocalDateTime updatedOn;

	private Post post;
}
