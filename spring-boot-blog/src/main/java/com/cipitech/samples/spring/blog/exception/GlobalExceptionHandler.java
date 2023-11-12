package com.cipitech.samples.spring.blog.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler
{
//	@ExceptionHandler(SpringBlogException.class)
	public ModelAndView handleSpringBlogException(SpringBlogException e)
	{
		log.error("SpringBlogException occurred: " + e.getMessage());

		ModelAndView model = new ModelAndView("error");
		model.addObject("exception", e);
		return model;
	}
}
