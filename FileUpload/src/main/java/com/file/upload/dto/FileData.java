package com.file.upload.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FileData 
{

	private String fileName;
	private String contentType;
	private String firstname;
	private String lastname;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String toString() 
	{
		StringBuilder sbf = new StringBuilder();
		sbf.append("fileName:");
		sbf.append(this.fileName);
		sbf.append("<<>>");
		sbf.append("contentType").append(this.contentType).append("<<>>");
		sbf.append("firstname").append(this.firstname).append("<<>>");
		sbf.append("lastname").append(this.lastname).append("<<>>");
		sbf.append("");

		return sbf.toString();
	}

}

