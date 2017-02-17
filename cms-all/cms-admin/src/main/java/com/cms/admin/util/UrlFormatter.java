package com.cms.admin.util;

import org.springframework.stereotype.Component;

@Component
public class UrlFormatter {
	
	public String formatUrl(String url){
		return url.replaceAll(" ", "%20");
	}

}
