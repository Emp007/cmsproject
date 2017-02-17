package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.cms.app.dao.FooterDao;
import com.cms.model.Footer;

@Service("footerServiceImpl")
public class FooterServiceImpl implements FooterService {

	@Autowired
	private FooterDao footerDao;
	
	@Autowired
	MongoOperations mongoOperations;

	@Override
	public Footer get(long id) {
		// TODO Auto-generated method stub
		return footerDao.findOne(id);
	}

	@Override
	public Footer save(Footer entity) {
		// TODO Auto-generated method stub
		footerDao.save(entity);
		return entity;
	}

	@Override
	public Footer update(long id, Footer entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Footer> getAll() {
		// TODO Auto-generated method stub
		return footerDao.findAll();
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return footerDao.count();
	}

	@Override
	public Footer findByFooterId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Footer findByFooterName(String footerName,long hostId) {
		// TODO Auto-generated method stub
		return footerDao.getAllFooterByFooterNameAndhostId(footerName, hostId);	
	}
	
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		footerDao.delete(id);
	}

	@Override
	public List<Footer> findByTempletId(long id) {
		// TODO Auto-generated method stub
		return footerDao.findByTempletId(id);
	}

	@Override
	public List<Footer> findByHostId(long id) {
		// TODO Auto-generated method stub
		return footerDao.findByHostId(id);
	}

	@Override
	public long countByHostId(long id) {
		// TODO Auto-generated method stub
		return footerDao.countByHostId(id);
	}
	 
}
