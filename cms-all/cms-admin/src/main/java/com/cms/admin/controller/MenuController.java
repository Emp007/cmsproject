package com.cms.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cms.admin.CMSAdminException;
import com.cms.admin.Response;
import com.cms.admin.service.HostService;
import com.cms.admin.service.MenuService;
import com.cms.admin.service.PageService;
import com.cms.admin.service.TemplateService;
import com.cms.model.Host;
import com.cms.model.Menu;
import com.cms.model.Page;
import org.springframework.http.HttpStatus;

@Controller
@RequestMapping(value = "/admin/menu")
public class MenuController {

	private final static Logger logger = LoggerFactory.getLogger(MenuController.class);
	private final static String MENU_SETTING = "admin/menu-setting";
	public final static String ISSAVE = "issave";
	@Autowired
	private HostService hostService;

	@Autowired
	@Qualifier("templateService")
	private TemplateService templateService;
	
	@Autowired
	@Qualifier("pageService")
	private PageService pageService;

	@Autowired
	@Qualifier("menuService")
	private MenuService menuService;

	@RequestMapping(value = "menu-setting", method = RequestMethod.GET)
	public ModelAndView getClientView(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(MENU_SETTING);
		List<Host> hostList = null;
		try {
			hostList = hostService.getAllHosts();
			mav.addObject("hosts", hostList);
			mav.addObject(ISSAVE, success);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in menu setting");
		}
		return mav;
	}

	@RequestMapping(value = "get-page-list/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Page>>> getPageCount(@PathVariable("id") long id) {
		List<Page> pageList = null;
		List<Menu> menus = new ArrayList<Menu>();
		try {
			pageList = pageService.getPagesByHostId(id);
			menus = menuService.getAllMenuByHostId(id);
			if (!CollectionUtils.isEmpty(menus)) {
				pageList = filterMenu(menus, pageList);
			}
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Page List by Host Id");
		}
		return new ResponseEntity<Response<List<Page>>>(
				new Response<List<Page>>(HttpStatus.OK.value(), "page list filter successfully", pageList),
				HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-hostId/{hostId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByHostId(@PathVariable("hostId") long hostId) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByHostId(hostId);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Menus by Host Id");
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-hostName/{hostName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByHostName(@PathVariable("hostName") String hostName) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByHostName(hostName);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Menus by Host Name");
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-pageId/{pageId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByPageId(@PathVariable("pageId") long pageId) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByPageId(pageId);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Menus by Page Id");
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-pageName/{pageName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByPageId(@PathVariable("pageName") String pageName) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByPageName(pageName);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Menus by Page Name");
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-parentsId/{parentsId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByParentsId(@PathVariable("parentsId") long parentsId) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByParentsId(parentsId);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Menus by Parant Id");
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-childId/{childId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByChildId(@PathVariable("childId") long childId) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByChildId(childId);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Menus by Child Id");
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-parentsId-and-childId/{parentsId}/{childId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByChildId(@PathVariable("childId") long parentsId,
			@PathVariable("childId") long childId) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByPatentsIdAndChildId(parentsId, childId);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Menus by Parant Id and Child Id");
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam("hostId") Long hostId, @RequestParam("hostName") String hostName,
			@RequestParam("childMenuId") Long childMenuId, @RequestParam("childMenuName") String childMenuName,
			@RequestParam(value = "parentsId", required = false) Long parentsId,
			@RequestParam(value = "parentsMenuName", required = false) String parentsMenuName) {

		Menu menu = new Menu();
		Boolean bool = false;
		try {
			menu.setHostId(hostId);
			menu.setHostName(hostName);
			menu.setChildId(childMenuId);
			menu.setPageId(childMenuId);
			menu.setChildPageName(childMenuName);
			if (parentsId == null)
				menu.setParentsId(0L);
			else
				menu.setParentsId(parentsId);

			if (parentsMenuName == null || parentsMenuName == "")
				menu.setPageName("parents");
			else
				menu.setPageName(parentsMenuName);
			menuService.save(menu);
			bool = true;
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Saving Menu");
		}
		return new ModelAndView("redirect:" + "/admin/menu/menu-setting?success=" + bool);
	}

	@RequestMapping(value = "save", method = RequestMethod.PUT)
	public ResponseEntity<Response<Menu>> update(@RequestBody Menu menu) {

		try {
			menuService.save(menu);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Saving Menu");
		}
		return new ResponseEntity<Response<Menu>>(
				new Response<Menu>(HttpStatus.OK.value(), "Menu update successfully", menu), HttpStatus.OK);

	}

	public List<Page> filterMenu(List<Menu> menus, List<Page> pages) {
		List<Page> listPages = new ArrayList<Page>();
		for (Page p : pages) {
			Boolean b = false;
			for (Menu m : menus) {
				if ((m.getChildPageName().equals(p.getPageName()))) {
					b = true;
					break;
				}
			}
			if (b == false) {
				listPages.add(p);
			}
		}
		return listPages;
	}

}