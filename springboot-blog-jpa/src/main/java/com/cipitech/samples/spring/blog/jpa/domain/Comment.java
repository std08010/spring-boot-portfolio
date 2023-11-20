package com.cipitech.samples.spring.blog.jpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer       id;

	@Column(length = 200, nullable = false)
	private String        title;

	@Column(columnDefinition = "TEXT")
	private String        body;

	@Column(name = "author_name", length = 200, nullable = false)
	private String        authorName;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Post post;
}
