package com.cipitech.samples.spring.blog.reactive.exception.rest;

public class PostDeletionException extends RuntimeException
{
	public PostDeletionException(String message)
	{
		super(message);
	}
}
