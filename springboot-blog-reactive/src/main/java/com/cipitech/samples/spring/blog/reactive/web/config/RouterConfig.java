package com.cipitech.samples.spring.blog.reactive.web.config;

import com.cipitech.samples.spring.blog.reactive.web.handler.PostHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Component
public class RouterConfig
{
	@Bean
	RouterFunction<ServerResponse> postRoutes(PostHandler postHandler)
	{
		return nest(path("/api/func/posts"),
				nest(accept(MediaType.APPLICATION_JSON),
						route(GET("/{slug}"), postHandler::findPostBySlug)
								.andRoute(method(HttpMethod.GET), postHandler::listPosts)
								.andRoute(DELETE("/{id}"), postHandler::deletePost)
								.andRoute(POST("/"), postHandler::savePost)
								.andRoute(PUT("/"), postHandler::updatePost)
				)
		);
	}
}
