package com.cipitech.samples.spring.blog.validation;

import com.cipitech.samples.spring.blog.dto.PostDTO;
import com.cipitech.samples.spring.blog.service.PostService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public record BlogPostTitleValidator(
		PostService postService) implements ConstraintValidator<BlogPostTitleAlreadyExists, PostDTO>
{
	@Override
	public void initialize(BlogPostTitleAlreadyExists constraintAnnotation)
	{
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(PostDTO post, ConstraintValidatorContext constraintValidatorContext)
	{
		if (!StringUtils.isEmpty(post.getTitle()) && postService.postExistsWithTitle(post.getTitle()))
		{
			constraintValidatorContext.disableDefaultConstraintViolation();
			constraintValidatorContext
					.buildConstraintViolationWithTemplate(constraintValidatorContext.getDefaultConstraintMessageTemplate()) // BlogPostTitleAlreadyExists.message
//					.buildConstraintViolationWithTemplate("{MyCustomLabel}")
					.addPropertyNode("title").addConstraintViolation();
			return false;
		}
		return true;
	}
}
