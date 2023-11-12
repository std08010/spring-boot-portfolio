package com.cipitech.samples.spring.blog.validation;

import com.cipitech.samples.spring.blog.domain.Post;
import com.cipitech.samples.spring.blog.service.PostServiceInMemory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public record BlogPostTitleValidator(
		PostServiceInMemory postService) implements ConstraintValidator<BlogPostTitleAlreadyExists, Post>
{
	@Override
	public void initialize(BlogPostTitleAlreadyExists constraintAnnotation)
	{
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Post post, ConstraintValidatorContext constraintValidatorContext)
	{
		if (!StringUtils.isEmpty(post.getTitle()) && postService.postExistsWithTitle(post.getTitle()))
		{
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate()).addPropertyNode("title").addConstraintViolation();
			return false;
		}
		return true;
	}
}
