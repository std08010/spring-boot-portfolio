package com.cipitech.samples.spring.blog.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateUtils
{
	public static LocalDateTime convertToLocalDateTime(Timestamp date)
	{
		if (date == null)
		{
			return null;
		}
		else
		{
			return date.toLocalDateTime();
		}
	}
}
