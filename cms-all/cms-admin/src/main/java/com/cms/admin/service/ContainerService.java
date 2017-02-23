package com.cms.admin.service;

import com.cms.admin.CMSAdminException;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.URLConstants;
import com.cms.model.Container;
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
public class ContainerService {

	private static final Logger logger = LoggerFactory.getLogger(ContainerService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Autowired
	private RestServiceUtil restServiceUtil;

	public List<Container> getAllContainer() {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.GET_ALL_CONTAINER_DETAILS, null, null,HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all container  details.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Container>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all container details", e);
			throw new CMSAdminException("Error while fetching all container details", e);
		}
	}

	public Container getContainer(long id) {

		int status = 0;
		String url = String.format(URLConstants.GET_CONTAINER_BY_CONTAINERID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all container  details by container id.", id));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<Container>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all container details by container id", e);
			throw new CMSAdminException("Error while fetching all containers details by container id", e);
		}
	}

	public Container Save(Container container) {

		if (container == null) {
			throw new CMSAdminException(String.format("Container cannot be null ", container.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.SAVE_CONTAINER_BY_CONTAINERID, container,
					null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while save container by container id.", container.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<Container>() {

			});
		} catch (Exception e) {
			logger.error("Error while container container by container id", e);
			throw new CMSAdminException("Error while saving container by container id", e);
		}
	}
	
	public Container Update(Container container) {

		if (container == null) {
			throw new CMSAdminException(String.format("Container cannot be null ", container.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.UPDATE_CONTAINER_BY_CONTAINERID, container,
					null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while update  container by container id.", container.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<Container>() {

			});
		} catch (Exception e) {
			logger.error("Error while updatting container by container id", e);
			throw new CMSAdminException("Error while updatting container by container id", e);
		}
	}

}
