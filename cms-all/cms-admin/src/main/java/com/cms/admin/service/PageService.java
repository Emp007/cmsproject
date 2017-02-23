package com.cms.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cms.model.Meta;

import com.cms.model.Page;

public interface PageService {

	public List<Page> getAllPages();
	
	public Page getPage(long id);
	
	public Page getPage(String pageName,long hostId);
	
	public Page Save(Page page);
	public Meta Save(Meta meta);


	
	public Page update(Page page);
	
	void uploadDocument(MultipartFile multipartFile, long hostId);

	public List<Page> getPagesByHostId(long id);
	
	public Page getPageCount(long id);
}