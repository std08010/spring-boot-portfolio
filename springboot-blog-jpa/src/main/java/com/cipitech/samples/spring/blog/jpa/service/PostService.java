package com.cipitech.samples.spring.blog.jpa.service;

import com.cipitech.samples.spring.blog.jpa.domain.Post;
import com.cipitech.samples.spring.blog.jpa.dto.PostDTO;
import com.cipitech.samples.spring.blog.jpa.exception.SpringBlogException;
import com.cipitech.samples.spring.blog.jpa.mapper.PostMapper;
import com.cipitech.samples.spring.blog.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService
{
	private final PostRepository postRepository;
	private final PostMapper     postMapper;

	public List<PostDTO> getPostsByPage(int size, int page)
	{
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "title"));
		Page<Post> postsPage = postRepository.findAll(pageable);

		log.debug("Total amount of elements: {}", postsPage.getTotalElements());
		log.debug("Total number of pages: {}", postsPage.getTotalPages());
		log.debug("Has next page: {}", postsPage.hasNext());
		log.debug("Has previous page: {}", postsPage.hasPrevious());

		return postsPage.getContent().stream().map(postMapper::toPostDTO).collect(Collectors.toList());
	}

	public PostDTO findByPostID(Integer postId)
	{
		return postMapper.toPostDTO(postRepository.findById(postId).orElseThrow(() -> new SpringBlogException("Cannot find post by id: " + postId)));
	}

	public boolean postExistsWithTitle(String title)
	{
		return postRepository.existsPostByTitle(title);
	}

	public PostDTO findBySlug(String slug)
	{
		Post post = postRepository.findBySlug(slug).orElseThrow(() -> new SpringBlogException("Cannot find Post with Slug - " + slug));
		return postMapper.toPostDTO(post);
	}

	public boolean isEmpty()
	{
		return !(postRepository.count() > 0);
	}

	public List<PostDTO> findAllPosts()
	{
		return postRepository.findAll(Sort.by(Sort.Direction.ASC, "title")).stream().map(postMapper::toPostDTO).toList();
	}

	public PostDTO addPost(PostDTO postDTO)
	{
		if (postDTO == null)
		{
			return null;
		}

		Post post = postMapper.toPost(postDTO);
		post.setId(null);
		post.setCreatedOn(LocalDateTime.now());
		post.setUpdatedOn(LocalDateTime.now());
		return postMapper.toPostDTO(postRepository.save(post));
	}

	public PostDTO updatePost(PostDTO postDTO)
	{
		if (postDTO == null)
		{
			return null;
		}

		Post savedPost = postRepository.findById(postDTO.getId()).orElseThrow(() -> new SpringBlogException("Cannot find Post with Id " + postDTO.getId()));
		Post post = postMapper.toPost(postDTO);
		post.setId(savedPost.getId());
		post.setUpdatedOn(LocalDateTime.now());
		return postMapper.toPostDTO(postRepository.save(post));
	}

	public void deletePost(Integer id)
	{
		postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No post found with id=" + id));
		postRepository.deleteById(id);
	}
}
