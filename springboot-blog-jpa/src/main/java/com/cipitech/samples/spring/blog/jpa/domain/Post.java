package com.cipitech.samples.spring.blog.jpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "posts") //This is not the same Table as in spring-data-jpa (jakarta.persistence.Table)
public class Post
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "posts_id_seq", sequenceName = "posts_id_seq", allocationSize = 1)
	//When you put GenerationType.AUTO (OR SEQUENCE) then hibernate assumes that a sequence named "posts_seq" is available
	//So, we have to specify the sequence generator because postgres creates a sequence named "posts_id_seq"
	//When you put GenerationType.IDENTITY then hibernate doesn't care what the sequence name is.
	//For H2 it creates "generated default as identity" column and for postgres it creates "serial" column
	private Integer id;

	@Column(length = 50, nullable = false)
	private String title;

	@Column(length = 500, nullable = false)
	private String description;

	@Column(columnDefinition = "TEXT")
	private String body;

	@Column(length = 60)
	private String slug;

	@Column(name = "post_status", length = 20)
	@Enumerated(EnumType.STRING)
	private PostStatus postStatus;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	//whenever a post is deleted, you also delete the related Comment records from the database.
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;
}
