package com.cipitech.samples.spring.blog.reactive.domain;

public enum PostStatus
{
	DRAFT, PUBLISHED;

	public static PostStatus fromString(String value)
	{
		if(value == null)
		{
			return null;
		}

		return valueOf(value);
	}

	public static String intoString(PostStatus value)
	{
		if(value == null)
		{
			return null;
		}

		return value.name();
	}
}
