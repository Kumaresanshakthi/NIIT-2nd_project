package com.niit.dao;

import com.niit.model.FileUpload;

public interface FileUploadDAO 
{
	public void save(FileUpload fileUpload, String username);
	public FileUpload getFile(String username);
}