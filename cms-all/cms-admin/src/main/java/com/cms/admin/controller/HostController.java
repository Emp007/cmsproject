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

import java.util.List;

@Controller
@RequestMapping(value = "admin/host")
public class HostController {
    private final static Logger logger = LoggerFactory.getLogger(HostController.class);
    
    private final static String HOST_VIEW_NAME = "admin/host_view";
    private final static String HOST_UPDATE_NAME = "admin/host_update";
    public final static String ISSAVE = "issave";
    private final static String HOST_NEW_HOST = "admin/host_new";
    public final static String HOST_CONFIG = "admin/host-config";
    
    @Autowired
    @Qualifier("hostService")
    private HostService hostService;
    
    @Autowired
    @Qualifier("pageService")
    private PageService pageService;
    
    @Autowired
    @Qualifier("templateService")
    private TemplateService templateService;

	@RequestMapping(value = "config/{hostId}", method = RequestMethod.GET)
    public ModelAndView getHostConfig(@PathVariable("hostId") long hostId) {
        ModelAndView mav = new ModelAndView(HOST_CONFIG);
        Host host = null;
        List<Page> pages = null;
        host = hostService.getHost(hostId);
        pages = pageService.getPagesByHostId(hostId);
        
        mav.addObject("host", host);
        mav.addObject("pages", pages);
        
        if(pages!=null){
        	if(pages.size() > 0)
            	mav.addObject("pageStatus", true);
            else
            	mav.addObject("pageStatus", false);
        }
        
        return mav;
    }
	
	@RequestMapping(value = "saveHostConfig", method = RequestMethod.POST)
    public ModelAndView saveHostConfig(@ModelAttribute("host") Host host,Model model) {
        Boolean bool = true;
        try {
        	 hostService.hostConfig(host);
        } catch (CMSAdminException e) {
        	 logger.error(e.getMessage(), e);
             throw new CMSAdminException("Error in Host Config");            
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
        	templets = templateService.getAllTemplets();
        	pages = pageService.getAllPages();
            hostList = hostService.getAllHosts();
        } catch (CMSAdminException e) {
            logger.error(e.getMessage(), e);
            throw new CMSAdminException("Error in getting all host");
        }
        mav.addObject(ISSAVE, success);
        mav.addObject("hosts", hostList);
        mav.addObject("pages", pages);
        mav.addObject("templets", templets);
        return mav;
    }

    @RequestMapping(value = "getHost/{hostId}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("hostId") long hostId) {
        ModelAndView mav = new ModelAndView(HOST_UPDATE_NAME);
        Host host = null;
        try {
        	host = hostService.getHost(hostId);
        	System.out.println(host.getAlias());
        } catch (CMSAdminException e) {
	            logger.error(e.getMessage(), e);
	            throw new CMSAdminException("Error in getting host by Host Id");
			}
        mav.addObject("host", host);
        return mav;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView addNewUser(@RequestParam(required = false) boolean success) {
        ModelAndView mav = new ModelAndView(HOST_NEW_HOST);
        mav.addObject("host", new Host());
        return mav;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("host") Host host,Model model) {
        ModelAndView mav = new ModelAndView(HOST_VIEW_NAME);
        Boolean bool = false;
        try {
        	Host savedhost = hostService.save(host);
            if (savedhost != null) {
                bool = true;
            }
        } catch (CMSAdminException e) {
            logger.error(e.getMessage(), e);
            throw new CMSAdminException("Error in Saving Host");
        }
        mav.addObject("hosts", host);
        return new ModelAndView("redirect:" + "getallhost?success=" + bool);
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
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
        	
        	Host saveHost = hostService.update(prevhost);
            if (saveHost != null) {
                bool = true;
            }
        } catch (CMSAdminException e) {
            logger.error(e.getMessage(), e);
            throw new CMSAdminException("Error in Updating Host");
        }
        return new ModelAndView("redirect:" + "getallhost?success=" + bool);
    }
   

    @RequestMapping(value = "checkhostname/{hostName}", method = RequestMethod.GET)
	public @ResponseBody String getHostByHostName(@PathVariable("hostName") String hostName) {
		try {
			if(hostService.getHost(hostName) != null)
				return "FOUND";
			
		} catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new CMSAdminException("Error in Checking Host Name");
		}
		return "NOT-FOUND";
	}
}
