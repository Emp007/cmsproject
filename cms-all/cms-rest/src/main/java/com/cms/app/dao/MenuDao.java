package com.cms.app.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cms.model.Menu;

public interface MenuDao extends MongoRepository<Menu, Long> {
	
	 public List<Menu> getAllMenuByHostId(Long hostId);
	
	 public List<Menu> getAllMenuByhostName(String hostName);
	
     public List<Menu> getAllMenuByPageId(Long pageId);
	
	 public List<Menu> getAllMenuByPageName(String pageName);
	
	 public List<Menu> getAllMenuByParentsId(Long parentsId);

	 public List<Menu> getAllMenuByChildId(Long childId);
	 
	 @Query("{'parentsId' : ?0 , 'childId' : ?1}")
	 public List<Menu> getAllMenuByParentsIdAndChildId(Long parentsId ,Long childId);
	 
	 @Query("{'hostName' : ?0 , 'pageName' : ?1}")
	 public List<Menu> getAllMenuListByHostNamePageName(String hostName ,String pageName);
	 
	 @Query("{'hostName' : ?0 , 'childPageName' : ?1}")
	 public List<Menu> getAllMenuListByHostNameChildPageName(String hostName ,String childPageName);
	 
}