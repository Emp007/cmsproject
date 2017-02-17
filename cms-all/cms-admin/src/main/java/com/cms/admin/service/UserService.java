package com.cms.admin.service;

import com.cms.admin.CMSAdminException;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.URLConstants;
import com.cms.model.User;
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
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Autowired
	private RestServiceUtil restServiceUtil;
	
	public List<User> getAllUsers() {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.GET_ALL_USER_DETAILS, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all user  details.",
								status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<List<User>>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching all users details", e);
			throw new CMSAdminException("Error while fetching all users details", e);
		}
	}
	
	public User getUser(long id) {
		
		int status = 0;
		String url = String.format(URLConstants.GET_USER_BY_USERID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all user  details by user id.",id));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<User>() {
					});
		} catch (Exception e) {
			logger.error("Error while fetching all users details by user id", e);
			throw new CMSAdminException("Error while fetching all users details by user id", e);
		}
	}
	
	public User Update(User user) {
		
		if(user==null){
			throw new CMSAdminException(String.format("User cannot be null ",user.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.UPDATE_USER_BY_USERID, user , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while update  user by user id.",user.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<User>() {
					});
		} catch (Exception e) {
			logger.error("Error while updatting user by user id", e);
			throw new CMSAdminException("Error while updatting user by user id", e);
		}
	}


}
