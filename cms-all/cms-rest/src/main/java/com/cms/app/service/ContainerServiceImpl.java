package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.cms.app.dao.ContainerDao;
import com.cms.model.Container;


@Service("containerServiceImpl")
public class ContainerServiceImpl implements ContainerService {

	@Autowired
	private ContainerDao containerDao;
	
	 @Autowired
	 MongoOperations mongoOperations;
	
	
	@Override
	public Container get(long id) {
		// TODO Auto-generated method stub
		return containerDao.findOne(id);
	}

	@Override
	public Container save(Container entity) {
		containerDao.save(entity);
				return entity;
	}

	@Override
	public Container update(long id, Container entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Container> getAll() {
		return containerDao.findAll();
	}

	@Override
	public long count() {
		return containerDao.count();
	}
	
	@Override
	public Container findByTempletId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		containerDao.delete(id);
	}
}

