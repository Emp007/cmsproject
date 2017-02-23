package com.cms.app.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DateTimeUtil {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Date currentDateTime(){
		
		Date date = null;
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		df.setTimeZone(tz);
		String currentDateTime = df.format(new Date());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        try {
        	logger.debug("Process start to get current date and time");
             date = formatter.parse(currentDateTime);
             System.out.println("hello");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
     
	}

}
