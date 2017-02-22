package com.cms.admin.service;

import java.util.List;
import java.util.Map;

import com.cms.model.Page;

public interface LandingPageService {

	public List<Page> getIndexPage(String hostName, String templetName, String indexPage);
	
	public Map<String, String> arrangePages(List<Page> pages);
	
	public Map<String, String> indexPageSetting(List<Page> pages, String pageName);

}
