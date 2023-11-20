package com.cipitech.samples.spring.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User
{
	@MongoId
	private String id;
	@Field(name = "username")
	private String  name;
	private String  email;

	public User(String name, String email)
	{
		this.name = name;
		this.email = email;
	}
}
