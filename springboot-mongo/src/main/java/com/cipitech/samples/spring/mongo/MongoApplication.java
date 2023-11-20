package com.cipitech.samples.spring.mongo;

import com.cipitech.samples.spring.mongo.domain.User;
import com.cipitech.samples.spring.mongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MongoApplication implements CommandLineRunner
{
	private final UserRepository userRepository;

	public static void main(String[] args)
	{
		SpringApplication.run(MongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		User user = User.builder()
				.name("haris")
				.email("haris.tsipis@gmail.com")
				.build();
		userRepository.save(user);
	}
}
