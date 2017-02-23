package com.cms.app.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cms.model.Page;

public interface PageDao extends MongoRepository<Page, Long> {

	@Query("{'host.$id' : ?0}")
	 public Page findByPageId(long id);
	
	 @Query("{ 'templetName' : ?0 }")
	 public Page findByName(String name);
	 
	 public List<Page> findByTempletId(long id,Sort sort);
	 
	 public List<Page> findByHostId(long id,Sort sort);
	 
	 public long countByHostId(long id);
	 
	 @Query("{'pageName' : ?0 , 'hostId' : ?1}")
	 public Page getAllPageByPageNameAndhostId(String pageName, long hostId);
}
