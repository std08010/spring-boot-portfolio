package com.cipitech.samples.spring.mongo.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
@Getter
@Setter
@Profile("mongo-plain")
@Configuration
@ConfigurationProperties(prefix = "mongo")
public class MongoConfig
{
	private String url;
	private String database;

	@Bean
	public MongoClient mongoClient()
	{
		return MongoClients.create(url);
	}

	@Bean
	public MongoTemplate mongoTemplate()
	{
		return new MongoTemplate(mongoClient(), database);
	}
}
