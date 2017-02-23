package com.cms.app.service;

import java.util.List;

import com.cms.model.Footer;

public interface FooterService extends CMSServcie<Footer> {

	public List<Footer> getAll();

	public long count();

	public Footer findByFooterId(long id);
	
	public Footer findByFooterName(String footerName,long hostId);
	
	public List<Footer> findByTempletId(long id);
	
	public List<Footer> findByHostId(long id);
	
    public long countByHostId(long id);

	public void delete(long id);
}
