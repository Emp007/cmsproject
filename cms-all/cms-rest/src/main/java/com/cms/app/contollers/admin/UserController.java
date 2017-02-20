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
import com.cms.app.service.UserDetailServiceImpl;
import com.cms.model.User;

@RestController
@RequestMapping(value = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	@Qualifier("userDetailServiceImpl")
	private UserDetailServiceImpl userDetailServiceImpl;
	
	  @RequestMapping(method = RequestMethod.POST)
	  public ResponseEntity<Response<User>> save(@RequestBody User user) {
			try{
				LOGGER.debug("Process start to save User data");
				user.setId(userDetailServiceImpl.count());
				userDetailServiceImpl.save(user);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save User data in Usercontroller with usre name :" + user.getName(),e);
			}
	   	    return ResponseEntity.ok(new Response<User>(HttpStatus.OK.value(), "User saved successfully", user));
	  }
	  
	  @RequestMapping(method = RequestMethod.GET)
	  public ResponseEntity<Response<List<User>>> getAll() { 
		  
		  List<User> users;
		  try{
				LOGGER.debug("Process start to save employee data");
				users = userDetailServiceImpl.getAllUsers();
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save User data in Usercontroller with User name :"  ,e);
			}
			  return ResponseEntity.ok(new Response<List<User>>(HttpStatus.OK.value(), "User saved successfully", users));
	 
	  }
}
