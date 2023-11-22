package com.cipitech.samples.spring.blog.reactive.web.client;

import com.cipitech.samples.spring.blog.reactive.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
public class RestClient
{
	public static void main(String[] args)
	{
		RestTemplate restTemplate = new RestTemplate();

		PostDTO[] posts = restTemplate.getForEntity("http://localhost:8081/blog/api/rest/posts", PostDTO[].class).getBody();

		assert posts != null;
		log.info(Arrays.toString(posts));
	}
}
