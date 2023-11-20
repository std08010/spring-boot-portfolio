package com.cipitech.samples.spring.blog.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * SpringMVC’s CORS configuration is NOT applied to Spring Data REST endpoints.
 * So this is necessary.
 */

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer
{
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors)
	{
		cors
				.addMapping("/api/data/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.allowCredentials(false)
				.maxAge(3600);
	}
}
