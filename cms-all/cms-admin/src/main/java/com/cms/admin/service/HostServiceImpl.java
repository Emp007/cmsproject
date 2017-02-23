package com.cms.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cms.admin.CMSAdminException;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.URLConstants;
import com.cms.model.EditableInfo;
import com.cms.model.Host;
import com.cms.model.HostConfig;
import com.cms.model.Templet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("hostService")
public class HostServiceImpl implements HostService {

	private static final Logger logger = LoggerFactory.getLogger(HostService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Autowired
	private RestServiceUtil restServiceUtil;

	@Override
	public void hostConfig(Host hostConfig){
		if(hostConfig!=null){
			Host host = getHost(hostConfig.getId());
			HostConfig hostConfig2 = host.getHostConfig();
			EditableInfo editableInfo = null;
			if(hostConfig2 == null){
				editableInfo = new EditableInfo();
				editableInfo.setCreatedAt();
				editableInfo.setCreatedBy("User-Name");
				hostConfig2 = new HostConfig();
			}else{
				editableInfo = hostConfig2.getEditableInfo();
			}
			editableInfo.setUpdatedAt();
			editableInfo.setUpdatedBy("User-Name");
			
			hostConfig2.setEditableInfo(editableInfo);
			
			if((hostConfig.getHostConfig().getStatusName()).equals("active")){
				hostConfig2.setActive(true);
				hostConfig2.setActiveAt(System.currentTimeMillis());
				hostConfig2.setWelcomePage(hostConfig.getHostConfig().getWelcomePage());
			}else{
				hostConfig2.setActive(false);
				hostConfig2.setInactiveAt(System.currentTimeMillis());
			}
			hostConfig2.setActiveInactivePeriod(1000L);
			
			host.setHostConfig(hostConfig2);
			update(host);
		}
	}
	@Override
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

	@Override
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
	
	@Override
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

	@Override
	public Host save(Host host) {
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

	@Override
	public Host update(Host host) {
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
	
	

	@Override
	public void uploadDocument(MultipartFile multipartFile, long hostId) {
		// TODO Auto-generated method stub
		
	}

	public List<Templet> getTempletsByHostId(long hostid) {

		int status = 0;
		String url = String.format(URLConstants.GET_TEMPLET_BY_HOSTID, hostid);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all templet details by host id.", hostid));
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
