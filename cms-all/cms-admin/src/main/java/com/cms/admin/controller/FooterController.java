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
import com.cms.admin.service.TemplateService;
import com.cms.admin.CMSAdminException;
import com.cms.admin.service.FooterService;
import com.cms.model.Host;
import com.cms.model.Footer;
import com.cms.model.Templet;

@Controller
@RequestMapping(value = "admin/footer")
public class FooterController {
	private final static Logger logger = LoggerFactory.getLogger(HostController.class);
	private final static String FOOTER_VIEW_NAME = "admin/footer_view";
	public final static String ISSAVE = "issave";
	private final static String FOOTER_NEW_FOOTER = "admin/footer_new";
	private final static String FOOTER_VIEW = "admin/footer_view_data"; 	
	
	private final static String FOOTER_UPDATE = "admin/footer_update";
	

	@Autowired
	@Qualifier("footerService")
	private FooterService footerService;

	@Autowired
	@Qualifier("templateService")
	private TemplateService templateService;
	
	@Autowired
	@Qualifier("hostService")
	private HostService hostService;

	@RequestMapping(value = "getallfooter", method = RequestMethod.GET)
	public ModelAndView getAllFooters(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(FOOTER_VIEW_NAME);
		List<Footer> footerList = new ArrayList<Footer>();
		try {
			footerList = footerService.getAllFooters();
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Footer List");
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("footers", footerList);
		return mav;
	}

	
	@RequestMapping(value = "getfooter/{hostId}", method = RequestMethod.GET)
	public ModelAndView getFooterByHostId(@PathVariable("hostId")long hostId,@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(FOOTER_VIEW_NAME);
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
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Footer List By Host Id");
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("footer", footerFound);
		return mav;
	}
	
	
	@RequestMapping(value = "newfooter", method = RequestMethod.GET)
	public ModelAndView addNew(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(FOOTER_NEW_FOOTER);
		List<Host> hosts = null;
		try {
			hosts = hostService.getAllHosts();
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Footer Creation");
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("hosts", hosts);
		return mav;
	}
	
	@RequestMapping(value = "newfooter/{hostId}", method = RequestMethod.GET)
	public ModelAndView addNewFooter(@RequestParam(required = false) boolean success,@PathVariable("hostId") long hostId) {
		ModelAndView mav = new ModelAndView(FOOTER_NEW_FOOTER);
		List<Templet> templets = null;
		List<Host> hosts = null;
		try {
			templets = templateService.getTempletsByHostId(hostId);
			hosts = hostService.getAllHosts();
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Footer Creation");
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("hostId", hostId);
		mav.addObject("templets", templets);
		mav.addObject("hosts", hosts);
		return mav;
	}

	@RequestMapping(value = "savenewfooter", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam("footerName") String footerName,
			@RequestParam("hostId") long hostId,@RequestParam("templetId") long templetId, @RequestParam("footerContent") String footerContent) {
		ModelAndView mav = new ModelAndView(FOOTER_VIEW_NAME);

		Boolean bool = false;
		System.out.println(footerContent);
		Footer footer = new Footer();
		footer.setFooterName(footerName);
		footer.setTempletId(templetId);
		footer.setHostId(hostId);
	
		footer.setFooterContent(footerContent);
		try {
			Footer saveFooter = footerService.save(footer);
			if (saveFooter != null) {
				bool = true;
			}
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Footer Saving");
		}

		mav.addObject("footers", footer);
		return new ModelAndView("redirect:" + "/admin/host/getallhost?success=" + bool);
	}
	
	@RequestMapping(value = "footerview/{footerId}", method = RequestMethod.GET)
	public ModelAndView getFooter(@PathVariable("footerId")long footerId) {
		ModelAndView mav = new ModelAndView(FOOTER_VIEW);
		Footer footer = new Footer();
		try {
			footer = footerService.getFooter(footerId);

			System.out.println(footerId);
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Footer View");
		}
		mav.addObject("footer", footer);
		return mav;
	}
	
	
	@RequestMapping(value = "footeredit/{footerId}", method = RequestMethod.GET)
	public ModelAndView getFooterEdit(@PathVariable("footerId")long footerId) {
		ModelAndView mav = new ModelAndView(FOOTER_UPDATE);
		Footer footer = new Footer();
		try {
			footer = footerService.getFooter(footerId);
		}catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Footer Edit");
		}
		mav.addObject("footer", footer);
		return mav;
	}
	
	@RequestMapping(value = "updatenewfooter", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("id") Long id, @RequestParam("footerName") String footerName,
			@RequestParam("URL") String hostName, @RequestParam("templetContent") String templateContent) {
		ModelAndView mav = new ModelAndView(FOOTER_VIEW_NAME);
		Boolean bool = false;
		Footer footer = new Footer();
		footer.setFooterName(footerName);
		footer.setId(id);
		footer.setFooterContent(templateContent);
		footer.setFooterContent(templateContent);

		try {
			Footer updateFooter = footerService.getFooter(footer.getId());
			updateFooter.setFooterContent(footer.getFooterContent());
			Footer saveFooter = footerService.update(updateFooter);
			
			if (saveFooter != null) {
				bool = true;
			}
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Footer Update");
		}

		mav.addObject("footers", footer);
		return new ModelAndView("redirect:" + "/admin/footer/footerview/"+ id);
	}
	
	@RequestMapping(value = "checkfootername/{footerName}/hostId/{hostId}", method = RequestMethod.GET)
	public @ResponseBody String getHostByHostName(@PathVariable("footerName") String footerName,@PathVariable("hostId") long hostId) {
		try {
			if(footerService.getFooter(footerName,hostId) != null)
				return "FOUND";
			
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Checking Footer Name");
		}
		return "NOT-FOUND";
	}
	

}
