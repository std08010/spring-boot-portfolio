package com.cipitech.samples.spring.blog.reactive.exception.rest;

import lombok.Data;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorDetails
{
	private String              errorCode;
	private String              errorMessage;
	private String              devErrorMessage;
	private Map<String, Object> additionalData = new HashMap<>();

	public ErrorDetails(Exception e, String errorCode)
	{
		setErrorCode(errorCode);
		setErrorMessage(e.getMessage());
		setDevErrorMsg(e);
	}

	public void setDevErrorMsg(Exception e)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		setDevErrorMessage(sw.toString());
	}
}
