package com.cms.admin.service;

import com.cms.admin.CMSAdminException;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.URLConstants;
import com.cms.model.Footer;
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

@Service("footerService")
public  class FooterServiceImpl implements FooterService {
	
	private static final Logger logger = LoggerFactory.getLogger(HostService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Autowired
	private RestServiceUtil restServiceUtil;
	
	@Override
	public List<Footer> getAllFooters() {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.GET_ALL_FOOTER_DETAILS, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all footer  details.",
								status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<List<Footer>>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching all footer details", e);
			throw new CMSAdminException("Error while fetching all footer details", e);
		}
	}
	
	@Override
	public Footer getFooter(long id) {
		
		int status = 0;
		String url = String.format(URLConstants.GET_FOOTER_BY_FOOTER_ID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all footer  details by footer id.",id));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Footer>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching all footer details by footer id", e);
			throw new CMSAdminException("Error while fetching all footer details by footer id", e);
		}
	}
	

	@Override	
public Footer getFooter(String pageName, long hostId) {
		
		int status = 0;
		String url = String.format(URLConstants.GET_FOOTER_BY_FOOTER_NAME, pageName,hostId);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching footer details by footer name.",pageName));
			}
			if(response.get(CMSConstant.DATA).toString() != null){
				String data = response.get(CMSConstant.DATA).toString();
				return OBJECT_MAPPER.readValue(data, new TypeReference<Footer>() {});
			}
		} catch (Exception e) {
			logger.error("Error while fetching all footer details by footer name", e);
			throw new CMSAdminException("Error while fetching footer details by footer id", e);
		}
		return null;
	}
	
	@Override
	public Footer Save(Footer footer) {
		
		if(footer==null){
			throw new CMSAdminException(String.format("footer cannot be null ",footer.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.SAVE_FOOTER_BY_FOOTER_ID, footer , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while saving footer by footer id.",footer.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Footer>() {
					});
		} catch (Exception e) {
			logger.error("Error while saving footer by footer id", e);
			throw new CMSAdminException("Error while saving footer by footer id", e);
		}
	}
	
	@Override
	public Footer update(Footer footer) {
		
		if(footer==null){
			throw new CMSAdminException(String.format("footer cannot be null ",footer.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.UPDATE_FOOTER_BY_FOOTER_ID, footer , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while saving footer by footer id.",footer.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Footer>() {
					});
		} catch (Exception e) {
			logger.error("Error while saving footer by footer id", e);
			throw new CMSAdminException("Error while saving footer by footer id", e);
		}
	}

	
	
}