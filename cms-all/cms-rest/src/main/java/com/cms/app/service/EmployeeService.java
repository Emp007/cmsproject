package com.cms.app.service;

import java.util.List;

import com.cms.model.Employee;


public interface EmployeeService extends CMSServcie<Employee> {

	public List<Employee> getAll();
	
	public long count();

}
