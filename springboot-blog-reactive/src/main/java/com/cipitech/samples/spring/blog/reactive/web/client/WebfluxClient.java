package com.cipitech.samples.spring.blog.reactive.web.client;

import com.cipitech.samples.spring.blog.reactive.domain.PostStatus;
import com.cipitech.samples.spring.blog.reactive.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Slf4j
public class WebfluxClient
{
	public static void main(String[] args)
	{
		WebClient webClient = WebClient.create("http://localhost:8081/blog");

		PostDTO post = new PostDTO();
		post.setTitle("title");
		post.setBody("body");
		post.setDescription("description");
		post.setSlug("1");
		post.setPostStatus(PostStatus.DRAFT);

		PostDTO savedPost = webClient.post().uri("/api/rest/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(post)
				.exchangeToMono(clientResponse -> clientResponse.bodyToMono(PostDTO.class))
				.block();

		if(savedPost != null)
		{
			log.info(savedPost.toString());

			savedPost.setTitle("title2");
			savedPost.setBody("body2");
			savedPost.setSlug("2");
			savedPost.setDescription("description2");
			savedPost.setPostStatus(PostStatus.PUBLISHED);

			webClient.put().uri("/api/rest/posts")
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(savedPost)
					.exchangeToMono(clientResponse -> clientResponse.bodyToMono(PostDTO.class))
					.block();
		}

		List<PostDTO> posts = webClient.get().uri("/api/rest/posts")
				.accept(MediaType.APPLICATION_JSON)
				.exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(PostDTO.class))
				.collectList().block();

		assert posts != null;
		log.info(posts.toString());
	}
}
