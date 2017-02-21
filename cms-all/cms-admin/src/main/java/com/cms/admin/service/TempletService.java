package com.cms.admin.service;

import com.cms.admin.CMSAdminException;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.URLConstants;
import com.cms.model.Host;
import com.cms.model.Page;
import com.cms.model.Templet;
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
public class TempletService {
	
	private static final Logger logger = LoggerFactory.getLogger(TempletService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Autowired
	private RestServiceUtil restServiceUtil;
	
	public List<Templet> getAllTemplets() {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.GET_ALL_TEMPLET_DETAILS, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all templet  details.",
								status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<List<Templet>>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching all templet details", e);
			throw new CMSAdminException("Error while fetching all templet details", e);
		}
	}
	
	public Templet getTemplet(long id) {
		
		int status = 0;
		String url = String.format(URLConstants.GET_TEMPLET_BY_TEMPLETID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all templet details by templet id.",id));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Templet>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching templet details by templet id", e);
			throw new CMSAdminException("Error while fetching templets details by templet id", e);
		}
	}
	
public Templet getTemplet(String templetName,long hostId) {
		
		int status = 0;
		String url = String.format(URLConstants.GET_TEMPLET_BY_TEMPLETNAME, templetName,hostId);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching templet details by templet name.",templetName));
			}
			if(response.get(CMSConstant.DATA).toString() != null){
				String data = response.get(CMSConstant.DATA).toString();
				return OBJECT_MAPPER.readValue(data, new TypeReference<Templet>() {});
			}
		} catch (Exception e) {
			logger.error("Error while fetching templet details by templet name");
			throw new CMSAdminException("Error while fetching templets details by templet name");
		}
		return null;
	}
	
public Templet Save(Templet templet) {
		
		if(templet==null){
			throw new CMSAdminException(String.format("Templet cannot be null ",templet.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.SAVE_TEMPLET_BY_TEMPLETID, templet , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while saving templet by templet id.",templet.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Templet>() {
					});
		} catch (Exception e) {
			logger.error("Error while saving templet by templet id", e);
			throw new CMSAdminException("Error while saving templet by templet id", e);
		}
	}
	
	public Templet Update(Templet templet) {
		
		if(templet==null){
			throw new CMSAdminException(String.format("Templet cannot be null ",templet.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.UPDATE_TEMPLET_BY_TEMPLETID, templet , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while update templet by templet id.",templet.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Templet>() {
					});
		} catch (Exception e) {
			logger.error("Error while updatting templet by templet id", e);
			throw new CMSAdminException("Error while updatting templet by templet id", e);
		}
	}
	
	public Page getPageCount(long id) {

		int status = 0;
		String url = String.format(URLConstants.PAGE_COUNT, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching page count ",
								status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<List<Page>>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching page count", e);
			throw new CMSAdminException("Error while fetching page count", e);
		}
	}
}
