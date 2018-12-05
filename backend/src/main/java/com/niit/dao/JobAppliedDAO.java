package com.niit.dao;

import java.util.List;

import com.niit.model.Job;
import com.niit.model.JobApplied;

public interface JobAppliedDAO 
{
	
	public boolean applyNew(JobApplied jobApplied);
	
	public List<JobApplied> listByUser(String username);
	
	public List<JobApplied> listByCompany();
	
}