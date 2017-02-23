package com.cms.admin.service;

import java.util.List;

import com.cms.model.Footer;

public interface FooterService {

	public List<Footer> getAllFooters();
	
	public Footer getFooter(long id);
	
	public Footer getFooter(String footerName,long hostId);
	
	public Footer save(Footer footer);
	
	public Footer update(Footer footer);

}