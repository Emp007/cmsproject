package com.cms.app.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cms.model.Templet;

public interface TempletDao extends MongoRepository<Templet, Long> {
	
	 @Query("{'host.$id' : ?0}")
	 public Templet findByTempletId(long id);
	 
	 @Query("{ 'templetName' : ?0 }")
	 public Templet findByName(String name);

	 public List<Templet> findByHostId(long id,Sort sort);
	 
	 public long countByHostId(long id);
	 
	 @Query("{'templetName' : ?0 , 'hostId' : ?1}")
	 public Templet getAllTemplateByTempletNameAndhostId(String templetName, long hostId);
}
