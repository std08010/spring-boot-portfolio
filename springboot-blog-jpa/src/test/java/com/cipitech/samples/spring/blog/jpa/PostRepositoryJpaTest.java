package com.cipitech.samples.spring.blog.jpa;

import com.cipitech.samples.spring.blog.jpa.domain.Post;
import com.cipitech.samples.spring.blog.jpa.domain.PostStatus;
import com.cipitech.samples.spring.blog.jpa.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

@DataJpaTest
public class PostRepositoryJpaTest
{
	@Autowired
	private PostRepository postRepository;

	@Test
	void testFindAllPosts()
	{
		Post post = new Post();
		post.setTitle("sample blog post");
		post.setDescription("sample blog post");
		post.setBody("sample blog post");
		post.setSlug("sample-blog-post");
		post.setPostStatus(PostStatus.PUBLISHED);
		post.setUpdatedOn(LocalDateTime.now());
		post.setCreatedOn(LocalDateTime.now());
		postRepository.save(post);

		Assertions.assertThat(postRepository.findAll()).hasSize(1);
	}
}
