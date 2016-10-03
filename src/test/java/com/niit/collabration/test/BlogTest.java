package com.niit.collabration.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collabration.dao.BlogDAO;
import com.niit.collabration.model.Blog;

public class BlogTest {

	public static void main(String[] args) {

		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collabration");
		context.refresh();

		BlogDAO blogDAO = (BlogDAO) context.getBean("blogDAO");
		
		Blog blog = (Blog) context.getBean("blog");
		
		
		System.out.println("Hi");
		blog.setBlgoId(1);
		
		blogDAO.saveOrUpdate(blog);
		
		
	}

}


