package com.cms.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cms.model.UserLog;


/**
 * Created by manishsanger on 03/11/16.
 */
@Repository
public interface UserLogDao extends MongoRepository<UserLog, Long> {
}
