package com.cipitech.samples.spring.jooq.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
	private Integer id;
	private String  name;
	private String  email;

	public User(String name, String email)
	{
		this.name = name;
		this.email = email;
	}
}
