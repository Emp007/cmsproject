package com.cms.admin.service;

import com.cms.admin.CMSAdminException;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.URLConstants;
import com.cms.model.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageService_old {
	
	private static final Logger logger = LoggerFactory.getLogger(HostService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Autowired
	private RestServiceUtil restServiceUtil;
	
	public List<Page> getAllPages() {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.GET_ALL_HOST_DETAILS, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all page  details.",
								status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<List<Page>>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching all page details", e);
			throw new CMSAdminException("Error while fetching all page details", e);
		}
	}
	
	public Page getPage(long id) {
		
		int status = 0;
		String url = String.format(URLConstants.GET_HOST_BY_HOSTID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all page  details by page id.",id));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Page>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching all page details by page id", e);
			throw new CMSAdminException("Error while fetching all page details by page id", e);
		}
	}
	
	public Page Update(Page page) {
		
		if(page==null){
			throw new CMSAdminException(String.format("page cannot be null ",page.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.UPDATE_HOST_BY_HOSTID, page , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while update  page by page id.",page.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Page>() {
					});
		} catch (Exception e) {
			logger.error("Error while updatting host by page id", e);
			throw new CMSAdminException("Error while updatting page by host id", e);
		}
	}


}
