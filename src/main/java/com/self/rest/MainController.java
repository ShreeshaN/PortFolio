package com.self.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
	
	private static final int BUFFER_SIZE = 4096;
	
	@RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET,produces="application/pdf")
	public void getFile(
	    @PathVariable("file_name") String fileName, HttpServletRequest request,
	    HttpServletResponse response) {
	    try {
	    	String file = System.getProperty("user.dir")+File.separator+fileName+".pdf";
	    	
	    	 File downloadFile = new File(file);
	         FileInputStream inputStream = new FileInputStream(downloadFile);
	    	
	         ServletContext context = request.getServletContext();
	    	String mimeType =context.getMimeType(file);
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        System.out.println("MIME type: " + mimeType);
	 
	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
	    	
	    }
	    catch(Exception e)
	    {
	    	
	    }

	}

}
