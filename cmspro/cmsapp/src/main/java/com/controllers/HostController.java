package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.models.HeaderFooterRequest;
import com.models.HostRequest;
import com.models.HostResponse;
import com.models.PageRequest;
import com.models.TemplateResponse;
import com.services.HostService;
import com.services.TemplateService;

@Controller
@ComponentScan("com.services, com.repo")
@RequestMapping(value = "admin/host")
public class HostController {
	
	@Autowired
	@Qualifier("hostService")
	private HostService hostService;

	@Autowired
	@Qualifier("templateService")
	private TemplateService templateService;
	
	@RequestMapping(value = { "/create" }, method = RequestMethod.GET)
	public ModelAndView form() throws IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		
		return new ModelAndView("admin/host/create", "data", data);
	}
	
	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("hostSave") HostRequest host, BindingResult result,
			HttpServletRequest request) throws Exception {
		try {
			hostService.save(host);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> data = new HashMap<String, Object>();
		
		return new ModelAndView("admin/host/create", "data", data);
	}
	
	@RequestMapping(value = "/gethost/{hostName}", method = RequestMethod.GET)
    public @ResponseBody String getHostName(@PathVariable("hostName") String hostName) {
		if((hostService.getHostByHostName(hostName)) != null)
			return "FOUND";
		
		return "NOT-FOUND";
    }
	
	@RequestMapping(value = { "/get-hosts" }, method = RequestMethod.GET)
	public ModelAndView getEmployees() throws IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		
		return new ModelAndView("admin/host/list", "data", data);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/gethosts", method = RequestMethod.GET)
    public @ResponseBody Object getHostList() {
		Gson gson = new Gson();
		Set<HostResponse> hosts = hostService.getHosts();
		HostResponse host = null;
		Set<HostResponse> hostList = new HashSet<HostResponse>();
		for(Object object : hosts){
			host = gson.fromJson(gson.toJson(object), HostResponse.class);
			if(host.getAliasName() == null )
				host.setAliasName("Not-Set");	
			hostList.add(host);
		}
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("data", hostList);
		
		return new Gson().toJson(data);
    }
	
	
	@RequestMapping(value = { "/create-header-footer/{hostName}" }, method = RequestMethod.GET)
	public ModelAndView form(@PathVariable("hostName") String hostName) throws IOException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("hostName", hostName);
		Set<TemplateResponse> templates= templateService.getTemplates();
		data.put("templates", templates);
		
		return new ModelAndView("admin/host/header-footer-create", "data", data);
	}
	
	@RequestMapping(value = { "/header-footer-save" }, method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("hostSave") HeaderFooterRequest headerFooterRequest, BindingResult result,
			HttpServletRequest request) throws Exception {
		try {
			if(headerFooterRequest != null){
				TemplateResponse template = templateService.getTemplateByTemplateName(headerFooterRequest.getTemplateName());
				if(template != null){
					headerFooterRequest.setContent(template.getContent());
					hostService.saveHeaderFooter(headerFooterRequest);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> data = new HashMap<String, Object>();
		Set<TemplateResponse> templates= templateService.getTemplates();
		data.put("templates", templates);
		
		return new ModelAndView("admin/host/header-footer-create", "data", data);
	}
	
}
