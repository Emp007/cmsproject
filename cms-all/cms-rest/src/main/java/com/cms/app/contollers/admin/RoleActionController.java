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
import com.cms.app.service.RoleActionService;
import com.cms.model.RoleAction;

@RestController
@RequestMapping(value = "role-action", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleActionController {
	private final static Logger LOGGER = LoggerFactory.getLogger(RoleActionController.class);
	
	@Autowired
	@Qualifier("roleActionServiceImpl")
	private RoleActionService roleActionService;
	
	@RequestMapping(method = RequestMethod.POST)
	  public ResponseEntity<Response<RoleAction>> save(@RequestBody RoleAction roleAction) {
			try{
				LOGGER.debug("Process start to RoleAction data");
				roleAction.setId(roleActionService.count());
				roleActionService.save(roleAction);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save RoleAction data in RoleActionController:" ,e);
			}
	   	    return ResponseEntity.ok(new Response<RoleAction>(HttpStatus.OK.value(), "Role saved successfully", roleAction));
	  }
		  
		  @RequestMapping(method = RequestMethod.GET)
		  public ResponseEntity<Response<List<RoleAction>>> getAll() { 
			  
			  List<RoleAction> roleActions;
			  try{
					LOGGER.debug("Process start to filter role data");
					roleActions = roleActionService.getAll();
				}catch(Exception e){
					e.printStackTrace();
					throw new CMSException("Error find while filter RoleAction data in RoleActioncontroller :"  ,e);
				}
				  return ResponseEntity.ok(new Response<List<RoleAction>>(HttpStatus.OK.value(), "User filter successfully", roleActions));
		 
		  }
	
	
}
