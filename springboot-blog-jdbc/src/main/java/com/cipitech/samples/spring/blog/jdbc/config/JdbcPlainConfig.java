package com.cipitech.samples.spring.blog.jdbc.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Profile("jdbc-plain")
@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource(value = {"classpath:jdbc.properties"})
@RequiredArgsConstructor
public class JdbcPlainConfig
{
	private final Environment env;

	@Value("${init-db:false}")
	private Boolean initDatabase;

	/**
	 * Loads properties from the jdbc.properties file
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource dataSource()
	{
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource)
	{
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * Run a sql script to initialize the database.
	 */
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource)
	{
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setEnabled(initDatabase);
		dataSourceInitializer.setDataSource(dataSource);

		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource(Objects.requireNonNull(env.getProperty("init-scripts"))));
		dataSourceInitializer.setDatabasePopulator(databasePopulator);

		return dataSourceInitializer;
	}
}
