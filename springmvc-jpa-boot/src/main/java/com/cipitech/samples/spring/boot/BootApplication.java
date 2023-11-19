package com.cipitech.samples.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
//@EntityScan(basePackageClasses = User.class) // Instruct Spring JPA to scan for Entities under the package where User exists.
public class BootApplication implements CommandLineRunner
{
	private final SupportProperties supportProperties;

	public BootApplication(SupportProperties supportProperties)
	{
		this.supportProperties = supportProperties;
	}

	public static void main(String[] args)
	{
		SpringApplication.run(BootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception
	{
		log.info(this.supportProperties.toString());
	}
}
