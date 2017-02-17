package com.cms.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cms.admin.Response;
import com.cms.admin.service.FooterService;
import com.cms.admin.service.HeaderService;
import com.cms.admin.service.LandingPageService;
import com.cms.admin.service.MenuService;
import com.cms.model.Footer;
import com.cms.model.Header;
import com.cms.model.Menu;
import com.cms.model.Page;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "OM", produces = MediaType.APPLICATION_JSON_VALUE)
public class LandingPageController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(LandingPageController.class);
	private final static String PAGE_VIEW_NAME = "admin/landing_page";
	
	@Autowired
	private LandingPageService landingPageService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	@Qualifier("footerService")
	private FooterService footerService;
	
	@Autowired
	@Qualifier("headerService")
	private HeaderService headerService;
	
	@RequestMapping(value = "{hostName}/{templetName}/{indexPageName}", method = RequestMethod.GET)
	public ModelAndView getIndexPage(@PathVariable("hostName") String hostName,
			@PathVariable("templetName") String templetName, @PathVariable("indexPageName") String indexPageName){
		ModelAndView mav = new ModelAndView(PAGE_VIEW_NAME);
		Map<String , String> pageMap = new HashMap<String ,String>();
		Map<String , String> indexPage = new HashMap<String ,String>();
		List<Page> pages = new ArrayList<Page>();
		try{
		pages = landingPageService.getIndexPage(hostName, templetName, indexPageName);
		
		Page page = null;
		if(!CollectionUtils.isEmpty(pages))
			page = (Page) pages.get(0);
		
		
		long hostId = page.getHostId();
		
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
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		
		mav.addObject("header", headerFound);
		
		List<Footer> footerList = new ArrayList<Footer>();
		Footer footerFound = new Footer();
		try {
			footerList = footerService.getAllFooters();
			if(footerList!= null){	 
				footerFound = footerList.parallelStream()
								.filter(footer -> footer.getHostId() == hostId)
								.findAny()
								.orElse(null);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		
		mav.addObject("footer", footerFound);
		
		
		
		if(!CollectionUtils.isEmpty(pages) ){
			pageMap = landingPageService.arrangePages(pages);
			indexPage = landingPageService.indexPageSetting(pages, indexPageName);
			mav.addObject("pageList", pageMap);
			mav.addObject("indexPageName", indexPageName);
			mav.addObject("containt", indexPage.get(indexPageName));
			mav.addObject("hostName", hostName);
			mav.addObject("menu" ,menuService.getAllMenuByParentsId(0L));
			mav.addObject("parentsMenus",menuService.getAllHostNameMenuName(hostName,"parents"));
			
			
		}
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return mav;
	}
	
	@RequestMapping(value = "sub-menu/{hostName}/{menuName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByPageId(@PathVariable("hostName") String hostName ,@PathVariable("menuName") String menuName) {
		List<Menu> menus = null;
		//List<Menu> subMenus = null;
		try {
			menus = menuService.getAllHostNameMenuName(hostName,menuName);
			//subMenus = menuService.getAllHostNameChildPage(hostName,menuName);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}
	}
