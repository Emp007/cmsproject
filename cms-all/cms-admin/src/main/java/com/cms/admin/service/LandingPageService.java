package com.cms.admin.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import com.cms.admin.CMSAdminException;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.UrlFormatter;
import com.cms.admin.util.URLConstants;
import com.cms.model.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LandingPageService {

	private static final Logger logger = LoggerFactory.getLogger(LandingPageService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private RestServiceUtil restServiceUtil;

	@Autowired
	private UrlFormatter urlFormatter;

	public List<Page> getIndexPage(String hostName, String templetName, String indexPage) {
		int status = 0;
		String url ="";
		try {
			url = String.format(URLConstants.GET_LANDINGPAGE_URL, URLEncoder.encode(hostName, "UTF-8"), templetName, URLEncoder.encode(indexPage, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			url = urlFormatter.formatUrl(url);
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			System.out.println("REsponse : " + response);

			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while landing page calling.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Page>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching landing page details", e);
			throw new CMSAdminException("Error while fetching landing page details", e);
		}
	}

	public Map<String, String> arrangePages(List<Page> pages) {

		Map<String, String> pageMap = new HashMap<String, String>();
		try {
			pages.forEach(page -> {
				pageMap.put(page.getPageName(), page.getTemplateContent());
			});
		} catch (Exception e) {
			throw new CMSAdminException(e.getMessage(), e);
		}
		return pageMap;
	}
	
	public Map<String, String> getHerosPagesURL(List<Page> pages) {

		Map<String, String> pageMap = new HashMap<String, String>();
		try {
			pages.forEach(page -> {
				pageMap.put(page.getPageName(), page.getHerosURL());
			});
		} catch (Exception e) {
			throw new CMSAdminException(e.getMessage(), e);
		}
		return pageMap;
	}
	
	

	public Map<String, String> indexPageSetting(List<Page> pages, String pageName) {

		Map<String, String> pageMap = new HashMap<String, String>();
		try {
			pages.forEach(page -> {
				if (page.getPageName().equals(pageName)) {
					pageMap.put(page.getPageName(), page.getTemplateContent());
				}
			});
		} catch (Exception e) {
			throw new CMSAdminException(e.getMessage(), e);
		}
		return pageMap;
	}

}
