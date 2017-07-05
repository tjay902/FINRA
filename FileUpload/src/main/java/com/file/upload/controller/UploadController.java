package com.file.upload.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.dto.FileData;
import com.file.upload.service.UploadService;


@Controller
public class UploadController 
{
	private static final Logger log = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private UploadService uploadService;

	@GetMapping("/")
    public String index() 
	{
        return "uploadForm";
    }
	
	@RequestMapping(method = RequestMethod.POST, value = "/uploadFile", produces = "text/html")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("firstname") final String firstName, @RequestParam("lastname") final String lastName) 
	{
		try 
		{
			Map<String, FileData> uploadedObj = uploadService.prepareFileObjectAndUpload(file, firstName, lastName);
			
			if (uploadedObj != null) 
			{
				if (!uploadedObj.isEmpty() && uploadedObj.containsKey(file.getOriginalFilename())) 
				{
					return new ResponseEntity("Congrats. " + file.getOriginalFilename() + " was uploaded successfully.", HttpStatus.OK);
				}
				
				else 
				{
					return new ResponseEntity("Problem occurred while uploading the file.", HttpStatus.BAD_REQUEST);
				}
			} 
			
			else 
			{
				return new ResponseEntity("Required Input Data missing to run the Service.", HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception e) 
		{
			log.error("Exception while copying the file");
			return new ResponseEntity("Problem occurred while uploading the file.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
