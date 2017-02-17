package com.cms.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cms.model.UserRole;
//import org.springframework.data.mongodb.repository.Query;

public interface UserRoleDao extends MongoRepository<UserRole, Long> {
	
	 @Query("{'role.$id' : ?0}")
	 public UserRole findByRoleId(long id);

	
}
