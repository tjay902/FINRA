package com.file.upload.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.file.upload.dto.FileData;

@Component	
public class FileDAO 
{
	private static  Map<String,FileData> filemetadata = new ConcurrentHashMap<>();

	public static  Map<String,FileData> getFilemetadata() 
	{
		return filemetadata;
	}
	
}
