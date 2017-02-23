package com.cms.admin.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cms.admin.service.HostService;
import com.cms.admin.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
@RequestMapping(value = "admin/dashboard")
public class DashBoardController {

    private final static Logger logger = LoggerFactory.getLogger(DashBoardController.class);

    private final static String DASHBOARD_VIEW_NAME = "admin/dashboard";
    
    @Autowired
    private HostService hostService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(Principal principal) {

        if (principal == null) {
            logger.info("Cannot provide access to unauthenticated user");
            throw new AccessDeniedException("Cannot provide access to unauthenticated user");
        }
        ModelAndView mav = new ModelAndView(DASHBOARD_VIEW_NAME);
        mav.addObject("host", hostService.getAllHosts());
        return mav;

        //	return new ModelAndView(DASHBOARD_VIEW_NAME);
    }
    @RequestMapping(value = "downloadExcelFile", method = RequestMethod.GET)
    public void downloadPDFResource( HttpServletRequest request,  HttpServletResponse response, @RequestParam("fileName") String fileName) 
		{
		String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/templateexcel/");
		Path file = Paths.get(dataDirectory, fileName);
		if (Files.exists(file)) 
		{
		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition", "attachment; filename="+fileName);
		try
		{
		Files.copy(file, response.getOutputStream());
		response.getOutputStream().flush();
		} 
		catch (IOException ex) {
		ex.printStackTrace();
		}
		}
	}

}
