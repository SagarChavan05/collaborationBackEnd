package com.niit.collabration.dao;

import java.util.List;

import com.niit.collabration.model.Job;
import com.niit.collabration.model.User;

public interface UserDAO {

	public void saveOrUpdate(User user);

	public void delete(String id);

	public User get(String id);
	
	public List<User> list();

}
