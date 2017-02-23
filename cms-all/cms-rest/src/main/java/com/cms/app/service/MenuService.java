package com.cms.app.service;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;

import com.cms.model.Menu;

public interface MenuService extends CMSServcie<Menu> {
	
    public List<Menu> getAllMenuByHostId(Long hostId);
	
    public List<Menu> getAllMenuByHostName(String hostName);
    
    public List<Menu> getAllMenuByPageId(Long pageId);
 	
    public List<Menu> getAllMenuByPageName(String pageName);
    
    public List<Menu> getAllMenuByParentsId(Long parentsId);
	
	public List<Menu> getAllMenuByParendtsId(Long parentsId);
	
    public List<Menu> getAllMenuByChildId(Long childId);
    
    public List<Menu> getAllMenuByParentsIdAndChildId(Long parentsId ,Long childId);
    
	 public List<Menu> getAllMenuListByHostNamePageName(String hostName ,String pageName);
	 
	 public List<Menu> getAllMenuListByHostNameChildPageName(String hostName ,String childPageName);
	 
    public long count();

}