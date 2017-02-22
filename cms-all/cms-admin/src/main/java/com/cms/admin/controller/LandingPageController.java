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

import com.cms.admin.CMSAdminException;
import com.cms.admin.Response;
import com.cms.admin.service.FooterService;
import com.cms.admin.service.HeaderService;
import com.cms.admin.service.HostService;
import com.cms.admin.service.LandingPageService;
import com.cms.admin.service.MenuService;
import com.cms.model.Footer;
import com.cms.model.Header;
import com.cms.model.Host;
import com.cms.model.Menu;
import com.cms.model.Page;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "OM", produces = MediaType.APPLICATION_JSON_VALUE)
public class LandingPageController {

	private final static Logger LOGGER = LoggerFactory.getLogger(LandingPageController.class);
	private final static String PAGE_VIEW_NAME = "admin/landing_page";
	private final static String PAGE_404 = "fragments/404";
	private final static String PAGE_INACTIVE = "fragments/inactive";
	
	@Autowired
	private LandingPageService landingPageService;

	@Autowired
	@Qualifier("hostService")
    private HostService hostService;
	
	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;

	@Autowired
	@Qualifier("footerService")
	private FooterService footerService;

	@Autowired
	@Qualifier("headerService")
	private HeaderService headerService;

	@RequestMapping(value = "{hostName}/{templetName}/{indexPageName}", method = RequestMethod.GET)
	public ModelAndView getIndexPage(@PathVariable("hostName") String hostName,
			@PathVariable("templetName") String templetName, @PathVariable("indexPageName") String indexPageName) {
		ModelAndView mav = null;
		Map<String, String> pageMap = new HashMap<String, String>();
		Map<String, String> indexPage = new HashMap<String, String>();
		List<Page> pages = new ArrayList<Page>();
		try {
			Host host = hostService.getHost(hostName);
			
			/*checkout host is Found or not & if found then checkout host is Active Or not*/
			if(( host != null) && (host.getHostConfig()!= null) && host.getHostConfig().isActive()){
				
				indexPageName = host.getHostConfig().getWelcomePage();
				pages = landingPageService.getIndexPage(hostName, templetName, indexPageName);

				Page page = null;
				if (!CollectionUtils.isEmpty(pages))
					page = (Page) pages.get(0);

				long hostId = page.getHostId();

				List<Header> headerList = new ArrayList<Header>();
				/*Preparing Header*/
				Header headerFound = new Header();
				try {
					headerList = headerService.getAllHeaders();
					if (headerList != null) {
						headerFound = headerList.parallelStream().filter(header -> header.getHostId() == hostId).findAny()
								.orElse(null);
					}
				} catch (CMSAdminException e) {
					LOGGER.error(e.getMessage(), e);
					throw new CMSAdminException("Error in getting Header List");
				}

				
				/*Preparing Footer*/
				List<Footer> footerList = new ArrayList<Footer>();
				Footer footerFound = new Footer();
				try {
					footerList = footerService.getAllFooters();
					if (footerList != null) {
						footerFound = footerList.parallelStream().filter(footer -> footer.getHostId() == hostId).findAny()
								.orElse(null);
					}
				} catch (CMSAdminException e) {
					LOGGER.error(e.getMessage(), e);
					throw new CMSAdminException("Error in getting Footer List");

				}

				if (!CollectionUtils.isEmpty(pages)) {
					pageMap = landingPageService.arrangePages(pages);
					indexPage = landingPageService.indexPageSetting(pages, indexPageName);
					
					/*checkout the requested page is Found or Not*/
					if((indexPage.isEmpty())){
						mav = new ModelAndView(PAGE_404);
						
				        mav.addObject("hostType", hostName);
				        mav.addObject("hostName", hostName);
				        mav.addObject("pageType", indexPageName);
						return mav;
					}
					mav = new ModelAndView(PAGE_VIEW_NAME);
					mav.addObject("pageList", pageMap);
					mav.addObject("indexPageName", indexPageName);
					mav.addObject("containt", indexPage.get(indexPageName));
					mav.addObject("hostName", hostName);
					mav.addObject("menu", menuService.getAllMenuByParentsId(0L));
					mav.addObject("parentsMenus", menuService.getAllHostNameMenuName(hostName, "parents"));

				}
				mav.addObject("footer", footerFound);
				mav.addObject("header", headerFound);
				return mav;
			}else{
				if(( host != null) && ( host.getHostConfig() != null ) && !host.getHostConfig().isActive()){
					mav = new ModelAndView(PAGE_INACTIVE);
					mav.addObject("hostType", hostName);
					return mav;
				}
			}
		} catch (CMSAdminException e) {
			LOGGER.error(e.getMessage(), e);
			throw new CMSAdminException("Error in getting Page List");
		}
		mav = new ModelAndView(PAGE_404);
		
        mav.addObject("hostType", hostName);
        mav.addObject("hostName", hostName);
        mav.addObject("pageType", indexPage);
		return mav;
	}

	@RequestMapping(value = "sub-menu/{hostName}/{menuName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByPageId(@PathVariable("hostName") String hostName,
			@PathVariable("menuName") String menuName) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllHostNameMenuName(hostName, menuName);
		} catch (CMSAdminException e) {
			LOGGER.error(e.getMessage(), e);
			throw new CMSAdminException("Error in getting Menu by page id");
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}
}
