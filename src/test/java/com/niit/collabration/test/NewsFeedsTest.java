package com.niit.collabration.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collabration.dao.BlogDAO;
import com.niit.collabration.dao.NewsFeedsDAO;
import com.niit.collabration.model.Blog;
import com.niit.collabration.model.NewsFeeds;

public class NewsFeedsTest {

	public static void main(String[] args) {

		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collabration");
		context.refresh();

		NewsFeedsDAO newsFeedsDAO = (NewsFeedsDAO) context.getBean("newsFeedsDAO");
		
		NewsFeeds newsFeeds = (NewsFeeds) context.getBean("newsFeeds");
		
		
		System.out.println("Hi");
		newsFeeds.setNfId(1);
		
		newsFeedsDAO.saveOrUpdate(newsFeeds);
		
		
	}

}


