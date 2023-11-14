package com.cipitech.samples.spring.blog.config;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Properties;

/**
 * Add extra configuration on top of the autoconfiguration
 * If you add the @EnableWebMvc annotation then the
 * autoconfiguration will be disabled.
 */

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer
{
	private final MessageSource messageSource;

	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addRedirectViewController("/", "/posts"); // redirect "/" to "/posts"
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(localeInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/assets/").addResourceLocations("/resources/assets/");
	}

	@Override
	public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer)
	{
		// Left empty intentionally so that default configuration for DispatcherServlet is used.
	}

	@Override
	public void addFormatters(FormatterRegistry registry)
	{
		//Add additional formatters here
	}

	//Instead of using this you can create a GlobalExceptionHandler annotated with the @ControllerAdvice
	@Bean(name = "simpleMappingExceptionResolver")
	@Profile("bean-exception")
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
	{
		log.debug("Using exception handler configuration via SimpleMappingExceptionResolver bean in WebConfig");
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		//add a view for each type of exception
		mappings.setProperty("SpringBlogException", "error");
		mappings.setProperty("RuntimeException", "error");
		exceptionResolver.setExceptionMappings(mappings);
		exceptionResolver.setDefaultErrorView("error");
		exceptionResolver.setExceptionAttribute("exception"); //The exception object will be passed to this attribute which can be accessed from thymeleaf.
		return exceptionResolver;
	}

	/**
	 * This adds the provided locale from the query param "lang" to a session cookie
	 * so that the user doesn't have to provide the query param again.
	 */
	@Bean
	public LocaleResolver localeResolver()
	{
		return new CookieLocaleResolver();
	}

	/**
	 * This reads the locale from the query param "lang"
	 */
	@Bean
	public LocaleChangeInterceptor localeInterceptor()
	{
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		return localeInterceptor;
	}

	@Override
	public Validator getValidator()
	{
		LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
		//Set the Hibernate validator to use the "messages.properties" file
		//for error messages and not the default "ValidationMessages.properties"
		factory.setValidationMessageSource(messageSource);
		return factory;
	}
}
