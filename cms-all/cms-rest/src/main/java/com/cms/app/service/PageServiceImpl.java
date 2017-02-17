package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.cms.app.dao.PageDao;
import com.cms.model.Page;

@Service("pageServiceImpl")
public class PageServiceImpl implements PageService {

	@Autowired
	private PageDao pageDao;
	
	@Autowired
	MongoOperations mongoOperations;

	@Override
	public Page get(long id) {
		// TODO Auto-generated method stub
		return pageDao.findOne(id);
	}

	@Override
	public Page save(Page entity) {
		// TODO Auto-generated method stub
		pageDao.save(entity);
		return entity;
	}

	@Override
	public Page update(long id, Page entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Page> getAll() {
		// TODO Auto-generated method stub
		return pageDao.findAll(new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return pageDao.count()+1;
	}

	@Override
	public Page findByPageId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page findByPageName(String pageName,long hostId) {
		// TODO Auto-generated method stub
		return pageDao.getAllPageByPageNameAndhostId(pageName, hostId);	
	}
	
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		pageDao.delete(id);
	}

	@Override
	public List<Page> findByTempletId(long id) {
		// TODO Auto-generated method stub
		return pageDao.findByTempletId(id,new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	public List<Page> findByHostId(long id) {
		// TODO Auto-generated method stub
		return pageDao.findByHostId(id,new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	public long countByHostId(long id) {
		// TODO Auto-generated method stub
		return pageDao.countByHostId(id);
	}
	 
}
