package com.cms.app.contollers.admin;

import java.util.ArrayList;
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
import com.cms.app.service.PageServiceImpl;
import com.cms.app.service.TempletServiceImpl;
import com.cms.model.Page;
import com.cms.model.Templet;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "templet", produces = MediaType.APPLICATION_JSON_VALUE)
public class TempletController {
	private final static Logger LOGGER = LoggerFactory.getLogger(TempletController.class);
	
	@Autowired
	@Qualifier("templetServiceImpl")
	private TempletServiceImpl templetServiceImpl;
	
	@Autowired
	@Qualifier("pageServiceImpl")
	private PageServiceImpl pageServiceImpl;
	
	  @RequestMapping(value="/insert",method = RequestMethod.POST)
	  public ResponseEntity<Response<Templet>> save(@RequestBody Templet templet) {
			try{
				LOGGER.debug("Process start to save Templet data");
				System.out.println("Templet Detail : "+new Gson().toJson(templet));
			    templet.setId(templetServiceImpl.count());
				templetServiceImpl.save(templet);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save Templet data in TempletController with templet name :" + templet.getTempletName(),e);
			}
	   	    return ResponseEntity.ok(new Response<Templet>(HttpStatus.OK.value(), "Templet saved successfully", templet));
	  }
	  
	  @RequestMapping(value="/update",method = RequestMethod.POST)
	  public ResponseEntity<Response<Templet>> Update(@RequestBody Templet templet) {
			try{
				LOGGER.debug("Process start to Update Host data");
				System.out.println("Templet Detail : "+new Gson().toJson(templet));
				templetServiceImpl.save(templet);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while update Templet data in Templet Controller with templet id:" + templet.getTempletName(),e);
			}
	   	    return ResponseEntity.ok(new Response<Templet>(HttpStatus.OK.value(), "Templet updated successfully", templet));
	  }
	  
	  @RequestMapping(value="all-templets",method = RequestMethod.GET)
	  public ResponseEntity<Response<List<Templet>>> getAll() { 
		  
		  List<Templet> templets;
		  try{
				LOGGER.debug("Process start to save fetch all templet data");
				templets = templetServiceImpl.getAll();
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save Templet data in TempletController with Templet name :"  ,e);
			}
			  return ResponseEntity.ok(new Response<List<Templet>>(HttpStatus.OK.value(), "Templet list fetched successfully", templets));
	 
	  }
	  
	  @RequestMapping(value="byTempletId/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Templet>> getTempletById(@PathVariable("id") long id) { 
		  
		  Templet templet;
		  try{
				LOGGER.debug("Process start to fetch templet data by id");
				templet = templetServiceImpl.get(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Templet data in TempletController with Templet id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Templet>(HttpStatus.OK.value(), "Templet fetched successfully", templet));
	 
	  }
	  
	  @RequestMapping(value="byTempletName/{templateName}/hostId/{hostId}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Templet>> checkTempletByName(@PathVariable("templateName") String templateName, @PathVariable("hostId") long hostId) {
		  Templet templet = new Templet();
		  try{
				LOGGER.debug("Process start to fetch host by templet name");
				templet = templetServiceImpl.findByTempletName(templateName,hostId);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Templet data in TempletController with templet name :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Templet>(HttpStatus.OK.value(), "Templet fetched successfully", templet));
	 
	  }
	  
	  @RequestMapping(value="deletetempletById/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Templet>> deleteTempletById(@PathVariable("id") long id) { 
		  Templet templet = new Templet();
		  try{
				LOGGER.debug("Process start to delete templet data by id");
				templetServiceImpl.delete(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while deleting Templet data in TempletController with Templet id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Templet>(HttpStatus.OK.value(), "Templet deleted successfully", templet));
	 
	  }
	  
	  
	  @RequestMapping(value="getPageByTempletId/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<List<Page>>> getPageByTempletId(@PathVariable("id") long id) {
		  List<Page> pages = new ArrayList<Page>();
		  try{
				LOGGER.debug("Process start to fetch templet by host id");
				pages =  pageServiceImpl.findByTempletId(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Page data in TempletController with host id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<List<Page>>(HttpStatus.OK.value(), "Page fetched successfully", pages));
	 
	  }
	  
	  @RequestMapping(value="getTempletCount/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Templet>> getPageCount(@PathVariable("id") long id) {
		 Templet templet = new Templet();
		  try{
				LOGGER.debug("Process start to fetch templet count by host id");
				templet.setCount(templetServiceImpl.countByHostId(id));
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Templet data in TempletController with host id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Templet>(HttpStatus.OK.value(), "Templet count successfully", templet));
	 
	  }
}
