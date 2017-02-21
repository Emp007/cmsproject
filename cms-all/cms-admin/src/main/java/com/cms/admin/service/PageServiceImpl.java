package com.cms.admin.service;

import com.cms.admin.CMSAdminException;
import com.cms.admin.util.RestServiceUtil;
import com.cms.admin.util.CMSConstant;
import com.cms.admin.util.URLConstants;
import com.cms.model.Constants;
import com.cms.model.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pageService")
public class PageServiceImpl implements PageService {
	
	private static final Logger logger = LoggerFactory.getLogger(HostService.class);
	private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	@Autowired
	private RestServiceUtil restServiceUtil;
	
	private static final String PDF = "PDF";
	private static final String JPG = "JPG";
	private static final String JPEG = "JPEG";
	private static final String MP4 = "MP4";
	private static final String PNG = "PNG";
	private static final String GP = "3GP";
	
	public List<Page> getAllPages() {

		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.GET_ALL_PAGE_DETAILS, null, null, HttpMethod.GET);
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
	
	@Override
	public List<Page> getPagesByHostId(long id) {

		int status = 0;
		String url = String.format(URLConstants.GET_PAGE_BY_HOST_ID, id);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
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
		String url = String.format(URLConstants.GET_PAGE_BY_PAGE_ID, id);
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
	
	public Page getPage(String pageName, long hostId) {
		
		int status = 0;
		String url = String.format(URLConstants.GET_PAGE_BY_PAGE_NAME, pageName,hostId);
		try {
			JsonNode response = restServiceUtil.makeRequest(url, null, null, HttpMethod.GET);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(
						String.format("API not responded while fetching page details by page name.",pageName));
			}
			if(response.get(CMSConstant.DATA).toString() != null){
				String data = response.get(CMSConstant.DATA).toString();
				return OBJECT_MAPPER.readValue(data, new TypeReference<Page>() {});
			}
		} catch (Exception e) {
			logger.error("Error while fetching all page details by page name", e);
			throw new CMSAdminException("Error while fetching page details by page id", e);
		}
		return null;
	}
	
	public Page Save(Page page) {
		
		if(page==null){
			throw new CMSAdminException(String.format("page cannot be null ",page.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.SAVE_PAGE_BY_PAGE_ID, page , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while saving page by page id.",page.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Page>() {
					});
		} catch (Exception e) {
			logger.error("Error while saving page by page id", e);
			throw new CMSAdminException("Error while saving page by page id", e);
		}
	}
	
	public Page update(Page page) {
		
		if(page==null){
			throw new CMSAdminException(String.format("page cannot be null ",page.getId()));
		}
		int status = 0;
		try {
			JsonNode response = restServiceUtil.makeRequest(URLConstants.UPDATE_PAGE_BY_PAGE_ID, page , null, HttpMethod.POST);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
			if (status != 200) {
				throw new CMSAdminException(String.format("API not responded while saving page by page id.",page.getId()));
			}
			String data = response.get(CMSConstant.DATA).toString();
			return OBJECT_MAPPER.readValue(data,new TypeReference<Page>() {
					});
		} catch (Exception e) {
			logger.error("Error while saving page by page id", e);
			throw new CMSAdminException("Error while saving page by page id", e);
		}
	}
	
	@Override
	public void uploadDocument(MultipartFile multipartFile, long hostId) {

		if (multipartFile == null) {
			logger.info("Cannot upload empty file.");
			throw new IllegalArgumentException("Cannot upload empty file.");
		}

		if (hostId < 0) {
			logger.info("Invalid industry id: " + hostId);
			throw new IllegalArgumentException("Invalid industry id" );
		}
		String fileName = multipartFile.getOriginalFilename();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());

		if (!PDF.equalsIgnoreCase(extension)&& !JPG.equalsIgnoreCase(extension) && !JPEG.equalsIgnoreCase(extension) && !MP4.equalsIgnoreCase(extension)&& !PNG.equalsIgnoreCase(extension) && !GP.equalsIgnoreCase(extension)) {
			logger.error("Unknown file format, please upload pdf files");
			throw new IllegalArgumentException(
					"Unknown file format, please upload  files");
		}
		
		int status = 0;
		try {
			String pdfAsString = Base64.getEncoder().encodeToString(multipartFile.getBytes());
			Map<String, Object> payload = new HashMap<String, Object>();
			payload.put(Constants.ID, hostId);
			payload.put(Constants.DOCUMENT_CONTAINT, pdfAsString);
			payload.put(Constants.NAME, multipartFile.getOriginalFilename());
			payload.put(Constants.TYPE,extension );
			JsonNode response = restServiceUtil.makeRequest(URLConstants.UPLOAD_DOCUMENT, payload, null,HttpMethod.PUT);
			status = response.get(CMSConstant.STATUS_CODE).intValue();
		} catch (CMSAdminException | IOException e) {
			logger.error("Error while uploading pdf for industry with id");
			throw new CMSAdminException("Error while uploading pdf for industry with id");
		}

		if (status != 200) {
			throw new CMSAdminException("API responded with error code while updating macro trend.");
		}
	}
}