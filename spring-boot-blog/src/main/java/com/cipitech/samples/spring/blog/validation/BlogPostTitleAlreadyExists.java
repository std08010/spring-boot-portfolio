package com.cipitech.samples.spring.blog.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //can be applied on a class level
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BlogPostTitleValidator.class)
public @interface BlogPostTitleAlreadyExists
{
	String message() default "Title Already Exists";
//	String message() default "{MyCustomLabel}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
