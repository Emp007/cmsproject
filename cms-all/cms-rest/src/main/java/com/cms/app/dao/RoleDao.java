package com.cms.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cms.model.Role;

public interface RoleDao extends MongoRepository<Role, Long> {

}
