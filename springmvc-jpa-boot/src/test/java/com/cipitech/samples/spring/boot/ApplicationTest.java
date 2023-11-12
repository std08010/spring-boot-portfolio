package com.cipitech.samples.spring.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Either run as a test in Intellij
 * OR run "mvn test"
 */

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(classes=Application.class) //Note that here we are using the class of the SpringBootApplication
public class ApplicationTest
{
	@Autowired
	private SupportProperties supportProperties;
	
	@Test
	public void testContextLoads()
	{
		log.info(supportProperties.toString());
	}
}
