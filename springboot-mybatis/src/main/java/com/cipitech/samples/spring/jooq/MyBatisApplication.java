package com.cipitech.samples.spring.jooq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cipitech.samples.spring.jooq.mappers")
public class MyBatisApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(MyBatisApplication.class, args);
	}
}
