package com.cms.admin.service;
import com.cms.model.Templet;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface TemplateService {
	
    public List<Templet> getAllTemplets();
	
	public Templet getTemplet(long id);
	
	public Templet getTemplet(String pageName,long hostId);
	
	public Templet save(Templet page);
	
	public Templet update(Templet page);
	
	void uploadDocument(MultipartFile multipartFile, long hostId);

	public List<Templet> getTempletsByHostId(long id);
}
