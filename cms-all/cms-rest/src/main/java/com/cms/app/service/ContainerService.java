package com.cms.app.service;

import java.util.List;

import com.cms.model.Container;

public interface ContainerService extends CMSServcie<Container> {

    public List<Container> getAll();
	
	public long count();
	
	public Container findByTempletId(long id);
	
	public void delete(long id);
	
}
