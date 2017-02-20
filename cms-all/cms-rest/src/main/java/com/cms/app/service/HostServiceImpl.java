package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import com.cms.app.dao.HostDao;
import com.cms.model.Host;

@Service("hostServiceImpl")
public class HostServiceImpl implements HostService {

	@Autowired
	private HostDao hostDao;

	@Autowired
	MongoOperations mongoOperations;

	@Override
	public Host get(long id) {
		// TODO Auto-generated method stub
		return hostDao.findOne(id);
	}

	@Override
	public Host save(Host entity) {
		hostDao.save(entity);
		return entity;
	}

	@Override
	public Host update(long id, Host entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Host> getAll() {
		return hostDao.findAll(new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	public long count() {
		return hostDao.count()+1;
	}

	@Override
	public Host findByName(String name) {
		return hostDao.findByName(name);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		hostDao.delete(id);
	}
}
