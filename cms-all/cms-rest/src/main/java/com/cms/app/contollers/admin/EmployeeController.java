package com.cms.app.contollers.admin;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cms.app.dto.Response;
import com.cms.app.exception.CMSException;
import com.cms.app.service.EmployeeService;
import com.cms.model.Employee;

@RestController
@RequestMapping(value = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
	private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	@Qualifier("employeeService")
	private EmployeeService employeeService;
	
	  @RequestMapping(method = RequestMethod.POST)
	  public ResponseEntity<Response<Employee>> save(@RequestBody Employee employee) {
			try{
				LOGGER.debug("process start to save employee data");
				employee.setId(employeeService.count()+1);
				employeeService.save(employee);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save Employee data in employee controller with employee name :" + employee.getName(),e);
			}
	   	    return ResponseEntity.ok(new Response<Employee>(HttpStatus.OK.value(), "Employee saved successfully", employee));
	  }
	  
	  @RequestMapping(method = RequestMethod.GET)
	  public ResponseEntity<Response<List<Employee>>> getAll() {
		  List<Employee> employees;
			try{
				 LOGGER.debug("process start to filter all employee");
				employees  = employeeService.getAll();
			}catch(Exception e){
				throw new CMSException("Error find while save Employee data in employee controller with employee name :" ,e);
			}
	   	    return ResponseEntity.ok(new Response<List<Employee>>(HttpStatus.OK.value(), "Employee saved successfully", employees));
	  }

}
