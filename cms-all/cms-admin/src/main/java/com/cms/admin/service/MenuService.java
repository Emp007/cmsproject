package com.cms.admin.service;

import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import com.cms.admin.CMSAdminException;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.URLConstants;
import com.cms.model.Menu;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Autowired
	private RestServiceUtil restServiceUtil;

	public List<Menu> getAllMenuByHostId(long id) {

		int status = 0;
		String url = String.format(URLConstants.GET_ALL_MENU_BY_HOST_ID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all Menu  details.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all menu details by host id", e);
			throw new CMSAdminException("Error while fetching all menu details by host id", e);
		}
	}

	public List<Menu> getAllMenuByHostName(String hostName) {

		int status = 0;
		String url = String.format(URLConstants.GET_ALL_MENU_BY_HOST_ID, hostName);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all Menu  details by host name.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all menu details by host name", e);
			throw new CMSAdminException("Error while fetching all menu details by host name", e);
		}
	}

	public List<Menu> getAllMenuByPageId(long pageId) {

		int status = 0;
		String url = String.format(URLConstants.GET_ALL_MENU_BY_PAGE_ID, pageId);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all Menu  details by page id.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all menu details by page id", e);
			throw new CMSAdminException("Error while fetching all menu details by page id", e);
		}
	}

	public List<Menu> getAllMenuByPageName(String pageName) {

		int status = 0;
		String url = String.format(URLConstants.GET_ALL_MENU_BY_PAGE_NAME, pageName);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all Menu  details by page id.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all menu details by page id", e);
			throw new CMSAdminException("Error while fetching all menu details by page id", e);
		}
	}

	public List<Menu> getAllMenuByParentsId(long parentsId) {

		int status = 0;
		String url = String.format(URLConstants.GET_ALL_MENU_BY_PARENTS_ID, parentsId);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all Menu  details by page id.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all menu details by page id", e);
			throw new CMSAdminException("Error while fetching all menu details by page id", e);
		}
	}

	public List<Menu> getAllMenuByChildId(long child) {

		int status = 0;
		String url = String.format(URLConstants.GET_ALL_MENU_BY_CHILD_ID, child);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all Menu  details by page id.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all menu details by page id", e);
			throw new CMSAdminException("Error while fetching all menu details by page id", e);
		}
	}

	public List<Menu> getAllMenuByPatentsIdAndChildId(long parentsId, long child) {

		int status = 0;
		String url = String.format(URLConstants.GET_ALL_MENU_BY_PARENTS_ID_AND_CHILD_ID, parentsId, child);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching all Menu  details by page id.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while fetching all menu details by page id", e);
			throw new CMSAdminException("Error while fetching all menu details by page id", e);
		}
	}

	public Menu save(Menu menu) {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.SAVE_MENU, menu, null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while save menu details.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<Menu>() {
			});
		} catch (Exception e) {
			logger.error("Error while saving menu details", e);
			throw new CMSAdminException("Error while saving menu details", e);
		}
	}

	public List<Menu> update(Menu menu) {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.SAVE_MENU, menu, null, HttpMethod.PUT);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while update menu details.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while saving menu details", e);
			throw new CMSAdminException("Error while updating menu details", e);
		}
	}
	
	public List<Menu> getAllHostNameMenuName(String hostName ,String pageName) {

		int status = 0;
		try {
			
			//String url = "http://example.com/query?q="  URLEncoder.encode(pageName, "UTF-8");
			String url = String.format(URLConstants.MENULIST, URLEncoder.encode(hostName, "UTF-8") ,URLEncoder.encode(pageName, "UTF-8"));
			JsonNode response = restServiceUtil.makeRequest(url,null,null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while update menu details.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while saving menu details", e);
			throw new CMSAdminException("Error while updating menu details", e);
		}
	}
	
	public List<Menu> getAllHostNameChildPage(String hostName ,String pageName) {

		int status = 0;
		try {
			String url = String.format(URLConstants.MENU_LIST_BY_CHILDPAGE, hostName ,pageName);
			JsonNode response = restServiceUtil.makeRequest(url,null,null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while update menu details.", status));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data, new TypeReference<List<Menu>>() {
			});
		} catch (Exception e) {
			logger.error("Error while saving menu details", e);
			throw new CMSAdminException("Error while updating menu details", e);
		}
	}

}
