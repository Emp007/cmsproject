package com.cms.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cms.admin.service.ContainerService;
import com.cms.model.Container;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "admin/container")
public class ContainerController {
	private final static Logger logger = LoggerFactory.getLogger(ContainerController.class);
	private final static String CONTAINER_VIEW_NAME = "admin/container_view";
	private final static String CONTAINER_UPDATE_NAME = "admin/container_update";
	public final static String ISSAVE = "issave";
	private final static String CONTAINER_NEW_CONTAINER = "admin/container_new";

	@Autowired
	private ContainerService containerService;

	
	  @RequestMapping(value = "ajaxtest", method = RequestMethod.POST)
	  public ModelAndView updateNewDocument(HttpServletRequest request, HttpServletResponse response) {
          try{ 
	      System.out.println("f/erjd.fnjhk +  saud ahamad khan");
	    } catch (Exception e) {
	      e.printStackTrace();
	      }
		return null;
	    }

	
	@RequestMapping(value = "getallcontainer", method = RequestMethod.GET)
	public ModelAndView getClientView(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(CONTAINER_VIEW_NAME);
		List<Container> containerList = null;
		try {
			containerList = containerService.getAllContainer();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject(ISSAVE, success);
		mav.addObject("containers", containerList);
		return mav;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ModelAndView getUserById(@PathVariable("id") long id) {

		ModelAndView mav = new ModelAndView(CONTAINER_UPDATE_NAME);
		Container container = null;
		try {
			container = containerService.getContainer(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("container", container);
		return mav;
	}

	@RequestMapping(value = "newContainer", method = RequestMethod.GET)
	public ModelAndView addNewUser(@RequestParam(required = false) boolean success) {
		ModelAndView mav = new ModelAndView(CONTAINER_NEW_CONTAINER);
		Container container = null;
		try {

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		return mav;
	}

	@RequestMapping(value = "saveContainer", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam("id") Long id, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("containercontent") String containercontent) {
		ModelAndView mav = new ModelAndView(CONTAINER_VIEW_NAME);
		Container container = new Container();
		List<Container> containers;
		Boolean bool = false;
		container.setName(name);
		container.setDescription(description);
		container.setContent(containercontent);

		try {
			Container saveContainer = containerService.Save(container);
			containers = containerService.getAllContainer();
			if (saveContainer != null) {
				bool = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("containers", container);
		return new ModelAndView("redirect:" + "getallcontainer?success=" + bool);
	}

	@RequestMapping(value = "updateContainer", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam("id") Long id, 
			                   @RequestParam("name") String name,
			                   @RequestParam("description") String description 
			                   ) {
		ModelAndView mav = new ModelAndView(CONTAINER_VIEW_NAME);
		Container prevcontainer = new Container();
		List<Container> container;
		Boolean bool = false;

		try {
			prevcontainer = containerService.getContainer(id);
			prevcontainer.setName(name);
			prevcontainer.setDescription(description);

			Container saveContainer = containerService.Update(prevcontainer);
			container = containerService.getAllContainer();
			if (saveContainer != null) {
				bool = true;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
		mav.addObject("containers", container);
		return new ModelAndView("redirect:" + "getallcontainer?success=" + bool);
	}
}
