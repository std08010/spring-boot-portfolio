package com.cipitech.samples.spring.blog.reactive.service;

import com.cipitech.samples.spring.blog.reactive.domain.Post;
import com.cipitech.samples.spring.blog.reactive.dto.PostDTO;
import com.cipitech.samples.spring.blog.reactive.mapper.PostMapper;
import com.cipitech.samples.spring.blog.reactive.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostService
{
	private final PostRepository postRepository;
	private final PostMapper     postMapper;

	public Mono<PostDTO> findByPostID(String postId)
	{
		return postRepository.findById(postId).map(postMapper::toPostDTO);
	}

	public Boolean postExistsWithTitle(String title)
	{
		return postRepository.existsPostByTitle(title).block();
	}

	public Mono<PostDTO> findBySlug(String slug)
	{
		Mono<Post> post = postRepository.findBySlug(slug);
		return post.map(postMapper::toPostDTO);
	}

	public Mono<Boolean> isEmpty()
	{
		return postRepository.count().map(count -> !(count > 0));
	}

	public Flux<PostDTO> findAllPosts()
	{
		return postRepository.findAll().map(postMapper::toPostDTO).switchIfEmpty(Flux.empty());
	}

	public Mono<PostDTO> addPost(PostDTO postDTO)
	{
		if (postDTO == null)
		{
			return null;
		}

		Post post = postMapper.toPost(postDTO);
		post.setId(null);
		post.setCreatedOn(LocalDateTime.now());
		post.setUpdatedOn(LocalDateTime.now());
		return postRepository.save(post).map(postMapper::toPostDTO);
	}

	public Mono<PostDTO> updatePost(PostDTO postDTO)
	{
		if (postDTO == null)
		{
			return null;
		}

		return postRepository.findById(postDTO.getId())
				.flatMap(savedPost ->
				{
					Post post = postMapper.toPost(postDTO);
					post.setId(savedPost.getId());
					post.setUpdatedOn(LocalDateTime.now());
					return postRepository.save(post);
				})
				.map(postMapper::toPostDTO);
	}

	public Mono<Void> deletePost(String id)
	{
		return postRepository.deleteById(id);
	}
}
