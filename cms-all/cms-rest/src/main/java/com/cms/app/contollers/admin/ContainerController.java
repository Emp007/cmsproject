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
import org.springframework.web.bind.annotation.RestController;
import com.cms.app.dto.Response;
import com.cms.app.exception.CMSException;
import com.cms.app.service.ContainerServiceImpl;
import com.cms.model.Container;
import com.cms.model.Host;
import com.google.gson.Gson;

@RestController
@RequestMapping(value ="container",produces = MediaType.APPLICATION_JSON_VALUE)
public class ContainerController {
	private final static Logger LOGGER = LoggerFactory.getLogger(ContainerController.class);

	@Autowired
	@Qualifier("containerServiceImpl")
	private ContainerServiceImpl containerServiceImpl;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Response<Container>> save(@RequestBody Container container) {
		try {
			LOGGER.debug("Process start to save Container data");
			container.setId(containerServiceImpl.count());
			containerServiceImpl.save(container);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while saving Container data in Containercontroller with host name :"
					+ container.getName(), e);
		}
		return ResponseEntity
				.ok(new Response<Container>(HttpStatus.OK.value(), "Container saved successfully", container));
	}
	
	 @RequestMapping(value="/update",method = RequestMethod.POST)
	  public ResponseEntity<Response<Container>> Update(@RequestBody Container container) {
			try{
				LOGGER.debug("Process start to Update Host data");
				System.out.println("Host Dertail : "+new Gson().toJson(container));
				//host.setId(hostServiceImpl.count());
				containerServiceImpl.save(container);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while update Host data in Hostcontroller with host name :" + container.getName(),e);
			}
	   	    return ResponseEntity.ok(new Response<Container>(HttpStatus.OK.value(), "Host updated successfully", container));
	  }
	
	 @RequestMapping(value="all-containers",method = RequestMethod.GET)
	  public ResponseEntity<Response<List<Container>>> getAll() { 
		  
		  List<Container> container;
		  try{
				LOGGER.debug("Process start to save host data");
				container = containerServiceImpl.getAll();
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save Container data in Container controller with User name :"  ,e);
			}
			  return ResponseEntity.ok(new Response<List<Container>>(HttpStatus.OK.value(), "Container retrived successfully", container));
	 
	  }
	  
	  @RequestMapping(value="byContainerId/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Container>> getContainerById(@PathVariable("id") long id) { 
		  
		  Container container;
		  try{
				LOGGER.debug("Process start to get Container data by id");
				container = containerServiceImpl.get(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while retrive Container data in Container controller with id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Container>(HttpStatus.OK.value(), "Container retrived successfully", container));
	  }
	  
	  @RequestMapping(value="deletecontainerbyid/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Container>> deleteHostById(@PathVariable("id") long id) { 
		  Container container = new Container();
		  try{
				LOGGER.debug("Process start to delete host data by id");
				containerServiceImpl.delete(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while deleting Container data in Container Controller with container id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Container>(HttpStatus.OK.value(), "Controller deleted successfully", container));
	 
	  }
}
