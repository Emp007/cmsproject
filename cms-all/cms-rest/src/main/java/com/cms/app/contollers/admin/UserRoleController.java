package com.cms.app.contollers.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cms.app.dto.Response;
import com.cms.app.exception.CMSException;
import com.cms.app.service.UserRoleService;
import com.cms.model.UserRole;

@RestController
@RequestMapping(value = "user_role", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRoleController {
private final static Logger LOGGER = LoggerFactory.getLogger(UserRoleController.class);
	
	@Autowired
	@Qualifier("userRoleServiceImpl")
	private UserRoleService useRoleService;
	

	  @RequestMapping(method = RequestMethod.POST)
	  public ResponseEntity<Response<UserRole>> save(@RequestBody UserRole userRole) {
			try{
				LOGGER.debug("Process start to save UserRole data");
				userRole.setId(useRoleService.count());
				useRoleService.save(userRole);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save UserRole data in UserRolecontroller with usre name :" ,e);
			}
	   	    return ResponseEntity.ok(new Response<UserRole>(HttpStatus.OK.value(), "User saved successfully", userRole));
	  }
	  
	  @RequestMapping(method = RequestMethod.GET)
	  public ResponseEntity<Response<List<UserRole>>> getAll() { 
		  
		  List<UserRole> userRoles;
		  try{
				LOGGER.debug("Process start to filter employee data");
				userRoles = useRoleService.getAll();
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while filter User data in UserRolecontroller :"  ,e);
			}
			  return ResponseEntity.ok(new Response<List<UserRole>>(HttpStatus.OK.value(), "UserRole saved successfully", userRoles));
	  }
	  
	  @RequestMapping(value="role/{roleId}" ,method = RequestMethod.GET)
	  public ResponseEntity<Response<UserRole>> getByRoleId(@PathVariable("roleId") long id) { 
		  
		  UserRole userRoles;
		  try{
				LOGGER.debug("Process start to filter UserRole Id data");
				userRoles = useRoleService.findByRoleId(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while filter UserRole data by UserRolecontroller by role if :"+id  ,e);
			}
			  return ResponseEntity.ok(new Response<UserRole>(HttpStatus.OK.value(), "UserRole filter successfully", userRoles));
	  }
	  
	  @RequestMapping(value="user/{userId}" ,method = RequestMethod.GET)
	  public ResponseEntity<Response<List<UserRole>>> getByUserId(@PathVariable("userId") long id) { 
		  
		  List<UserRole> userRoles;
		  try{
				LOGGER.debug("Process start to filter UserRole Id data");
				userRoles = useRoleService.findByUserId(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while filter UserRole data by UserRolecontroller by role if :"+id  ,e);
			}
			  return ResponseEntity.ok(new Response<List<UserRole>>(HttpStatus.OK.value(), "UserRole filter successfully", userRoles));
	  }
	  
	  
	  

}
