package com.cipitech.samples.spring.boot;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "support")
@Validated
public class SupportProperties
{
	@NotNull
	private String applicationName;

	@NotNull
	@Email
	private String email;

	@Min(1) @Max(5)
	private Integer severity;

	//If we have nested properties then in order
	// for validation to work we must use the @Valid annotation
	// @Valid
	// private NestedProperties nestedProps;
}
