package com.cipitech.samples.spring.blog.jpa.mapper;

import com.cipitech.samples.spring.blog.jpa.domain.Comment;
import com.cipitech.samples.spring.blog.jpa.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper
{
	public Comment toComment(CommentDTO dto)
	{
		return Comment.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.authorName(dto.getAuthorName())
				.body(dto.getBody())
				.createdOn(dto.getCreatedOn())
				.updatedOn(dto.getUpdatedOn())
				.build();
	}

	public CommentDTO toCommentDTO(Comment comment)
	{
		return CommentDTO.builder()
				.id(comment.getId())
				.title(comment.getTitle())
				.authorName(comment.getAuthorName())
				.body(comment.getBody())
				.createdOn(comment.getCreatedOn())
				.updatedOn(comment.getUpdatedOn())
				.build();
	}
}
