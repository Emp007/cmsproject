package com.cms.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.app.dao.EmployeeDao;
import com.cms.model.Employee;
@Service("employeeService")
public class EployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee save(Employee entity) {
		employeeDao.save(entity);
		return entity;
	}

	@Override
	public Employee update(long id, Employee entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Employee> getAll(){
		return employeeDao.findAll();
	}
	
	@Override
	public long count(){
		return employeeDao.count();
	}

}
