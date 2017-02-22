package com.cms.admin.service;

import java.util.List;
import com.cms.model.Menu;


public interface MenuService {

	public List<Menu> getAllMenuByHostId(long hostId);
	
	public List<Menu> getAllMenuByHostName(String  hostName);
	
	public List<Menu> getAllMenuByPageId(long  pageId);
	
	public List<Menu> getAllMenuByPageName(String  pageName);
	
	public List<Menu> getAllMenuByParentsId(long parentsId);
	
	public List<Menu> getAllMenuByChildId(long child);

	public List<Menu> getAllMenuByPatentsIdAndChildId(long parentsId, long child);
	
	public Menu save(Menu menu);
	
	public List<Menu> update(Menu menu);
	
	public List<Menu> getAllHostNameMenuName(String hostName ,String pageName);
	
	public List<Menu> getAllHostNameChildPage(String hostName ,String pageName);

}
