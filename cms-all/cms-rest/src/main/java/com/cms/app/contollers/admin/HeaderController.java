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
import com.cms.app.service.HeaderServiceImpl;
import com.cms.model.Header;
import com.cms.model.Host;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "header", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeaderController {
	private final static Logger LOGGER = LoggerFactory.getLogger(HeaderController.class);

	@Autowired
	@Qualifier("headerServiceImpl")
	private HeaderServiceImpl headerService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Response<Header>> save(@RequestBody Header header ) {
		try {
			LOGGER.debug("Process start to save Header data");
			System.out.println("Header Detail : " + new Gson().toJson(header));
			header.setId(headerService.count());
			
			
			
			System.out.println("Header Detail UpdatedId : " + new Gson().toJson(header));
			
			headerService.save(header);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while save Header data in HeaderController with Header name :" + header.getHeaderName(), e);
		}
		return ResponseEntity.ok(new Response<Header>(HttpStatus.OK.value(), "Header saved successfully", header));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Response<Header>> update(@RequestBody Header header) {
		try {
			LOGGER.debug("Process start to save Header data");
			System.out.println("Header Detail : " + new Gson().toJson(header));
			//header.setId(headerService.count());
			
			System.out.println("Header Detail UpdatedId : " + new Gson().toJson(header));
			
			headerService.save(header);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while save Header data in HeaderController with Header name :" + header.getHeaderName(), e);
		}
		return ResponseEntity.ok(new Response<Header>(HttpStatus.OK.value(), "Header saved successfully", header));
	}

	@RequestMapping(value = "all-headers", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Header>>> getAll() {

		List<Header> headers;
		try {
			LOGGER.debug("Process start to save fetch all headers data");
			headers = headerService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while save header data in headerController with header name :", e);
		}
		return ResponseEntity
				.ok(new Response<List<Header>>(HttpStatus.OK.value(), "Header list fetched successfully", headers));
	}

	@RequestMapping(value = "byHeaderId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<Header>> getHeaderById(@PathVariable("id") long id) {

		Header header;
		try {
			LOGGER.debug("Process start to fetch header data by id");
			header = headerService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while fetching header data in HeaderController with Header id :", e);
		}
		return ResponseEntity.ok(new Response<Header>(HttpStatus.OK.value(), "header fetched successfully", header));
	}
	
	 @RequestMapping(value="byHeaderName/{headerName}/hostId/{hostId}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Header>> checkHeaderById(@PathVariable("headerName") String headerName,@PathVariable("hostId") long hostId) {
		  Header header = new Header();
		  try{
				LOGGER.debug("Process start to fetch host by host name");
				header = headerService.findByHeaderName(headerName,hostId);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Header data in HeaderController with header name :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Header>(HttpStatus.OK.value(), "Header fetched successfully", header));
	 
	  }

	@RequestMapping(value = "deleteHeaderById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<Header>> deleteHeaderById(@PathVariable("id") long id) {
		Header header = new Header();
		try {
			LOGGER.debug("Process start to delete Header data by id");
			headerService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while deleting Header data in HeaderController with Templet id :", e);
		}
		return ResponseEntity.ok(new Response<Header>(HttpStatus.OK.value(), "Header deleted successfully", header));

	}
	
	@RequestMapping(value="getHeaderCount/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Header>> getHeaderCount(@PathVariable("id") long id) {
		 Header header = new Header();
		  try{
				LOGGER.debug("Process start to fetch header count by host id");
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Header count in HeaderController with host id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Header>(HttpStatus.OK.value(), "Header count successfully", header));
	 
	  }

}
