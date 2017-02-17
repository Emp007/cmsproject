package com.cms.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cms.admin.service.HostService;
import com.cms.admin.service.HeaderService;
import com.cms.admin.service.TempletService;
import com.cms.model.Host;
import com.cms.model.Header;
import com.cms.model.Templet;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "admin/header")
public class HeaderController {
	private final static Logger logger = LoggerFactory.getLogger(HostController.class);
	private final static String HEADER_VIEW_NAME = "admin/header_view";
	private final static String HEADER_UPDATE_NAME = "admin/header_update";
	public final static String ISSAVE = "issave";
	private final static String HEADER_NEW_HEADER = "admin/header_new";
	private final static String HEADER_VIEW = "admin/header_view_data"; 	
	
	private final static String HEADER_UPDATE = "admin/header_update";
	

	@Autowired
	@Qualifier("headerService")
	private HeaderService headerService;

	@Autowired
	private TempletService templetService;
	
	@Autowired
	private HostService hostService;

	@RequestMapping(value = "getallheader", method = RequestMethod.GET)
	public ModelAndView getAllHeaders(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(HEADER_VIEW_NAME);
		List<Header> headerList = new ArrayList<Header>();
		try {
			headerList = headerService.getAllHeaders();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("headers", headerList);
		return mav;
	}
	
	@RequestMapping(value = "getheader/{hostId}", method = RequestMethod.GET)
	public ModelAndView getHeaderByHostId(@PathVariable("hostId")long hostId,@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(HEADER_VIEW_NAME);
		List<Header> headerList = new ArrayList<Header>();
		Header headerFound = new Header();
		try {
			headerList = headerService.getAllHeaders();
			if(headerList!= null){	 
				headerFound = headerList.parallelStream()
								.filter(header -> header.getHostId() == hostId)
								.findAny()
								.orElse(null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("header", headerFound);
		return mav;
	}

	@RequestMapping(value = "newheader", method = RequestMethod.GET)
	public ModelAndView addNew(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(HEADER_NEW_HEADER);
		List<Host> hosts = null;
		try {
			//templets = templetService.getAllTemplets();
			hosts = hostService.getAllHosts();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("hosts", hosts);
		return mav;
	}
	
	@RequestMapping(value = "newheader/{hostId}", method = RequestMethod.GET)
	public ModelAndView addNewHeader(@RequestParam(required = false) boolean success,@PathVariable("hostId") long hostId) {
		ModelAndView mav = new ModelAndView(HEADER_NEW_HEADER);
		List<Templet> templets = null;
		List<Host> hosts = null;
		try {
			templets = hostService.getTempletsByHostId(hostId);
			hosts = hostService.getAllHosts();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("hostId", hostId);
		mav.addObject("templets", templets);
		mav.addObject("hosts", hosts);
		return mav;
	}

	@RequestMapping(value = "savenewheader", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam("headerName") String headerName,
			@RequestParam("hostId") long hostId,@RequestParam("templetId") long templetId, @RequestParam("headerContent") String headerContent) {
		ModelAndView mav = new ModelAndView(HEADER_VIEW_NAME);

		Boolean bool = false;
		Header header = new Header();
		header.setHeaderName(headerName);
		header.setTempletId(templetId);
		header.setHostId(hostId);
		header.setHeaderContent(headerContent);
		try {
			Header saveHeader = headerService.Save(header);
			if (saveHeader != null) {
				bool = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		mav.addObject("headers", header);
		return new ModelAndView("redirect:" + "/admin/host/getallhost?success=" + bool);
	}
	
	@RequestMapping(value = "headerview/{headerId}", method = RequestMethod.GET)
	public ModelAndView getHeader(@PathVariable("headerId")long headerId) {
		ModelAndView mav = new ModelAndView(HEADER_VIEW);
		Header header = new Header();
		try {
			header = headerService.getHeader(headerId);

			System.out.println(headerId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("header", header);
		return mav;
	}
	
	
	@RequestMapping(value = "headeredit/{headerId}", method = RequestMethod.GET)
	public ModelAndView getHeaderEdit(@PathVariable("headerId")long headerId) {
		ModelAndView mav = new ModelAndView(HEADER_UPDATE);
		Header header = new Header();
		try {
			header = headerService.getHeader(headerId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("header", header);
		
		return mav;
	}
	
	@RequestMapping(value = "updatenewheader", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("id") Long id, @RequestParam("headerName") String headerName,
			@RequestParam("URL") String hostName, @RequestParam("templetContent") String templateContent) {
		ModelAndView mav = new ModelAndView(HEADER_VIEW_NAME);
		Boolean bool = false;
		Header header = new Header();
		header.setHeaderName(headerName);
		
		header.setId(id);
		header.setHeaderContent(templateContent);
		
		header.setHeaderContent(templateContent);

		try {
			Header updateHeader = headerService.getHeader(header.getId());
			updateHeader.setHeaderContent(header.getHeaderContent());
			Header saveHeader = headerService.update(updateHeader);
			
			if (saveHeader != null) {
				bool = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

		mav.addObject("headers", header);
		return new ModelAndView("redirect:" + "/admin/header/headerview/"+ id);
	}
	
	@RequestMapping(value = "checkheadername/{headerName}/hostId/{hostId}", method = RequestMethod.GET)
	public @ResponseBody String getHostByHostName(@PathVariable("headerName") String headerName,@PathVariable("hostId") long hostId) {
		try {
			System.out.println("Header Nmae : "+headerName +" :: Host Id : "+hostId);
			if(headerService.getHeader(headerName,hostId) != null)
				return "FOUND";
			
		} catch (Exception e) {}
		return "NOT-FOUND";
	}
	

}
