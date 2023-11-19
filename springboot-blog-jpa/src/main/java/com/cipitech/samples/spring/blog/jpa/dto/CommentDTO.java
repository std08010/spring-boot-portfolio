package com.cipitech.samples.spring.blog.jpa.dto;

import com.cipitech.samples.spring.blog.jpa.domain.Comment;
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

	public static Comment toComment(CommentDTO dto)
	{
		return Comment.builder()
				.title(dto.getTitle())
				.authorName(dto.getAuthorName())
				.body(dto.getBody())
				.createdOn(dto.getCreatedOn())
				.updatedOn(dto.getUpdatedOn())
				.build();
	}

	public static CommentDTO toCommentDTO(Comment comment)
	{
		return CommentDTO.builder()
				.title(comment.getTitle())
				.authorName(comment.getAuthorName())
				.body(comment.getBody())
				.createdOn(comment.getCreatedOn())
				.updatedOn(comment.getUpdatedOn())
				.build();
	}
}
