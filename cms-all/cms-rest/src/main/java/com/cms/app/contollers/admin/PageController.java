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
import com.cms.app.service.PageService;
import com.cms.app.service.PageServiceImpl;
import com.cms.model.Page;
import com.cms.model.Templet;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "page", produces = MediaType.APPLICATION_JSON_VALUE)
public class PageController {
	private final static Logger LOGGER = LoggerFactory.getLogger(PageController.class);

	@Autowired
	@Qualifier("pageServiceImpl")
	private PageService pageService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Response<Page>> save(@RequestBody Page page) {
		try {
			LOGGER.debug("Process start to save Page data");
			System.out.println("Page Detail : " + new Gson().toJson(page));
			page.setId(pageService.count());
			
			System.out.println("Page Detail UpdatedId : " + new Gson().toJson(page));
			
			pageService.save(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while save Page data in PageController with Page name :" + page.getPageName(), e);
		}
		return ResponseEntity.ok(new Response<Page>(HttpStatus.OK.value(), "Page saved successfully", page));
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Response<Page>> update(@RequestBody Page page) {
		try {
			LOGGER.debug("Process start to save Page data");
			System.out.println("Page Detail : " + new Gson().toJson(page));
			//page.setId(pageService.count());
			
			System.out.println("Page Detail UpdatedId : " + new Gson().toJson(page));
			
			pageService.save(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while save Page data in PageController with Page name :" + page.getPageName(), e);
		}
		return ResponseEntity.ok(new Response<Page>(HttpStatus.OK.value(), "Page saved successfully", page));
	}
	
	@RequestMapping(value="getPageByHostId/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<List<Page>>> getPageByHostId(@PathVariable("id") long id) {
		  List<Page> pages = new ArrayList<Page>();
		  try{
				LOGGER.debug("Process start to fetch page by host id");
				pages =  pageService.findByHostId(id);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Page data in TempletController with host id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<List<Page>>(HttpStatus.OK.value(), "Page fetched successfully", pages));
	 
	  }

	@RequestMapping(value = "all-pages", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Page>>> getAll() {

		List<Page> pages;
		try {
			LOGGER.debug("Process start to save fetch all pages data");
			pages = pageService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while save page data in pageController with page name :", e);
		}
		return ResponseEntity
				.ok(new Response<List<Page>>(HttpStatus.OK.value(), "Page list fetched successfully", pages));
	}

	@RequestMapping(value = "byPageId/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<Page>> getPageById(@PathVariable("id") long id) {

		Page page;
		try {
			LOGGER.debug("Process start to fetch page data by id");
			page = pageService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while fetching page data in PageController with Page id :", e);
		}
		return ResponseEntity.ok(new Response<Page>(HttpStatus.OK.value(), "page fetched successfully", page));
	}
	
	 @RequestMapping(value="byPageName/{pageName}/hostId/{hostId}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Page>> checkPageById(@PathVariable("pageName") String pageName,@PathVariable("hostId") long hostId) {
		  Page page = new Page();
		  try{
				LOGGER.debug("Process start to fetch host by host name");
				page = pageService.findByPageName(pageName,hostId);
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Page data in PageController with page name :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Page>(HttpStatus.OK.value(), "Page fetched successfully", page));
	 
	  }

	@RequestMapping(value = "deletePageById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<Page>> deletePageById(@PathVariable("id") long id) {
		Page page = new Page();
		try {
			LOGGER.debug("Process start to delete Page data by id");
			pageService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException("Error find while deleting Page data in PageController with Templet id :", e);
		}
		return ResponseEntity.ok(new Response<Page>(HttpStatus.OK.value(), "Page deleted successfully", page));

	}
	
	  @RequestMapping(value="getPageCount/{id}",method = RequestMethod.GET)
	  public ResponseEntity<Response<Page>> getPageCount(@PathVariable("id") long id) {
		 Page page = new Page();
		  try{
				LOGGER.debug("Process start to fetch page count by host id");
				page.setCount(pageService.countByHostId(id));
			}catch(Exception e){
				e.printStackTrace();
				throw new CMSException("Error find while fetching Page count in PageController with host id :"  ,e);
			}
			  return ResponseEntity.ok(new Response<Page>(HttpStatus.OK.value(), "Page count successfully", page));
	 
	  }

}
