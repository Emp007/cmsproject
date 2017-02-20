package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.cms.app.dao.HeaderDao;
import com.cms.model.Header;

@Service("headerServiceImpl")
public class HeaderServiceImpl implements HeaderService {

	@Autowired
	private HeaderDao headerDao;
	
	@Autowired
	MongoOperations mongoOperations;

	@Override
	public Header get(long id) {
		// TODO Auto-generated method stub
		return headerDao.findOne(id);
	}

	@Override
	public Header save(Header entity) {
		// TODO Auto-generated method stub
		headerDao.save(entity);
		return entity;
	}

	@Override
	public Header update(long id, Header entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Header> getAll() {
		// TODO Auto-generated method stub
		return headerDao.findAll();
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return headerDao.count();
	}

	@Override
	public Header findByHeaderId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Header findByHeaderName(String headerName,long hostId) {
		// TODO Auto-generated method stub
		return headerDao.getAllHeaderByHeaderNameAndhostId(headerName, hostId);	
	}
	
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		headerDao.delete(id);
	}

	@Override
	public List<Header> findByTempletId(long id) {
		// TODO Auto-generated method stub
		return headerDao.findByTempletId(id);
	}

	@Override
	public List<Header> findByHostId(long id) {
		// TODO Auto-generated method stub
		return headerDao.findByHostId(id);
	}

	@Override
	public long countByHostId(long id) {
		// TODO Auto-generated method stub
		return headerDao.countByHostId(id);
	}
	 
}
