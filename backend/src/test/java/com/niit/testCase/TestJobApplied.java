package com.niit.testCase;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.Date_Time;
import com.niit.dao.JobAppliedDAO;
import com.niit.dao.JobDAO;
import com.niit.model.Job;
import com.niit.model.JobApplied;

public class TestJobApplied 
{
	Logger log = LoggerFactory.getLogger(TestUser.class);
	
	@Autowired
	JobAppliedDAO jobAppliedDAO;
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	Job job;
	
	@Autowired
	JobApplied jobApplied;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public TestJobApplied()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		jobAppliedDAO = (JobAppliedDAO) context.getBean("jobAppliedDAO");
		jobApplied = (JobApplied) context.getBean("jobApplied");
		jobDAO = (JobDAO) context.getBean("jobDAO");
		job = (Job) context.getBean("job");
	}
	@Test
	public void addJob()
	{
		jobApplied.setUsername("kumar912");
		
		job = jobDAO.getJob("My Second Job");
		jobApplied.setCompany(job.getCompany());
		jobApplied.setLocation(job.getLocation());
		jobApplied.setPosition(job.getPosition());
		jobApplied.setStatus(job.getStatus());
		jobApplied.setTitle(job.getTitle());
		
		Date_Time dt = new Date_Time();
		String date = dt.getDateTime();
		jobApplied.setDate(date);
		
		jobAppliedDAO.applyNew(jobApplied);
		System.out.println("New Job has been Applied");
	}
	@Test
	public void getListbyUser()
	{
		String username = "deadstone_kk";
		List<JobApplied> jobs = jobAppliedDAO.listByUser(username);
		System.out.println("Size "+jobs.size());
		for(JobApplied job: jobs)
		{
			System.out.println("JOB APPLIED -"+job.getTitle()+"\t JOB POSITION -"+job.getPosition()+" \t Applied by "+job.getUsername());
		}
	}
	@Test
	public void getListbyJob()
	{
		String title = "My Second Job";
		List<JobApplied> jobs = jobAppliedDAO.listByCompany();
		System.out.println("Size "+jobs.size());
		for(JobApplied job: jobs)
		{
			System.out.println("JOB APPLIED -"+job.getTitle()+"\t JOB POSITION -"+job.getPosition()+" \t Applied by "+job.getUsername());
		}
	}
	
	public static void main(String[] args) 
	{
		TestJobApplied tj = new TestJobApplied();
	tj.addJob();
	tj.getListbyUser();
	tj.getListbyJob();
	}
}
