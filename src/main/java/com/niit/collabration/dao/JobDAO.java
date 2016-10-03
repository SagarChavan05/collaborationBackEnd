package com.niit.collabration.dao;

import java.util.List;

import com.niit.collabration.model.Job;

public interface JobDAO {

	public void saveOrUpdate(Job blog);

	public void delete(int jobId);

	public Job get(int jobId);
	
	public List<Job> list();

}
