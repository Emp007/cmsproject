package com.cms.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cms.model.Host;

public interface HostDao extends MongoRepository<Host, Long> {
	
	 @Query("{ 'hostName' : ?0 }")
	 public Host findByName(String name);

}

