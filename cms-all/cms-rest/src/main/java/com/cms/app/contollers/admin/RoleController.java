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
import com.cms.app.service.RoleService;
import com.cms.model.Role;

@RestController
@RequestMapping(value = "role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	@Qualifier("roleServiceImpl")
	private RoleService roleService;
	

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<Response<Role>> save(@RequestBody Role role) {
		try{
			LOGGER.debug("Process start to save Role data");
			role.setId(roleService.count());
			roleService.save(role);
		}catch(Exception e){
			e.printStackTrace();
			throw new CMSException("Error find while save Role data in RoleController with usre roleName :" + role.getRoleName(),e);
		}
   	    return ResponseEntity.ok(new Response<Role>(HttpStatus.OK.value(), "Role saved successfully", role));
  }
	  
	  @RequestMapping(method = RequestMethod.GET)
	  public ResponseEntity<Response<List<Role>>> getAll() { 
		  
		  List<Role> roles;
		  try{
				LOGGER.debug("Process start to filter role data");
				roles = roleService.getAll();
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while filter Role data in Rolecontroller with role name :"  ,e);
			}
			  return ResponseEntity.ok(new Response<List<Role>>(HttpStatus.OK.value(), "User saved successfully", roles));
	 
	  }
	  

}
