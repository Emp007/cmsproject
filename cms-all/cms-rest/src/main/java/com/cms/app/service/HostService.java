package com.cms.app.service;

import java.util.List;

import com.cms.model.Host;

public interface HostService extends CMSServcie<Host> {

public List<Host> getAll();
	
	public long count();
	
	public Host findByName(String name);
	
	public void delete(long id);
	
}
