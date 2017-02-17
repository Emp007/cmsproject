package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.app.dao.ActionDao;
import com.cms.app.util.DateTimeUtil;
import com.cms.model.Action;

@Service("actionServiceImpl")
public class ActionServiceImpl implements ActionService {
	
	@Autowired
	private ActionDao actionDao;
	
    @Autowired
	private DateTimeUtil dateTimeUtil;

	@Override
	public Action get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action save(Action entity) {
		setDateTimeWithUserName(entity);
		actionDao.save(entity);
		return entity;
	}

	@Override
	public Action update(long id, Action entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> getAll() {
		return actionDao.findAll();
	}

	@Override
	public long count() {
		return actionDao.count();
	}
	
	public void setDateTimeWithUserName(Action action){
		  
		action.setCreatedDate(dateTimeUtil.currentDateTime());
		action.setUpdatedDate(dateTimeUtil.currentDateTime());
		action.setCreatedBy("virendra");
		action.setUpdatedBy("Virendra");
	  }
	

}
