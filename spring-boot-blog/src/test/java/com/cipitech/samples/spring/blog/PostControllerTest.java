package com.cipitech.samples.spring.blog;

import com.cipitech.samples.spring.blog.domain.Post;
import com.cipitech.samples.spring.blog.service.PostServiceInMemory;
import com.cipitech.samples.spring.blog.web.controllers.PostController;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;

import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest
{
	@Autowired
	private MockMvc             mvc;

	@MockBean
	private PostServiceInMemory postServiceInMemory;

	@Test
	public void testFindAllPosts() throws Exception
	{
		Post post = new Post();
		post.setId(1);
		post.setTitle("Test");
		post.setDescription("Test");
		postServiceInMemory.addPost(post);

		Post secondPost = new Post();
		secondPost.setId(2);
		secondPost.setTitle("Test 1");
		secondPost.setDescription("Test 1");
		postServiceInMemory.addPost(secondPost);

		BDDMockito.given(postServiceInMemory.findAllPosts()).willReturn(new CopyOnWriteArraySet<>(Arrays.asList(post, secondPost)));

		this.mvc.perform(MockMvcRequestBuilders.get("/posts").accept(MediaType.TEXT_HTML))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("post"))
				.andExpect(MockMvcResultMatchers.model().attribute("posts", hasSize(2)));
	}
}
