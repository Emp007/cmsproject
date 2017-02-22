package com.cms.admin.service;

import java.util.List;

import com.cms.model.Header;

public interface HeaderService {

	public List<Header> getAllHeaders();
	
	public Header getHeader(long id);
	
	public Header getHeader(String headerName,long hostId);
	
	public Header save(Header header);
	
	public Header update(Header header);

}