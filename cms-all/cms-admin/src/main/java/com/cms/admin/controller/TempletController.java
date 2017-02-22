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

import com.cms.admin.CMSAdminException;
import com.cms.admin.service.HostService;
import com.cms.admin.service.PageService;
import com.cms.admin.service.TemplateService;
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


	@Autowired
	@Qualifier("templateService")
	private TemplateService templateService;
	
	@Autowired
	@Qualifier("pageService")
	private PageService pageService;

	@Autowired
	@Qualifier("hostService")
	private HostService hostService;

	
	@RequestMapping(value = "create/{hostid}", method = RequestMethod.GET)
	public ModelAndView addNewTemplet(@RequestParam(required = false) boolean success,
			@PathVariable("hostid") long hostid) {
		ModelAndView mav = new ModelAndView(TEMPLET_NEW_TEMPLET);
		List<Host> hostList = null;
		try {
			hostList = hostService.getAllHosts();
		}catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Template Creation");
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("host", hostid);
		mav.addObject("hosts", hostList);
		return mav;
	}

	
	@RequestMapping(value = "save", method = RequestMethod.POST)
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
			Templet saveTemplet = templateService.save(templet);
			if (saveTemplet != null) {
				bool = true;
			}
		}catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Tempalte Save");
		}
		mav.addObject("templets", templet);
		return new ModelAndView("redirect:" + "/admin/host/getallhost?success=" + bool);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("template") Templet template,Model model) {
		Boolean bool = false;
	    try {
			Templet saveTemplet = templateService.update(template);
			if (saveTemplet != null)
				bool = true;
		}catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Template Update");
		}
		return new ModelAndView("redirect:" + "gettempletlist/" + template.getHostId());
	}

	
	@RequestMapping(value = "getalltemplet", method = RequestMethod.GET)
	public ModelAndView getClientView(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(TEMPLET_VIEW_NAME);
		List<Templet> templetList = null;
		try {
			templetList = templateService.getAllTemplets();
		}catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Template List");
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("templets", templetList);
		return mav;
	}

	@RequestMapping(value = "getTemplate/{templateId}", method = RequestMethod.GET)
	public ModelAndView getTempletById(@PathVariable("templateId") long templateId) {

		ModelAndView mav = new ModelAndView(TEMPLET_UPDATE_NAME);
		Host host = null;   
		Templet template = null;
		try {
			template = templateService.getTemplet(templateId);
			host = hostService.getHost(template.getHostId());
		}catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Template By Host Id ");
		}
		mav.addObject("template", template);
		mav.addObject("host", host);

		return mav;
	}


	@RequestMapping(value = "gettempletlist/{hostId}", method = RequestMethod.GET)
    public ModelAndView getTemplateListBYHostId(@PathVariable("hostId") long hostId ) {
        ModelAndView mav = new ModelAndView(TEMPLET_VIEW_NAME);
        List<Templet> templetList = null;
        try {
        	templetList = templateService.getTempletsByHostId(hostId);
        }catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Template List By Host Id");
		}
        mav.addObject("hostid",hostId);
        mav.addObject("templets", templetList);
        return mav;
    }

	
	@RequestMapping(value = "checktempletname/{templetName}/hostId/{hostId}", method = RequestMethod.GET)
	public @ResponseBody String getHostByHostName(@PathVariable("templetName") String templetName,@PathVariable("hostId") long hostId) {
		try {
			System.out.println("Templet Nmae : "+templetName +" :: Host Id : "+hostId);
			if(templateService.getTemplet(templetName,hostId) != null)
				return "FOUND";
			
		}catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Checking Template Name");
		}
		return "NOT-FOUND";
	}

}
