package com.cipitech.samples.spring.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Uncomment commented code when you want to build a WAR file instead of JAR file

@SpringBootApplication
public class BlogApplication /*extends SpringBootServletInitializer*/
{
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
//	{
//		return application.sources(BlogApplication.class);
//	}

	public static void main(String[] args)
	{
		SpringApplication.run(BlogApplication.class, args);
	}
}
