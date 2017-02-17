package com.cms.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cms.model.Container;

public interface ContainerDao extends MongoRepository<Container, Long> {
	
	 @Query("{ 'name' : ?0 }")
	 public Container findByName(String name);

	 /*public Host update(long id, Host entity);*/
}

