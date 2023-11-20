package com.cipitech.samples.spring.blog.jpa;

import com.cipitech.samples.spring.blog.jpa.dto.PostDTO;
import com.cipitech.samples.spring.blog.jpa.service.PostService;
import com.cipitech.samples.spring.blog.jpa.web.controllers.PostController;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import static org.hamcrest.Matchers.hasSize;

/**
 * Only PostController is scanned and only it's bean is created.
 * In the postService field our mockBean is autowired.
 */
@WebMvcTest(controllers = PostController.class)
class PostControllerTest
{
	@Autowired
	private MockMvc             mvc;

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
		post.setTitle("Test");
		post.setDescription("Test");
		postService.addPost(post);  // this does nothing as postService is a mock bean and the body of addPost is empty.

		PostDTO secondPost = new PostDTO();
		secondPost.setTitle("Test 1");
		secondPost.setDescription("Test 1");
		postService.addPost(secondPost);

		//postService.findAllPosts() will be called in the PostController
		//so the Set with the 2 elements will be returned.
		BDDMockito.given(postService.findAllPosts()).willReturn(new ArrayList<>(List.of(post, secondPost)));

		//postService.isEmpty() will return false as it is a mock implementation and those return always false by default.
		this.mvc.perform(MockMvcRequestBuilders.get("/posts").accept(MediaType.TEXT_HTML))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("post"))
				.andExpect(MockMvcResultMatchers.model().attribute("posts", hasSize(2)));
	}
}
