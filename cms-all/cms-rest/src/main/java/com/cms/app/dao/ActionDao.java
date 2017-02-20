package com.cms.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cms.model.Action;

public interface ActionDao extends MongoRepository<Action, Long> {

}
