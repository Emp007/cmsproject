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
import com.cms.app.service.FooterServiceImpl;
import com.cms.model.Footer;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "footer", produces = MediaType.APPLICATION_JSON_VALUE)
public class FooterController {
	private final static Logger LOGGER = LoggerFactory.getLogger(FooterController.class);

	@Autowired
	@Qualifier("footerServiceImpl")
	private FooterServiceImpl footerService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Response<Footer>> save(@RequestBody Footer footer) {
		try {
			LOGGER.debug("Process start to save Footer data");
			System.out.println("Footer Detail : " + new Gson().toJson(footer));
			footer.setId(footerService.count());
			
			System.out.println("Footer Detail UpdatedId : " + new Gson().toJson(footer));
			
			footerService.save(footer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while save Footer data in FooterController with Footer name :" + footer.getFooterName(), e);
		}
		return ResponseEntity.ok(new Response<Footer>(HttpStatus.OK.value(), "Footer saved successfully", footer));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Response<Footer>> update(@RequestBody Footer footer) {
		try {
			LOGGER.debug("Process start to save Footer data");
			System.out.println("Footer Detail : " + new Gson().toJson(footer));
			//footer.setId(footerService.count());
			
			System.out.println("Footer Detail UpdatedId : " + new Gson().toJson(footer));
			
			footerService.save(footer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while save Footer data in FooterController with Footer name :" + footer.getFooterName(), e);
		}
		return ResponseEntity.ok(new Response<Footer>(HttpStatus.OK.value(), "Footer saved successfully", footer));
	}

	@RequestMapping(value = "all-footers", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Footer>>> getAll() {

		List<Footer> footers;
		try {
			LOGGER.debug("Process start to save fetch all footers data");
			footers = footerService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while save footer data in footerController with footer name :", e);
		}
		return ResponseEntity
				.ok(new Response<List<Footer>>(HttpStatus.OK.value(), "Footer list fetched successfully", footers));
	}

	@RequestMapping(value = "byFooterId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<Footer>> getFooterById(@PathVariable("id") long id) {

		Footer footer;
		try {
			LOGGER.debug("Process start to fetch footer data by id");
			footer = footerService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while fetching footer data in FooterController with Footer id :", e);
		}
		return ResponseEntity.ok(new Response<Footer>(HttpStatus.OK.value(), "footer fetched successfully", footer));
	}
	
	 @RequestMapping(value="byFooterName/{footerName}/hostId/{hostId}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Footer>> checkFooterById(@PathVariable("footerName") String footerName,@PathVariable("hostId") long hostId) {
		  Footer footer = new Footer();
		  try{
				LOGGER.debug("Process start to fetch host by host name");
				footer = footerService.findByFooterName(footerName,hostId);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Footer data in FooterController with footer name :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Footer>(HttpStatus.OK.value(), "Footer fetched successfully", footer));
	 
	  }

	@RequestMapping(value = "deleteFooterById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<Footer>> deleteFooterById(@PathVariable("id") long id) {
		Footer footer = new Footer();
		try {
			LOGGER.debug("Process start to delete Footer data by id");
			footerService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while deleting Footer data in FooterController with Templet id :", e);
		}
		return ResponseEntity.ok(new Response<Footer>(HttpStatus.OK.value(), "Footer deleted successfully", footer));

	}
	
	@RequestMapping(value="getFooterCount/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Footer>> getFooterCount(@PathVariable("id") long id) {
		 Footer footer = new Footer();
		  try{
				LOGGER.debug("Process start to fetch footer count by host id");
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Footer count in FooterController with host id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Footer>(HttpStatus.OK.value(), "Footer count successfully", footer));
	 
	  }

}
