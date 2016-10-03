package com.niit.collabration.dao;

import java.util.List;

import com.niit.collabration.model.Blog;

public interface BlogDAO {

	public void saveOrUpdate(Blog blog);

	public void delete(int blogId);

	public Blog get(int blogId);
	
	public List<Blog> list();

}
