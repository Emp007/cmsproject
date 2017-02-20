package com.cms.admin.service;

import com.cms.admin.CMSAdminException;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.URLConstants;
import com.cms.model.Header;
import com.cms.model.Templet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("headerService")
public  class HeaderServiceImpl implements HeaderService {
	
	private static final Logger logger = LoggerFactory.getLogger(HostService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Autowired
	private RestServiceUtil restServiceUtil;
	
	@Override
	public List<Header> getAllHeaders() {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.GET_ALL_HEADER_DETAILS, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all header  details.",
								status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<List<Header>>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching all header details", e);
			throw new CMSAdminException("Error while fetching all header details", e);
		}
	}
	
	@Override
	public Header getHeader(long id) {
		
		int status = 0;
		String url = String.format(URLConstants.GET_HEADER_BY_HEADER_ID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all header  details by header id.",id));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Header>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching all header details by header id", e);
			throw new CMSAdminException("Error while fetching all header details by header id", e);
		}
	}

	@Override
	public Header getHeader(String headerName, long hostId) {
		
		int status = 0;
		String url = String.format(URLConstants.GET_HEADER_BY_HEADER_NAME, headerName,hostId);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching header details by header name.",headerName));
			}
			if(response.get(CMSConstant.DATA).toString() != null){
				String data = response.get(CMSConstant.DATA).toString();
				return OBJECT_MAPPER.readValue(data, new TypeReference<Header>() {});
			}
		} catch (Exception e) {
			logger.error("Error while fetching all header details by header name", e);
			throw new CMSAdminException("Error while fetching header details by header id", e);
		}
		return null;
	}
	
	@Override
	public Header Save(Header header) {
		
		if(header==null){
			throw new CMSAdminException(String.format("header cannot be null ",header.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.SAVE_HEADER_BY_HEADER_ID, header , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while saving header by header id.",header.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Header>() {
					});
		} catch (Exception e) {
			logger.error("Error while saving header by header id", e);
			throw new CMSAdminException("Error while saving header by header id", e);
		}
	}
	
	@Override
	public Header update(Header header) {
		
		if(header==null){
			throw new CMSAdminException(String.format("header cannot be null ",header.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.UPDATE_HEADER_BY_HEADER_ID, header , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while saving header by header id.",header.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Header>() {
					});
		} catch (Exception e) {
			logger.error("Error while saving header by header id", e);
			throw new CMSAdminException("Error while saving header by header id", e);
		}
	}
}