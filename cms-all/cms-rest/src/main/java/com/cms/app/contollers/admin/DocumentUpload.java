package com.cms.app.contollers.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cms.app.dto.Response;
import com.cms.app.exception.CMSException;
import com.cms.model.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "document-upload", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentUpload {
	
	 private final static Logger logger = LoggerFactory.getLogger(DocumentUpload.class);

	  @Value("${resource.location}")
	  private String resourcesLocation;

	  @Value("${pdf.location}")
	  private String pdfLocation;
	  
	  @Value("${image.location}")
	  private String imageLocation;
	  
	  @Value("${vedio.location}")
	  private String vedioLocation;
	  
	  
	@RequestMapping(value = "/document", method = RequestMethod.PUT,
		      consumes = MediaType.APPLICATION_JSON_VALUE)
		  public ResponseEntity<Response<Boolean>> uploadIndustryDocument(
		      @RequestBody Map<String, Object> payload) {

		    if (CollectionUtils.isEmpty(payload)) {
		      logger.info("Cannot process empty payload.");
		      throw new IllegalArgumentException("Cannot process empty payload.");
		    }
		    boolean documentUrlUpdated;
		    try{
		        savePdf(payload);
		        documentUrlUpdated =true;
		    }catch(IOException ee){
		    	 logger.info("Error uploading  document");
		         throw new CMSException("Error updating  document");
		    }
		    return new ResponseEntity<Response<Boolean>>(new Response<Boolean>(HttpStatus.OK.value(),
		        "Industry document url updated successfully.", documentUrlUpdated), HttpStatus.OK);
		  }
	
	private void savePdf(Map<String, Object> data) throws FileNotFoundException, IOException {
	    String imageAString = data.get(Constants.DOCUMENT_CONTAINT).toString();
	    String fileName = data.get(Constants.NAME).toString();
	    long id = Long.parseLong(data.get(Constants.ID).toString());
	    byte[] imageBytes = Base64.getDecoder().decode(imageAString);
	    String extension = (String) data.get(Constants.TYPE);
	    String fileLocation ="";
	    if(extension.equals("pdf"))
	    	fileLocation = resourcesLocation + pdfLocation + "/" + id;
	    else if(extension.equalsIgnoreCase("jpg")||extension.equalsIgnoreCase("jpeg")||extension.equalsIgnoreCase("png")||extension.equalsIgnoreCase("jpg"))
	    	fileLocation = resourcesLocation + imageLocation + "/" + id;
	    else if(extension.equalsIgnoreCase("MP4")||extension.equalsIgnoreCase("3GP"))
	    	fileLocation = resourcesLocation + vedioLocation.trim()  + id;
	    
	    File folder = new File(fileLocation);
	    if (!folder.exists() && !folder.isDirectory()) {
//	      final File[] files = folder.listFiles();
//	      for (File f : files)
//	        f.delete();
//	      folder.delete();
	    	 folder.mkdirs();
	    }
	   
	    try (FileOutputStream fos = new FileOutputStream(fileLocation + "/" + fileName)) {
	      fos.write(imageBytes);
	      fos.flush();
	    }
	}
	
}
