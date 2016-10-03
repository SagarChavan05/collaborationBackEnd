package com.niit.collabration.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collabration.dao.ForumDAO;
import com.niit.collabration.model.Forum;

public class ForumTest {

	public static void main(String[] args) {

		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collabration");
		context.refresh();

		ForumDAO forumDAO = (ForumDAO) context.getBean("forumDAO");
		
		Forum forum = (Forum) context.getBean("forum");
		
		
		System.out.println("Hi");
		forum.setForumId(0);
		
		forumDAO.saveOrUpdate(forum);
		
		
	}

}


