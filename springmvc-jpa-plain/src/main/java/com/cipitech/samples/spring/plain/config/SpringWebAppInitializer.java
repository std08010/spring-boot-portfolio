package com.cipitech.samples.spring.plain.config;

import jakarta.servlet.Filter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Since the Servlet 3.x specification, you can register servlets and filters programmatically using ServletContainerInitializer.
 * Spring MVC provides a convenient class called AbstractAnnotationConfigDispatcherServletInitializer to
 * register a DispatcherServlet.
 *
 * The job of DispatcherServlet is to take an incoming URI and find the right combination
 * of handlers (Controller classes) and views (usually JSPs).
 * When the DispatcherServlet determines the view, it renders it as the response.
 * Finally, the DispatcherServlet returns the Response Object to back to the client.
 */
public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	/**
	 * Configure AppConfig.class as RootConfigurationClasses, which will become the parent ApplicationContext
	 * that contains bean definitions shared by all child (DispatcherServlet) contexts.
	 */
	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class[]{AppConfig.class};
	}

	/**
	 * Configure WebMvcConfig.class as ServletConfigClasses, which is
	 * the child ApplicationContext that contains WebMvc bean definitions.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[]{WebMvcConfig.class};
	}

	/**
	 * Configure "/" as ServletMapping, which means that all the requests
	 * will be handled by DispatcherServlet.
	 */
	@Override
	protected String[] getServletMappings()
	{
		return new String[]{"/"};
	}

	/**
	 * Register OpenEntityManagerInViewFilter as a servlet filter so that you can lazy-load the JPA entity
	 * lazy collections while rendering the view.
	 */
	@Override
	protected Filter[] getServletFilters()
	{
		return new Filter[]{new OpenEntityManagerInViewFilter()};
	}
}
