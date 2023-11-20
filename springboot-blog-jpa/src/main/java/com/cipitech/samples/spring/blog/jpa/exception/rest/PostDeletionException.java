package com.cipitech.samples.spring.blog.jpa.exception.rest;

public class PostDeletionException extends RuntimeException
{
	public PostDeletionException(String message)
	{
		super(message);
	}
}
