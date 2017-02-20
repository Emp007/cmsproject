package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.app.dao.RoleDao;
import com.cms.app.util.DateTimeUtil;
import com.cms.model.Role;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
    @Autowired
    private DateTimeUtil dateTimeUtil;

	@Override
	public Role get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role save(Role entity) {
		setDateTimeWithUserName(entity);
		roleDao.save(entity);
		return entity;
	}

	@Override
	public Role update(long id, Role entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAll() {
			return roleDao.findAll();
	}

	@Override
	public long count() {
		return roleDao.count();
	}
	
	public void setDateTimeWithUserName(Role role){
		  
		role.setCreatedDate(dateTimeUtil.currentDateTime());
		role.setUpdatedDate(dateTimeUtil.currentDateTime());
		role.setCreatedBy("virendra");
		role.setUpdatedBy("Virendra");
	  }
}
