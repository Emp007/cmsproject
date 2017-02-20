package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.cms.app.dao.TempletDao;
import com.cms.model.Templet;
import com.google.gson.Gson;

@Service("templetServiceImpl")
public class TempletServiceImpl implements TempletService {

	@Autowired
	private TempletDao templetDao;
	
	 @Autowired
	 MongoOperations mongoOperations;
	
	
	@Override
	public Templet get(long id) {
		// TODO Auto-generated method stub
		return templetDao.findOne(id);
	}

	@Override
	public Templet save(Templet entity) {
		templetDao.save(entity);
				return entity;
	}

	@Override
	public Templet update(long id, Templet entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Templet> getAll() {
		return templetDao.findAll(new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	public long count() {
		return templetDao.count()+1;
	}
	
	@Override
	public Templet findByTempletId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Templet findByTempletName(String templateName, long hostId) {
		
		Query query2 = new Query();
		query2.addCriteria(Criteria.where("templetName").is(templateName).and("hostId").is(hostId));
		//System.out.println(new Gson().toJson(templetDao.getAllTemplateByTempletNameAndhostId(templateName, hostId)));
		System.out.println(new Gson().toJson(mongoOperations.findOne(query2, Templet.class)));
		
		//return mongoOperations.findOne(query2, Templet.class);
		
		return templetDao.getAllTemplateByTempletNameAndhostId(templateName, hostId); 
	}
	
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		templetDao.delete(id);
	}

	@Override
	public List<Templet> findByHostId(long id) {
		// TODO Auto-generated method stub
		return templetDao.findByHostId(id,new Sort(Sort.Direction.DESC, "id"));
	}

	@Override
	public long countByHostId(long id) {
		// TODO Auto-generated method stub
		return templetDao.countByHostId(id);
	}
}

