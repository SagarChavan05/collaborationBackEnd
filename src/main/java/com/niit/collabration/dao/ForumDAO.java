package com.niit.collabration.dao;

import java.util.List;

import com.niit.collabration.model.Forum;
import com.niit.collabration.model.Job;

public interface ForumDAO {

	public void saveOrUpdate(Forum forum);

	public void delete(int forumId);

	public Forum get(int forumId);
	
	public List<Forum> list();

}
