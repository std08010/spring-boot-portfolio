package com.cipitech.samples.spring.boot;

import com.cipitech.samples.spring.boot.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackageClasses = User.class) // Instruct Spring JPA to scan for Entities under the package where User exists.
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}
