package com.cms.app.service;

import java.util.List;

import com.cms.model.Templet;

public interface TempletService extends CMSServcie<Templet> {

    public List<Templet> getAll();
	
	public long count();
	
	public Templet findByTempletId(long id);
	
	public Templet findByTempletName(String templateName, long hostId);
	
	public List<Templet> findByHostId(long id);
	
	public long countByHostId(long id);
	
	public void delete(long id);
	
}
