package com.file.upload.exception;

public class FileUploadException extends RuntimeException 
{
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() 
	{
		return "File does not exist.";
	}
}
