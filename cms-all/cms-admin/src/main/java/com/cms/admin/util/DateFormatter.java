package com.cms.admin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateFormatter {

	public static Date convertISOtoSimpleDate(Date date) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String format = formatter.format(date);
		System.out.println(format);

		String start_dt = "2011-01-31";
		
		DateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); 
		Date simpledate = (Date) parser.parse(start_dt);

		return date;
	}
}
