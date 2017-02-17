package com.cms.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cms.model.Employee;

public interface EmployeeDao extends MongoRepository<Employee, Long> {

}
