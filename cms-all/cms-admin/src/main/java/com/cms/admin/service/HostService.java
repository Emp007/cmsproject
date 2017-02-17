package com.cms.admin.service;

import com.cms.admin.CMSAdminException;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.URLConstants;
import com.cms.model.Host;
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
public class HostService {

	private static final Logger logger = LoggerFactory.getLogger(HostService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Autowired
	private RestServiceUtil restServiceUtil;

	public List<Host> getAllHosts() {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.GET_ALL_HOST_DETAILS, null, null,
					HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all host  details.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Host>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all host details", e);
			throw new CMSAdminException("Error while fetching all hosts details", e);
		}
	}

	public Host getHost(long id) {

		int status = 0;
		String url = String.format(URLConstants.GET_HOST_BY_HOSTID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all host  details by host id.", id));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<Host>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all host details by host id", e);
			throw new CMSAdminException("Error while fetching all hosts details by host id", e);
		}
	}
	
	public Host getHost(String hostname) {

		int status = 0;
		String url = String.format(URLConstants.GET_HOST_BY_HOSTNAME, hostname);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching host  details by host name.", hostname));
			}
			
			if(response.get(CMSConstant.DATA).toString() != null){
				String data = response.get(CMSConstant.DATA).toString();
				return OBJECT_MAPPER.readValue(data, new TypeReference<Host>() {});
			}
		} catch (Exception e) {
			logger.error("Error while fetching host details by host name");
			throw new CMSAdminException("Error while fetching hosts details by host name");
		}
		return null;
	}

	public Host Save(Host host) {

		if (host == null) {
			throw new CMSAdminException(String.format("Host cannot be null ", host.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.SAVE_HOST_BY_HOSTID, host, null,
					HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while save host by host id.", host.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<Host>() {
			});
		} catch (Exception e) {
			logger.error("Error while saving host by host id", e);
			throw new CMSAdminException("Error while saving host by host id", e);
		}
	}

	public Host Update(Host host) {

		if (host == null) {
			throw new CMSAdminException(String.format("Host cannot be null ", host.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.UPDATE_HOST_BY_HOSTID, host, null,
					HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while update  host by host id.", host.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<Host>() {
			});
		} catch (Exception e) {
			logger.error("Error while updatting host by host id", e);
			throw new CMSAdminException("Error while updatting host by host id", e);
		}
	}
	
	public List<Templet> getTempletsByHostId(long id) {

		int status = 0;
		String url = String.format(URLConstants.GET_TEMPLET_BY_HOSTID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all templet details by host id.", id));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Templet>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all templet details by host id", e);
			throw new CMSAdminException("Error while fetching all templet details by host id", e);
		}
	}

}
