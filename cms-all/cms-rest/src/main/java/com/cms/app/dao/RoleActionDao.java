package com.cms.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cms.model.RoleAction;

public interface RoleActionDao extends MongoRepository<RoleAction, Long> {

}
