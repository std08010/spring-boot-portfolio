package com.cipitech.samples.spring.blog.jpa.service;

import com.cipitech.samples.spring.blog.jpa.domain.Post;
import com.cipitech.samples.spring.blog.jpa.dto.PostDTO;
import com.cipitech.samples.spring.blog.jpa.exception.SpringBlogException;
import com.cipitech.samples.spring.blog.jpa.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService
{
	private final PostRepository postRepository;

	public List<PostDTO> getPostsByPage(int size, int page)
	{
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "title"));
		Page<Post> postsPage = postRepository.findAll(pageable);

		log.debug("Total amount of elements: {}", postsPage.getTotalElements());
		log.debug("Total number of pages: {}", postsPage.getTotalPages());
		log.debug("Has next page: {}", postsPage.hasNext());
		log.debug("Has previous page: {}", postsPage.hasPrevious());

		return postsPage.getContent().stream().map(PostDTO::toPostDTO).collect(Collectors.toList());
	}

	public PostDTO findByPostID(Integer postId)
	{
		return PostDTO.toPostDTO(postRepository.findById(postId).orElseThrow(() -> new SpringBlogException("Cannot find post by id: " + postId)));
	}

	public boolean postExistsWithTitle(String title)
	{
		return postRepository.existsPostByTitle(title);
	}

	public boolean isEmpty()
	{
		return !(postRepository.count() > 0);
	}

	public Set<PostDTO> findAllPosts()
	{
		return postRepository.findAll(Sort.by(Sort.Direction.ASC, "title")).stream().map(PostDTO::toPostDTO).collect(Collectors.toSet());
	}

	public void addPost(PostDTO postDTO)
	{
		postDTO.setCreatedOn(LocalDateTime.now());
		postDTO.setUpdatedOn(LocalDateTime.now());
		postRepository.save(PostDTO.toPost(postDTO));
	}

	public void updatePost(PostDTO postDTO)
	{
		postDTO.setUpdatedOn(LocalDateTime.now());
		postRepository.save(PostDTO.toPost(postDTO));
	}

	public void deletePost(Integer id)
	{
		postRepository.deleteById(id);
	}
}
