package com.cms.admin.controller;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.cms.model.FileUpload;

@Controller
@RequestMapping(value = "admin/test")
public class TestController {
	
	
	 private static String UPLOAD_LOCATION="/home/orange/Desktop/dest/";
	
	@RequestMapping( method = RequestMethod.GET)
    public ModelAndView modelview(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
		ModelAndView mv = new  ModelAndView("admin/test");
		return mv;
    
    }

	  @RequestMapping(value = "/echofile", method = RequestMethod.POST, produces = {"application/json"})
	    public @ResponseBody HashMap<String, Object> echoFile(MultipartHttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	    
		  
		      MultipartFile multipartFile = request.getFile("file");
		  
			  String fileName = multipartFile.getOriginalFilename();
			  File distDir = new File("/home/orange/Desktop/dest/");
			  if (!distDir.exists()) {
				  distDir.mkdirs();
			  }
			  
			  File destFile = new File(distDir, fileName);     
			  multipartFile.transferTo(destFile);  
		  
	        HashMap<String, Object> map = new HashMap<String, Object>();
	           return map;
	    }
	  
	  
	  
	    @RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	    public String fileupload(@ModelAttribute() FileUpload fileBucket, BindingResult result, ModelMap model) throws Exception {
	    
		  System.out.println("---------------- Original File --------------");
		  if (result.hasErrors()) {
	            System.out.println("validation errors");
	            return "singleFileUploader";
	        } else {            
	            System.out.println("Fetching file");
	            MultipartFile multipartFile = fileBucket.getFile();
	 
	            //Now do something with file...
	            FileCopyUtils.copy(fileBucket.getFile().getBytes(), new File(UPLOAD_LOCATION + fileBucket.getFile().getOriginalFilename()));
	             
	            String fileName = multipartFile.getOriginalFilename();
	            model.addAttribute("fileName", fileName);
	            return "success";
	        }
	    }
}
