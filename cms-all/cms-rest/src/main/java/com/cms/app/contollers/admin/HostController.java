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
import com.cms.app.service.HostServiceImpl;
import com.cms.app.service.PageServiceImpl;
import com.cms.app.service.TempletServiceImpl;
import com.cms.model.Host;
import com.cms.model.Templet;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "host", produces = MediaType.APPLICATION_JSON_VALUE)
public class HostController {
	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	@Qualifier("hostServiceImpl")
	private HostServiceImpl hostServiceImpl;
	
	@Autowired
	@Qualifier("templetServiceImpl")
	private TempletServiceImpl templetServiceImpl;
	
	@Autowired
	@Qualifier("pageServiceImpl")
	private PageServiceImpl pageServiceImpl;
	
	  @RequestMapping(value="/insert",method = RequestMethod.POST)
	  public ResponseEntity<Response<Host>> save(@RequestBody Host host) {
			try{
				LOGGER.debug("Process start to save Host data");
				System.out.println("Host Dertail : "+new Gson().toJson(host));
				host.setId(hostServiceImpl.count());
				hostServiceImpl.save(host);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save Host data in Hostcontroller with host name :" + host.getHostName(),e);
			}
	   	    return ResponseEntity.ok(new Response<Host>(HttpStatus.OK.value(), "Host saved successfully", host));
	  }
	  
	  @RequestMapping(value="/update",method = RequestMethod.POST)
	  public ResponseEntity<Response<Host>> Update(@RequestBody Host host) {
			try{
				LOGGER.debug("Process start to Update Host data");
				System.out.println("Host Detail : "+new Gson().toJson(host));
				//host.setId(hostServiceImpl.count());
				hostServiceImpl.save(host);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while update Host data in Hostcontroller with host name :" + host.getHostName(),e);
			}
	   	    return ResponseEntity.ok(new Response<Host>(HttpStatus.OK.value(), "Host updated successfully", host));
	  }
	 
	  @RequestMapping(value="all-hosts",method = RequestMethod.GET)
	  public ResponseEntity<Response<List<Host>>> getAll() { 
		  
		  List<Host> hosts;
		  try{
				LOGGER.debug("Process start to get host data");
				
				hosts = hostServiceImpl.getAll();
				for(int i=0;i<hosts.size();i++){
					hosts.get(i).setTempletcount(templetServiceImpl.countByHostId(hosts.get(i).getId()));
					hosts.get(i).setPagecount(pageServiceImpl.countByHostId(hosts.get(i).getId()));
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while save Host data in Hostcontroller with User name :"  ,e);
			}
			  return ResponseEntity.ok(new Response<List<Host>>(HttpStatus.OK.value(), "Host retrived successfully", hosts));
	 
	  }
	  
	  @RequestMapping(value="byHostId/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Host>> getHostById(@PathVariable("id") long id) { 
		  
		  Host host;
		  try{
				LOGGER.debug("Process start to get host data by id");
				host = hostServiceImpl.get(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while retrive Host data in Hostcontroller with id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Host>(HttpStatus.OK.value(), "Host retrived successfully", host));
	  }
	  
	  @RequestMapping(value="byHostName/{name}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Host>> checkHostById(@PathVariable("name") String name) {
		  Host host = new Host();
		  try{
				LOGGER.debug("Process start to fetch host by host name");
				host = hostServiceImpl.findByName(name);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Templet data in HostController with host id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Host>(HttpStatus.OK.value(), "Host fetched successfully", host));
	 
	  }
	  
	  @RequestMapping(value="deleteHostById/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Templet>> deleteHostById(@PathVariable("id") long id) { 
		  Templet templet = new Templet();
		  try{
				LOGGER.debug("Process start to delete host data by id");
				hostServiceImpl.delete(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while deleting Host data in HostController with host id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Templet>(HttpStatus.OK.value(), "Host deleted successfully", templet));
	 
	  }
	  
	  @RequestMapping(value="getTempletByHostId/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<List<Templet>>> getTempletByHostId(@PathVariable("id") long id) {
		  List<Templet> templets = new ArrayList<Templet>();
		  try{
				LOGGER.debug("Process start to fetch templet by host id");
				templets = templetServiceImpl.findByHostId(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Templet data in HostController with host id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<List<Templet>>(HttpStatus.OK.value(), "Templet fetched successfully", templets));
	 
	  }
}
