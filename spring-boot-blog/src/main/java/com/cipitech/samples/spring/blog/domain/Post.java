package com.cipitech.samples.spring.blog.domain;

import com.cipitech.samples.spring.blog.validation.BlogPostTitleAlreadyExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@BlogPostTitleAlreadyExists/*(message = "Title Already Exists. Please try something else.")*/
@Table(name = "posts") //This is not the same Table as in spring-data-jpa (jakarta.persistence.Table)
public class Post
{
	@Id
	private Integer       id;
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
	@Transient
	private List<Comment> comments;
}
