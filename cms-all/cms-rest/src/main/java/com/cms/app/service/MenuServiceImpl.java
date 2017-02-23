package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cms.app.dao.MenuDao;
import com.cms.app.util.DateTimeUtil;
import com.cms.model.Menu;
import com.google.gson.Gson;

@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDao  menuDao;
	
	 @Autowired
	 private DateTimeUtil dateTimeUtil;
	
	@Override
	public Menu get(long id) {
		return menuDao.findOne(id);
	}

	@Override
	public Menu save(Menu entity) {
		setDateTimeWithUserName(entity);
		menuDao.save(entity);
		return entity;
	}

	@Override
	public Menu update(long id, Menu entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Menu> getAllMenuByHostId(Long hostId) {
		return menuDao.getAllMenuByHostId(hostId);
	}

	@Override
	public List<Menu> getAllMenuByHostName(String hostName) {
		return menuDao.getAllMenuByhostName(hostName);
	}
	
	@Override
	public List<Menu> getAllMenuByPageId(Long pageId) {
		return menuDao.getAllMenuByPageId(pageId);
	}
	
	@Override
	public List<Menu> getAllMenuByPageName(String pageName) {
		return menuDao.getAllMenuByPageName(pageName);
	}
	
	@Override
	public List<Menu> getAllMenuByParentsId(Long parentsId) {
		return menuDao.getAllMenuByParentsId(parentsId);
		
	}
	
	@Override
	public List<Menu> getAllMenuByChildId(Long childId) {
		return menuDao.getAllMenuByChildId(childId);
	}
	
	@Override
	public List<Menu> getAllMenuByParentsIdAndChildId(Long parentsId, Long childId) {
		return menuDao.getAllMenuByParentsIdAndChildId(parentsId,childId);
	}
	
	@Override
	public long count() {
		return menuDao.count()+1;
	}

	public void setDateTimeWithUserName(Menu entity){
		  entity.setCreatedDate(dateTimeUtil.currentDateTime());
		  entity.setUpdatedDate(dateTimeUtil.currentDateTime());
		  entity.setCreatedBy("virendra");
		  entity.setUpdatedBy("Virendra");
	  }

	@Override
	public List<Menu> getAllMenuByParendtsId(Long parentsId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Menu> getAllMenuListByHostNamePageName(String hostName ,String pageName){
			return menuDao.getAllMenuListByHostNamePageName(hostName ,pageName);
		
	}
	 
	 public List<Menu> getAllMenuListByHostNameChildPageName(String hostName ,String childPageName){
		 return menuDao.getAllMenuListByHostNameChildPageName(hostName ,childPageName);
	 }

	
}