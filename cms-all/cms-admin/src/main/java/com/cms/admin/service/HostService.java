package com.cms.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cms.model.Host;

import com.cms.model.Templet;



public interface HostService {

	public void hostConfig(Host config);
	
    public List<Host> getAllHosts();
   
	public Host getHost(long id);
	
	public Host getHost(String hostName);
	
	public Host save(Host page);
	
	public Host update(Host page);
	
	void uploadDocument(MultipartFile multipartFile, long hostId);


	public List<Templet> getTempletsByHostId(long hostid);


}