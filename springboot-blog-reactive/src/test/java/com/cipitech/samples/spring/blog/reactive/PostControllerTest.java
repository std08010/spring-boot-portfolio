package com.cipitech.samples.spring.blog.reactive;

import com.cipitech.samples.spring.blog.reactive.domain.PostStatus;
import com.cipitech.samples.spring.blog.reactive.dto.PostDTO;
import com.cipitech.samples.spring.blog.reactive.service.PostService;
import com.cipitech.samples.spring.blog.reactive.web.rest.PostRestApi;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

/**
 * Only PostRestApi is scanned and only it's bean is created.
 * In the postService field our mockBean is autowired.
 */
@WebFluxTest(controllers = PostRestApi.class)
class PostControllerTest
{
	@Autowired
	private WebTestClient webTestClient;

	// This is a mockBean.....that means that a fake bean for that class is created
	// and every function that is called from that class succeeds but does nothing
	// You can define for specific calls of this bean what will be returned by using
	// BDDMockito.given().willReturn() pattern.
	@MockBean
	private PostService postService;

	@Test
	public void testFindAllPosts() throws Exception
	{
		PostDTO post = new PostDTO();
		post.setTitle("title");
		post.setBody("body");
		post.setDescription("description");
		post.setPostStatus(PostStatus.DRAFT);

		PostDTO secondPost = new PostDTO();
		secondPost.setTitle("title2");
		secondPost.setBody("body2");
		secondPost.setDescription("description2");
		secondPost.setPostStatus(PostStatus.DRAFT);

		//postService.findAllPosts() will be called in the PostRestApi
		//so the Flux with the 2 elements will be returned.
		BDDMockito.when(postService.findAllPosts()).thenReturn(Flux.just(post, secondPost));

		webTestClient.get().uri("/api/rest/posts")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.[0].title").isEqualTo("title")
				.jsonPath("$.[1].title").isEqualTo("title2");
	}
}
