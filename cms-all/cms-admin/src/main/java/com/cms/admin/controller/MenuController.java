package com.cms.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cms.admin.Response;
import com.cms.admin.service.HostService;
import com.cms.admin.service.MenuService;
import com.cms.admin.service.TempletService;
import com.cms.model.Host;
import com.cms.model.Menu;
import com.cms.model.Page;
import com.google.gson.Gson;
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
	private TempletService templetService;

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "menu-setting", method = RequestMethod.GET)
	public ModelAndView getClientView(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(MENU_SETTING);
		List<Host> hostList = null;
		try {
			hostList = hostService.getAllHosts();
			mav.addObject("hosts", hostList);
			mav.addObject(ISSAVE, success);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return mav;
	}

	@RequestMapping(value = "get-page-list/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Page>>> getPageCount(@PathVariable("id") long id) {
		List<Page> pageList = null;
		List<Menu> menus = new ArrayList<Menu>();
		try {
			pageList = templetService.getPagesByHostId(id);
			menus = menuService.getAllMenuByHostId(id);
			if (!CollectionUtils.isEmpty(menus)) {
				pageList = filterMenu(menus, pageList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-hostName/{hostName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByHostName(@PathVariable("hostName") String hostName) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByHostName(hostName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-pageId/{pageId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByPageId(@PathVariable("pageId") long pageId) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByPageId(pageId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-pageName/{pageName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByPageId(@PathVariable("pageName") String pageName) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByPageName(pageName);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-parentsId/{parentsId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByParentsId(@PathVariable("parentsId") long parentsId) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByParentsId(parentsId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return new ResponseEntity<Response<List<Menu>>>(
				new Response<List<Menu>>(HttpStatus.OK.value(), "Menu list filter successfully", menus), HttpStatus.OK);

	}

	@RequestMapping(value = "get-menus-by-childId/{childId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenusByChildId(@PathVariable("childId") long childId) {
		List<Menu> menus = null;
		try {
			menus = menuService.getAllMenuByChildId(childId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
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
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return new ModelAndView("redirect:" + "/admin/menu/menu-setting?success=" + bool);
	}

	@RequestMapping(value = "save", method = RequestMethod.PUT)
	public ResponseEntity<Response<Menu>> update(@RequestBody Menu menu) {

		try {
			menuService.save(menu);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
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