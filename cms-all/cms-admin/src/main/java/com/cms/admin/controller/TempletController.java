package com.cms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cms.admin.service.HostService;
import com.cms.admin.service.PageService;
import com.cms.admin.service.TempletService;
import com.cms.model.Host;
import com.cms.model.Page;
import com.cms.model.Templet;
import com.google.gson.Gson;

import java.util.List;

@Controller
@RequestMapping(value = "admin/templet")
public class TempletController {
	private final static Logger logger = LoggerFactory.getLogger(TempletController.class);
	private final static String TEMPLET_VIEW_NAME = "admin/templet_view";
	private final static String TEMPLET_UPDATE_NAME = "admin/templet_update";
	public final static String ISSAVE = "issave";
	private final static String TEMPLET_NEW_TEMPLET = "admin/templet_new";
	private final static String PAGE_VIEW_NAME = "admin/page_view";

	@Autowired
	private TempletService templetService;
	
	@Autowired
	@Qualifier("pageService")
	private PageService pageService;

	@Autowired
	private HostService hostService;

	@RequestMapping(value = "getalltemplet", method = RequestMethod.GET)
	public ModelAndView getClientView(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(TEMPLET_VIEW_NAME);
		List<Templet> templetList = null;
		try {
			templetList = templetService.getAllTemplets();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("templets", templetList);
		return mav;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView getTempletById(@PathVariable("id") long id) {

		ModelAndView mav = new ModelAndView(TEMPLET_UPDATE_NAME);
		Host host = null;   
		Templet template = null;
		try {
			template = templetService.getTemplet(id);
			host = hostService.getHost(template.getHostId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("template", template);
		mav.addObject("host", host);

		return mav;
	}

	/*@RequestMapping(value = "newtemplet", method = RequestMethod.GET)
	public ModelAndView addNew(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(TEMPLET_NEW_TEMPLET);
		List<Host> hostList = null;
		try {
			hostList = hostService.getAllHosts();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("hosts", hostList);
		return mav;
	}
*/
	@RequestMapping(value = "newtemplet/{hostid}", method = RequestMethod.GET)
	public ModelAndView addNewTemplet(@RequestParam(required = false) boolean success,
			@PathVariable("hostid") long hostid) {
		ModelAndView mav = new ModelAndView(TEMPLET_NEW_TEMPLET);
		List<Host> hostList = null;
		try {
			hostList = hostService.getAllHosts();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("host", hostid);
		mav.addObject("hosts", hostList);
		return mav;
	}

	@RequestMapping(value = "savenewtemplet", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam("id") Long id, @RequestParam("hostId") long hostId,
			@RequestParam("templetName") String templetName, @RequestParam("description") String description,
			@RequestParam("isContainsHeader") String isContainsHeader,
			@RequestParam("isContainsFooter") String isContainsFooter,
			@RequestParam("templetContent") String templetContent) {
		ModelAndView mav = new ModelAndView(TEMPLET_VIEW_NAME);
		Boolean bool = false;
		Templet templet = new Templet();
		templet.setHostId(hostId);
		templet.setTempletName(templetName);
		templet.setDescription(description);
		templet.setIsContainsHeader(isContainsHeader);
		templet.setIsContainsFooter(isContainsFooter);
		templet.setTempletContent(templetContent);
		System.out.println("Templet Detail : " + new Gson().toJson(templet));
		try {
			Templet saveTemplet = templetService.Save(templet);
			if (saveTemplet != null) {
				bool = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("templets", templet);
		return new ModelAndView("redirect:" + "/admin/host/getallhost?success=" + bool);
	}

	/*@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("id") Long id, @RequestParam("templetName") String templetName,
			@RequestParam("description") String description) {
		ModelAndView mav = new ModelAndView(TEMPLET_VIEW_NAME);
		Templet prevtemplet = new Templet();
		List<Templet> templet;
		Boolean bool = false;

		try {
			prevtemplet = templetService.getTemplet(id);
			prevtemplet.setTempletName(templetName);
			prevtemplet.setDescription(description);

			Templet saveTemplet = templetService.Update(prevtemplet);
			templet = templetService.getAllTemplets();
			if (saveTemplet != null) {
				bool = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("templets", templet);
		return new ModelAndView("redirect:" + "getalltemplet?success=" + bool);
	}*/
     
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("template") Templet template,Model model) {
		ModelAndView mav = new ModelAndView(TEMPLET_VIEW_NAME);
		Boolean bool = false;
	    try {
			Templet saveTemplet = templetService.Update(template);
			if (saveTemplet != null) {
				bool = true;
		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return new ModelAndView("redirect:" + "gettempletlist/" + template.getHostId());
	}
	

	@RequestMapping(value = "gettempletlist/{id}", method = RequestMethod.GET)
	public ModelAndView getAllTempletBYHostId(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView(TEMPLET_VIEW_NAME);
		List<Templet> templetList = null;
		try {
			templetList = hostService.getTempletsByHostId(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("templets", templetList);
		return mav;
	}

	@RequestMapping(value = "getpagelist/{id}", method = RequestMethod.GET)
	public ModelAndView getAllPageByTempletId(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView(PAGE_VIEW_NAME);
		List<Page> pageList = null;
		try {
			pageList = pageService.getPagesByHostId(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("pages", pageList);
		return mav;
	}

	@RequestMapping(value = "getpagecount/{id}", method = RequestMethod.GET)
	public ModelAndView getPageCount(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView(PAGE_VIEW_NAME);
		List<Page> pageList = null;
		try {
			pageList = pageService.getPagesByHostId(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("pages", pageList);
		return mav;
	}
	
	@RequestMapping(value = "checktempletname/{templetName}/hostId/{hostId}", method = RequestMethod.GET)
	public @ResponseBody String getHostByHostName(@PathVariable("templetName") String templetName,@PathVariable("hostId") long hostId) {
		try {
			System.out.println("Templet Nmae : "+templetName +" :: Host Id : "+hostId);
			if(templetService.getTemplet(templetName,hostId) != null)
				return "FOUND";
			
		} catch (Exception e) {}
		return "NOT-FOUND";
	}

}
