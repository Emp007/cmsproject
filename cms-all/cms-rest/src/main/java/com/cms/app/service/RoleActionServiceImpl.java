package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.app.dao.RoleActionDao;
import com.cms.model.RoleAction;

@Service("roleActionServiceImpl")
public class RoleActionServiceImpl implements RoleActionService{

	@Autowired
	private RoleActionDao  roleActionDao;
	
	@Override
	public RoleAction get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleAction save(RoleAction entity) {
		roleActionDao.save(entity);
		return entity;
	}

	@Override
	public RoleAction update(long id, RoleAction entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleAction> getAll() {
		return roleActionDao.findAll();
	}

	@Override
	public long count() {
		return roleActionDao.count();
	}

}
