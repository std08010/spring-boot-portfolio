package com.cipitech.samples.spring.blog.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
public class ReactorTest
{
	@Test
	void testFlux()
	{
		Flux<String> flux = Flux.just("Spring", "SpringBoot", "Reactor");
		flux.log().subscribe();
	}
}
