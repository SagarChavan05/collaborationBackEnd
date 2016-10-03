package com.niit.collabration.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collabration.dao.JobDAO;
import com.niit.collabration.model.Job;

public class JobTest {

	public static void main(String[] args) {

		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collabration");
		context.refresh();

		JobDAO jobDAO = (JobDAO) context.getBean("jobDAO");
		
		Job job = (Job) context.getBean("job");
		
		
		System.out.println("Hi");
		job.setJobId(0);
		
		jobDAO.saveOrUpdate(job);
		
		
	}

}


