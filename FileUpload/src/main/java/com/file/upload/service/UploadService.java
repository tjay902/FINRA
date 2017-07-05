package com.file.upload.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.file.upload.dao.FileDAO;
import com.file.upload.dto.FileData;
import com.file.upload.exception.FileUploadException;

@Service
public class UploadService 
{
	private final Path rootLocation = Paths.get("upload-dir");

	@Autowired
	private FileDAO fileDAO;

	public void store(MultipartFile file) 
	{
		try 
		{
			this.init();
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} 
		catch (Exception e) 
		{
			throw new RuntimeException("Failed to copy the file");
		}
	}

	public void init() 
	{
		try 
		{
			if (!Files.exists(rootLocation)) 
			{
				Files.createDirectory(rootLocation);
			}
		} 
		catch (IOException e) 
		{
			throw new RuntimeException("Failed to create directory.");
		}
	}

	public Map<String, FileData> prepareFileObjectAndUpload(final MultipartFile file, final String firstName,
			final String lastName) throws FileUploadException 
	{
		if ((file != null) || (!StringUtils.isEmpty(firstName)) || (!StringUtils.isEmpty(lastName))) 
		{
			this.store(file);
			FileData metadata = new FileData();
			metadata.setFileName(file.getOriginalFilename());
			metadata.setContentType(file.getContentType());
			metadata.setFirstname(firstName);
			metadata.setLastname(lastName);

			fileDAO.getFilemetadata().put(file.getOriginalFilename(), metadata);

			return fileDAO.getFilemetadata();
		} 
		
		else 
		{
			return null;
		}
	}
}
