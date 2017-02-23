package com.cms.admin.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cms.admin.CMSAdminException;
import com.cms.admin.Response;
import com.cms.admin.service.FooterService;
import com.cms.admin.service.HeaderService;
import com.cms.admin.service.HostService;
import com.cms.admin.service.PageService;
import com.cms.admin.service.TemplateService;
import com.cms.model.Footer;
import com.cms.model.Header;
import com.cms.model.Host;

import com.cms.model.Meta;

import com.cms.model.Page;
import com.cms.model.Templet;

@Controller
@RequestMapping(value = "admin/page")
public class PageController {
	private final static Logger logger = LoggerFactory.getLogger(HostController.class);
	private final static String PAGE_VIEW_NAME = "admin/page_view";
	private final static String PAGE_UPDATE_NAME = "admin/page_update";
	public final static String ISSAVE = "issave";
	private final static String PAGE_NEW_PAGE = "admin/page_new";

	private final static String META = "admin/Meta";
	private final static String META_SAVE = "admin/host_view";

	private final static String PAGE_VIEW = "admin/page_view_data"; 	
	private final static String PAGE_UPDATE = "admin/page_update";
	

	@Autowired
	@Qualifier("pageService")
	private PageService pageService;

	@Autowired
	@Qualifier("templateService")
	private TemplateService templateService;
	
	@Autowired
	@Qualifier("hostService")
	private HostService hostService;
	
	@Autowired
	@Qualifier("footerService")
	private FooterService footerService;
	
	@Autowired
	@Qualifier("headerService")
	private HeaderService headerService;

	@RequestMapping(value = "getallpage", method = RequestMethod.GET)
	public ModelAndView getAllPages(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(PAGE_VIEW_NAME);
		List<Page> pageList = new ArrayList<Page>();
		try {
			pageList = pageService.getAllPages();
		} catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Page List");
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("pages", pageList);
		return mav;
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public ModelAndView addNew(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(PAGE_NEW_PAGE);
		List<Host> hosts = null;
		try {
			hosts = hostService.getAllHosts();
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Page Creation");
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("hosts", hosts);
		return mav;
	}
	
	@RequestMapping(value = "create/{hostid}", method = RequestMethod.GET)
	public ModelAndView addNewPage1(@RequestParam(required = false) boolean success,@PathVariable("hostid") long hostid) {
		ModelAndView mav = new ModelAndView(PAGE_NEW_PAGE);
		List<Templet> templets = null;
		List<Host> hosts = null;
		try {
			templets = templateService.getTempletsByHostId(hostid);
			hosts = hostService.getAllHosts();
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Templates");
		}
		
		List<Header> headerList = new ArrayList<Header>();
		Header headerFound = new Header();
		try {
			headerList = headerService.getAllHeaders();
			if(headerList!= null){	 
				headerFound = headerList.parallelStream().filter(header -> header.getHostId() == hostid).findAny().orElse(null);
			}
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Header List");
		}
		
		mav.addObject("header", headerFound);
		
		List<Footer> footerList = new ArrayList<Footer>();
		Footer footerFound = new Footer();
		try {
			footerList = footerService.getAllFooters();
			if(footerList!= null){	 
				footerFound = footerList.parallelStream().filter(footer -> footer.getHostId() == hostid).findAny().orElse(null);
			}
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Footer List");
		}
		
		mav.addObject("footer", footerFound);
		mav.addObject(ISSAVE, success);
		mav.addObject("hostid", hostid);
		mav.addObject("templets", templets);
		mav.addObject("hosts", hosts);
		Page page = new Page();
		page.setHostId(hostid);
		mav.addObject("page", page);
		return mav;
	}
	
	
	/*@RequestMapping(value = "create/{hostid}", method = RequestMethod.GET)
	public ModelAndView addNewPage(@RequestParam(required = false) boolean success,@PathVariable("hostid") long hostid) {
		ModelAndView mav = new ModelAndView(PAGE_NEW_PAGE);
		List<Templet> templets = null;
		List<Host> hosts = null;
		try {
			templets = templateService.getTempletsByHostId(hostid);
			hosts = hostService.getAllHosts();
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Page Creation");
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("hostid", hostid);
		mav.addObject("templets", templets);
		mav.addObject("hosts", hosts);
		mav.addObject("page", new Page());
		return mav;
	}*/

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("page") Page page,Model model) {
		ModelAndView mav = new ModelAndView(PAGE_VIEW_NAME);
    	Boolean bool = false;
		try {
			Page savePage = pageService.Save(page);
			if (savePage != null) {
				bool = true;
			}
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Page Save");
		}

		mav.addObject("pages", page);
		return new ModelAndView("redirect:" + "/admin/host/getallhost?success=" + bool);
	}
	
	@RequestMapping(value = "pageview/{pageId}", method = RequestMethod.GET)
	public ModelAndView getPage(@PathVariable("pageId")long pageId) {
		ModelAndView mav = new ModelAndView(PAGE_VIEW);
		Page page = new Page();
		try {
			page = pageService.getPage(pageId);
			System.out.println(pageId);
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Page Preview");
		}
		mav.addObject("page", page);
		return mav;
	}
	
	
	@RequestMapping(value = "pageedit/{pageId}", method = RequestMethod.GET)
	public ModelAndView getPageEdit(@PathVariable("pageId")long pageId) {
		ModelAndView mav = new ModelAndView(PAGE_UPDATE);
		Page page = new Page();
		try {
			page = pageService.getPage(pageId);
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Page Edit");
		}
		mav.addObject("page", page);
		return mav;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("id") Long id, @RequestParam("pageName") String pageName,
			@RequestParam("URL") String hostName, @RequestParam("templetContent") String templateContent) {
		ModelAndView mav = new ModelAndView(PAGE_VIEW_NAME);
		Boolean bool = false;
		Page page = new Page();
		page.setPageName(pageName);
		page.setPageURL(hostName);
		page.setId(id);
		page.setPageContent(templateContent);

		try {
			Page updatePage = pageService.getPage(page.getId());
			updatePage.setPageContent(page.getPageContent());
			Page savePage = pageService.update(updatePage);
			if (savePage != null) {
				bool = true;
			}
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Page Update");
		}

		mav.addObject("pages", page);
		return new ModelAndView("redirect:" + "/admin/page/pageview/"+ id);
	}
	
	@RequestMapping(value = "getpagecount/{id}", method = RequestMethod.GET)
	public ModelAndView getPageCount(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView(PAGE_VIEW_NAME);
		List<Page> pageList = null;
		try {
			pageList = pageService.getPagesByHostId(id);
		}catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Page Count ");
		}
		mav.addObject("pages", pageList);
		return mav;
	}
	
	@RequestMapping(value = "getpagelist/{id}", method = RequestMethod.GET)
	public ModelAndView getAllPageByTempletId(@PathVariable("id") long id) {
		ModelAndView mav = new ModelAndView(PAGE_VIEW_NAME);
		List<Page> pageList = null;
		try {
			pageList = pageService.getPagesByHostId(id);
		}catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Getting Page List By Host Id");
		}
		mav.addObject("pages", pageList);
		return mav;
	}
	
	@RequestMapping(value = "checkpagename/{pageName}/hostId/{hostId}", method = RequestMethod.GET)
	public @ResponseBody String getHostByHostName(@PathVariable("pageName") String pageName,@PathVariable("hostId") long hostId) {
		try {
			System.out.println("Page Nmae : "+pageName +" :: Host Id : "+hostId);
			if(pageService.getPage(pageName,hostId) != null)
				return "FOUND";
			
		}  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in Checking Page Name");
		}
		return "NOT-FOUND";
	}
	
	
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
    public ResponseEntity<Response<Boolean>> fileupload(@RequestParam("file") MultipartFile fileContaints , @RequestParam("hostId") long hostId) {
        boolean documentUrlUpdated;
        try {
            pageService.uploadDocument(fileContaints, hostId);
            documentUrlUpdated = true;
        }  catch (CMSAdminException e) {
			logger.error(e.getMessage(), e);
			throw new CMSAdminException("Error in File Uploading");
		}
        return new ResponseEntity<Response<Boolean>>(new Response<Boolean>(HttpStatus.OK.value(),
               "cms document url updated successfully.", documentUrlUpdated), HttpStatus.OK);
    } 

	@RequestMapping(value = "meta/{hostid}", method = RequestMethod.GET)
	public ModelAndView addMeta(@RequestParam(required = false) boolean success,@PathVariable("hostid") long hostid) {
		ModelAndView mav = new ModelAndView(META);
		List<Templet> templets = null;
		List<Host> hosts = null;
		List<Page> pageList = null;
		try {
			templets = hostService.getTempletsByHostId(hostid);
			hosts = hostService.getAllHosts();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		
		try {
			pageList = templateService.getPagesByHostId(hostid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("pages", pageList);
		mav.addObject("hostid", hostid);
		mav.addObject("templets", templets);
		mav.addObject("hosts", hosts);
		
		return mav;
}

	@RequestMapping(value = "savemeta", method = RequestMethod.POST)
	public ModelAndView savemeta( @RequestParam("hostId") long hostId,@RequestParam("discription") String discription,@RequestParam("title") String title,
			@RequestParam("pageName") String pageName) {
		ModelAndView mav = new ModelAndView(META_SAVE);

		Boolean bool = false;
		System.out.println(discription);
		Page page = new Page();
		Meta meta = new Meta();
		
		meta.setPageName(pageName); 
		meta.setHostId(hostId);
		meta.setTitle(title);
		System.out.println(title);
		System.out.println("Sample HTML = " + discription + "OOOOOO"+pageName+"===="+ hostId);
		
		meta.setDiscription(discription);
		page = pageService.getPage(pageName,hostId);
		if(page!=null){
			page.setMeta(meta);
			pageService.update(page);
		}
		mav.addObject("metas", meta);
		return new ModelAndView("redirect:" + "/admin/host/getallhost?success=" + bool);
	}
	
	

}