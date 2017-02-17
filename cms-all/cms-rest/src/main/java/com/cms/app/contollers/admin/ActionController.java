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
import com.cms.app.service.ActionService;
import com.cms.model.Action;

@RestController
@RequestMapping(value = "action", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActionController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ActionController.class);
	
	@Autowired
	@Qualifier("actionServiceImpl")
	private ActionService actionService;
	
	 @RequestMapping(method = RequestMethod.POST)
	  public ResponseEntity<Response<Action>> save(@RequestBody Action action) {
			try{
				LOGGER.debug("Process start to save Action data");
				action.setId(actionService.count());
				actionService.save(action);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save Action data in ActionController with usre roleName :" + action.getActionName(),e);
			}
	   	    return ResponseEntity.ok(new Response<Action>(HttpStatus.OK.value(), "Action saved successfully", action));
	  }
		  
		  @RequestMapping(method = RequestMethod.GET)
		  public ResponseEntity<Response<List<Action>>> getAll() { 
			  List<Action> actions;
			  try{
					LOGGER.debug("Process start to filter action data");
					actions = actionService.getAll();
				}catch(Exception e){
					e.printStackTrace();
					throw new CMSException("Error find while filter action data in Actioncontroller :"  ,e);
				}
				  return ResponseEntity.ok(new Response<List<Action>>(HttpStatus.OK.value(), "Action filters successfully", actions));
		  }

}
