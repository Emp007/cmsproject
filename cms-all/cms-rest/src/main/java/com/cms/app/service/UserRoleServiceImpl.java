package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.cms.app.dao.UserRoleDao;
import com.cms.model.UserRole;

@Service("userRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	 @Autowired
	 MongoOperations mongoOperations;
	
	
	@Override
	public UserRole get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRole save(UserRole entity) {
				userRoleDao.save(entity);
				return entity;
	}

	@Override
	public UserRole update(long id, UserRole entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserRole> getAll() {
		return userRoleDao.findAll();
	}

	@Override
	public long count() {
		return userRoleDao.count();
	}
	
	public UserRole findByRoleId(long id){
		return userRoleDao.findByRoleId(id);
	}

	@Override
	public List<UserRole> findByUserId(long id) {
		return mongoOperations.find(query(where("user.$id").is(id)), UserRole.class,"user_role");
		
	}

}
