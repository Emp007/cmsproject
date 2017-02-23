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
import com.cms.app.service.MenuService;
import com.cms.model.Menu;

@RestController
@RequestMapping(value = "menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

	private final static Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	@Qualifier("menuServiceImpl")
	private MenuService menuService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Response<Menu>> insert(@RequestBody Menu menu) {
		try {
			LOGGER.debug("Process start to save menu data");
			menu.setId(menuService.count());
			menuService.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while save menu data in MenuController with usre page name :" + menu.getPageName(), e);
		}
		return ResponseEntity.ok(new Response<Menu>(HttpStatus.OK.value(), "Menu saved successfully", menu));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Response<Menu>> update(@RequestBody Menu menu) {
		try {
			LOGGER.debug("Process start to update menu data");
			menuService.save(menu);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while update menu data in MenuController with usre page name :" + menu.getPageName(),
					e);
		}
		return ResponseEntity.ok(new Response<Menu>(HttpStatus.OK.value(), "Action saved successfully", menu));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Response<Menu>> getSingleMenu(@PathVariable long id) {
		Menu menu = new Menu();
		try {
			LOGGER.debug("Process start to find by id menu data");
			menu = menuService.get(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by id  in MenuController with usre page name :" + menu.getPageName(), e);
		}
		return ResponseEntity.ok(new Response<Menu>(HttpStatus.OK.value(), "filter by id  successfully", menu));
	}

	@RequestMapping(value = "hostId/{hostId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getSingleMenuByHostId(@PathVariable("hostId") Long hostId) {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			LOGGER.debug("Process start to find by host id");
			menus = menuService.getAllMenuByHostId(hostId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by hostID  in MenuController with usre page hostId :" + hostId, e);
		}
		return ResponseEntity.ok(new Response<List<Menu>>(HttpStatus.OK.value(), "filter by hostid  successfully", menus));
	}

	@RequestMapping(value = "hostName/{hostName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getSingleMenuByHostName(@PathVariable("hostName") String hostName) {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			LOGGER.debug("Process start to find by id menu data");
			menus = menuService.getAllMenuByHostName(hostName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by hostName  in MenuController with usre page hostId :" + hostName, e);
		}
		return ResponseEntity.ok(new Response<List<Menu>>(HttpStatus.OK.value(), "filter by host name  successfully", menus));
	}

	@RequestMapping(value = "pageId/{pageId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getSingleMenuByPageId(@PathVariable("pageId") long pageId) {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			LOGGER.debug("Process start to find by id menu data");
			menus = menuService.getAllMenuByPageId(pageId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by hostName  in MenuController with usre page pageIs :" + pageId, e);
		}
		return ResponseEntity.ok(new Response<List<Menu>>(HttpStatus.OK.value(), "filter by page id successfully", menus));
	}

	@RequestMapping(value = "pageName/{pageName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getSingleMenuByPageName(@PathVariable("pageName") String pageName) {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			LOGGER.debug("Process start to find by id menu data");
			menus = menuService.getAllMenuByPageName(pageName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by hostName  in MenuController with usre page pageIs :" + pageName, e);
		}
		return ResponseEntity.ok(new Response<List<Menu>>(HttpStatus.OK.value(), "filter by page name  successfully", menus));
	}

	@RequestMapping(value = "parentsId/{parentsId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getSingleMenuByPageName(@PathVariable("parentsId") long parentsId) {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			LOGGER.debug("Process start to find by id menu data");
			menus = menuService.getAllMenuByParentsId(parentsId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by hostName  in MenuController with usre page pageIs :" + parentsId, e);
		}
		return ResponseEntity.ok(new Response<List<Menu>>(HttpStatus.OK.value(), "filter by parents id  successfully", menus));
	}

	@RequestMapping(value = "childId/{childId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getMenuByChildId(@PathVariable("childId") long childId) {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			LOGGER.debug("Process start to find by id menu data");
			menus = menuService.getAllMenuByChildId(childId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by hostName  in MenuController with usre page pageIs :" + childId, e);
		}
		return ResponseEntity.ok(new Response<List<Menu>>(HttpStatus.OK.value(), "filter by child id  successfully", menus));
	}

	@RequestMapping(value = "menu-by/{parentsId}/{childId}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getAllMenuByParentsIdAndChildId(
			@PathVariable("parentsId") long parentsId, @PathVariable("childId") long childId) {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			LOGGER.debug("Process start to find by id menu data");
			menus = menuService.getAllMenuByParentsIdAndChildId(parentsId, childId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by hostName  in MenuController with usre page pageIs :" + childId, e);
		}
		return ResponseEntity.ok(new Response<List<Menu>>(HttpStatus.OK.value(), "filter by parents and child id  successfully", menus));
	}
	
	@RequestMapping(value = "menu/hostName/pageName/{hostName}/{pageName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getAllMenuListByHostNamePageName(
			@PathVariable("hostName") String hostName, @PathVariable("pageName") String pageName) {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			
			LOGGER.debug("Process start to find by id menu data");
			menus = menuService.getAllMenuListByHostNamePageName(hostName, pageName);
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by hostName  in MenuController with usre page pageIs ");
		}
		return ResponseEntity.ok(new Response<List<Menu>>(HttpStatus.OK.value(), "filter by parents and child id  successfully", menus));
	}
	
	@RequestMapping(value = "menu/hostName/childPageName/{hostName}/{childPageName}", method = RequestMethod.GET)
	public ResponseEntity<Response<List<Menu>>> getAllMenuListByHostNameChildPageName(
			@PathVariable("hostName")String hostName, @PathVariable("childPageName")String childPageName) {
		List<Menu> menus = new ArrayList<Menu>();
		try {
			LOGGER.debug("Process start to find by id menu data");
			menus = menuService.getAllMenuListByHostNamePageName(hostName, childPageName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CMSException(
					"Error find while filter by hostName  in MenuController with usre page pageIs ");
		}
		return ResponseEntity.ok(new Response<List<Menu>>(HttpStatus.OK.value(), "filter by parents and child id  successfully", menus));
	}

}