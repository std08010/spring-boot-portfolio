package com.cipitech.samples.spring.blog.reactive.exception;

import com.cipitech.samples.spring.blog.reactive.exception.rest.ErrorDetails;
import com.cipitech.samples.spring.blog.reactive.exception.rest.PostDeletionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
@Profile("global-exception-rest")
public class RestGlobalExceptionHandler
{
	@ExceptionHandler(SpringBlogException.class)
	public ResponseEntity<?> handleSpringBlogException(SpringBlogException e)
	{
		log.error("SpringBlogException occurred: " + e.getMessage());

		return new ResponseEntity<>(new ErrorDetails(e, HttpStatus.BAD_REQUEST.getReasonPhrase()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(PostDeletionException.class)
	public ResponseEntity<?> handlePostDeletionException(PostDeletionException e)
	{
		log.error("PostDeletionException occurred: " + e.getMessage());

		return new ResponseEntity<>(new ErrorDetails(e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e)
	{
		log.error("Exception occurred: " + e.getMessage());

		e.printStackTrace();

		return new ResponseEntity<>(new ErrorDetails(e, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
