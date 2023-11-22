package com.cipitech.samples.spring.blog.reactive.web.handler;

import com.cipitech.samples.spring.blog.reactive.dto.PostDTO;
import com.cipitech.samples.spring.blog.reactive.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostHandler
{
	private final Mono<ServerResponse> notFound = ServerResponse.notFound().build();

	private final PostService postService;

	public Mono<ServerResponse> listPosts(ServerRequest serverRequest)
	{
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(postService.findAllPosts(), PostDTO.class)
				.switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> findPostBySlug(ServerRequest serverRequest)
	{
		Mono<PostDTO> postBySlug = postService.findBySlug(serverRequest.pathVariable("slug"));
		return postBySlug.flatMap(post -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(BodyInserters.fromValue(post)))
				.switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> savePost(ServerRequest serverRequest)
	{
		Mono<PostDTO> postDtoMono = serverRequest.bodyToMono(PostDTO.class);

		return postDtoMono.flatMap(postDto -> ServerResponse
						.status(HttpStatus.CREATED)
						.contentType(MediaType.APPLICATION_JSON)
						.body(postService.addPost(postDto), PostDTO.class))
				.switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> updatePost(ServerRequest serverRequest)
	{
		Mono<PostDTO> postDtoMono = serverRequest.bodyToMono(PostDTO.class);

		return postDtoMono.flatMap(postDto -> ServerResponse
						.status(HttpStatus.OK)
						.contentType(MediaType.APPLICATION_JSON)
						.body(postService.updatePost(postDto), PostDTO.class))
				.switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> deletePost(ServerRequest serverRequest)
	{
		return ServerResponse
				.status(HttpStatus.NO_CONTENT)
				.build(postService.deletePost(Long.valueOf(serverRequest.pathVariable("id"))))
				.switchIfEmpty(notFound);
	}
}
