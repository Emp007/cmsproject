package com.cms.app.service;

import java.util.List;

import com.cms.model.Header;

public interface HeaderService extends CMSServcie<Header> {

	public List<Header> getAll();

	public long count();

	public Header findByHeaderId(long id);
	
	public Header findByHeaderName(String headerName,long hostId);
	
	public List<Header> findByTempletId(long id);
	
	public List<Header> findByHostId(long id);
	
    public long countByHostId(long id);

	public void delete(long id);
}
