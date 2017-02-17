package com.cms.app.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cms.model.Header;
import com.cms.model.Templet;

public interface HeaderDao extends MongoRepository<Header, Long> {

	@Query("{'host.$id' : ?0}")
	 public Header findByHeaderId(long id);
	
	 @Query("{ 'templetName' : ?0 }")
	 public Header findByName(String name);
	 
	 public List<Header> findByTempletId(long id);
	 
	 public List<Header> findByHostId(long id);
	 
	 public long countByHostId(long id);
	 
	 @Query("{'headerName' : ?0 , 'hostId' : ?1}")
	 public Header getAllHeaderByHeaderNameAndhostId(String headerName, long hostId);
}
