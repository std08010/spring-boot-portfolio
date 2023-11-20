package com.cipitech.samples.spring.blog.jpa.service;

import com.cipitech.samples.spring.blog.jpa.domain.Comment;
import com.cipitech.samples.spring.blog.jpa.domain.Post;
import com.cipitech.samples.spring.blog.jpa.dto.CommentDTO;
import com.cipitech.samples.spring.blog.jpa.exception.SpringBlogException;
import com.cipitech.samples.spring.blog.jpa.mapper.CommentMapper;
import com.cipitech.samples.spring.blog.jpa.repository.CommentRepository;
import com.cipitech.samples.spring.blog.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService
{
	private final PostRepository    postRepository;
	private final CommentRepository commentRepository;
	private final CommentMapper     commentMapper;

	public List<CommentDTO> findByPost(String slug)
	{
		Post post = getPostBySlug(slug);
		return post.getComments().stream().map(commentMapper::toCommentDTO).toList();
	}

	public void create(CommentDTO commentDTO, String slug)
	{
		Comment comment = commentMapper.toComment(commentDTO);
		comment.setCreatedOn(LocalDateTime.now());
		comment.setUpdatedOn(LocalDateTime.now());
		Post postBySlug = getPostBySlug(slug);
		comment.setPost(postBySlug);
		comment = commentRepository.save(comment);
		postBySlug.getComments().add(comment);
		postRepository.save(postBySlug);
	}

	private Post getPostBySlug(String slug)
	{
		return postRepository.findBySlug(slug).orElseThrow(() -> new SpringBlogException("Cannot find post by slug - " + slug));
	}
}
