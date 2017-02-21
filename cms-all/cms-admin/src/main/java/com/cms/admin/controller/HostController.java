package com.cms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@Controller
@RequestMapping(value = "admin/host")
public class HostController {
    private final static Logger logger = LoggerFactory.getLogger(HostController.class);
    
    private final static String HOST_VIEW_NAME = "admin/host_view";
    private final static String HOST_UPDATE_NAME = "admin/host_update";
    public final static String ISSAVE = "issave";
    private final static String HOST_NEW_HOST = "admin/host_new";
    private final static String TEMPLET_VIEW_NAME = "admin/templet_view";
    public final static String HOST_CONFIG = "admin/host-config";
    
    @Autowired
    private HostService hostService;
    
    @Autowired
    private PageService pageService;
    
    @Autowired
    private TempletService templetService;

	@RequestMapping(value = "config/{hostId}", method = RequestMethod.GET)
    public ModelAndView getHostConfig(@PathVariable("hostId") long hostId) {

        ModelAndView mav = new ModelAndView(HOST_CONFIG);
        Host host = null;
        List<Page> pages = null;
        try {
        	host = hostService.getHost(hostId);
        	pages = pageService.getPagesByHostId(hostId);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
        mav.addObject("host", host);
        mav.addObject("pages", pages);
        if(pages.size() > 0)
        	mav.addObject("pageStatus", true);
        else
        	mav.addObject("pageStatus", false);
        mav.addObject("hostType", host);
        mav.addObject("hostName", host);
        mav.addObject("pageType", host);
        return mav;
    }
	
	@RequestMapping(value = "saveHostConfig", method = RequestMethod.POST)
    public ModelAndView saveHostConfig(@ModelAttribute("host") Host host,Model model) {
        
        Boolean bool = true;
        try {
        	 hostService.hostConfig(host);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ModelAndView("redirect:" + "getallhost?success=" + bool);
    }
    
    @RequestMapping(value = "getallhost", method = RequestMethod.GET)
    public ModelAndView getClientView(@RequestParam(required = false) boolean success) {
        ModelAndView mav = new ModelAndView(HOST_VIEW_NAME);
        List<Host> hostList = null;
        List<Templet> templets = null;
		List<Page> pages = null;
        try {
        	templets = templetService.getAllTemplets();
        	pages = pageService.getAllPages();
            hostList = hostService.getAllHosts();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        mav.addObject(ISSAVE, success);
        mav.addObject("hosts", hostList);
        mav.addObject("pages", pages);
        mav.addObject("templets", templets);
        return mav;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("id") long id) {

        ModelAndView mav = new ModelAndView(HOST_UPDATE_NAME);
        Host host = null;
        try {
        	host = hostService.getHost(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        }
        mav.addObject("host", host);
        return mav;
    }

    @RequestMapping(value = "newHost", method = RequestMethod.GET)
    public ModelAndView addNewUser(@RequestParam(required = false) boolean success) {
        ModelAndView mav = new ModelAndView(HOST_NEW_HOST);
        try {
      
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        mav.addObject("host", new Host());
        return mav;
    }

    
    @RequestMapping(value = "saveHost", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("host") Host host,Model model) {
        ModelAndView mav = new ModelAndView(HOST_VIEW_NAME);
        Boolean bool = false;
        try {
        	Host saveHost = hostService.Save(host);
            if (saveHost != null) {
                bool = true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        mav.addObject("hosts", host);
        return new ModelAndView("redirect:" + "getallhost?success=" + bool);
    }
    
    @RequestMapping(value = "updateHost", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("host") Host host,Model model ) {
        Host prevhost = new Host();
        Boolean bool = false;
        
        try {
        	prevhost = hostService.getHost(host.getId());
        	prevhost.setHostName(host.getHostName());
        	prevhost.setStatus(host.getStatus());
        	prevhost.setType(host.getType());
        	prevhost.setStartDate(host.getStartDate());
        	prevhost.setExpireDate(host.getExpireDate());
        	prevhost.setAlias(host.getAlias());
        	prevhost.setIndexpageURL(host.getIndexpageURL());
        	
        	Host saveHost = hostService.Update(prevhost);
            if (saveHost != null) {
                bool = true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        return new ModelAndView("redirect:" + "getallhost?success=" + bool);
    }
    
    @RequestMapping(value = "gettempletlist/{id}", method = RequestMethod.GET)
    public ModelAndView getAllTempletBYHostId(@PathVariable("id") long id ) {
        ModelAndView mav = new ModelAndView(TEMPLET_VIEW_NAME);
        List<Templet> templetList = null;
        try {
        	templetList = hostService.getTempletsByHostId(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        mav.addObject("hostid",id);
        mav.addObject("templets", templetList);
        return mav;
    }

    @RequestMapping(value = "checkhostname/{hostName}", method = RequestMethod.GET)
	public @ResponseBody String getHostByHostName(@PathVariable("hostName") String hostName) {
		try {
			if(hostService.getHost(hostName) != null)
				return "FOUND";
			
		} catch (Exception e) {}
		return "NOT-FOUND";
	}
}
