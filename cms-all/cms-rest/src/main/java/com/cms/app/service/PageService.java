package com.cms.app.service;

import java.util.List;

import com.cms.model.Page;

public interface PageService extends CMSServcie<Page> {

	public List<Page> getAll();

	public long count();

	public Page findByPageId(long id);
	
	public Page findByPageName(String pageName,long hostId);
	
	public List<Page> findByTempletId(long id);
	
	public List<Page> findByHostId(long id);
	
    public long countByHostId(long id);

	public void delete(long id);
}
