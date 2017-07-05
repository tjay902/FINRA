package com.file.upload.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController 
{
	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e, Model model) 
	{
		e.printStackTrace();
		return "error";
	}
}
