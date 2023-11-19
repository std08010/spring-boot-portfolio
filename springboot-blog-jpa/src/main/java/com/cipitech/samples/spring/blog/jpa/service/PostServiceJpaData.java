package com.cipitech.samples.spring.blog.jpa.service;

import com.cipitech.samples.spring.blog.jpa.domain.Post;
import com.cipitech.samples.spring.blog.jpa.dto.PostDTO;
import com.cipitech.samples.spring.blog.jpa.repository.PostRepositoryJpaData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Profile("jpa-data")
@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceJpaData extends PostService
{
	private final PostRepositoryJpaData postRepository;

	@Override
	public PostRepositoryJpaData getRepository()
	{
		return postRepository;
	}

	public List<PostDTO> getPostsByPage(int size, int page)
	{
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "title"));
		Page<Post> postsPage = getRepository().findAll(pageable);

		log.debug("Total amount of elements: {}", postsPage.getTotalElements());
		log.debug("Total number of pages: {}", postsPage.getTotalPages());
		log.debug("Has next page: {}", postsPage.hasNext());
		log.debug("Has previous page: {}", postsPage.hasPrevious());

		return postsPage.getContent().stream().map(PostDTO::toPostDTO).collect(Collectors.toList());
	}
}
