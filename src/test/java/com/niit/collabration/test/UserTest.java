package com.niit.collabration.test;

import java.util.Date;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collabration.dao.UserDAO;
import com.niit.collabration.model.User;

public class UserTest {

	public static void main(String[] args) {

		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collabration");
		context.refresh();

		UserDAO userDAO = (UserDAO) context.getBean("userDAO");
		
		User user = (User) context.getBean("user");
		
		user.setUserRole("example1");
		user.setAddress("DemoAddress");
		user.setEmail("demo@sagar.com");
		user.setEnabled(true);
		user.setGender("male");
		user.setMob("1234567890");
		user.setName("demoName");
		user.setPasssword("passsword");
		user.setBirthDate(new Date());

		System.out.println("Hi");
		
		userDAO.saveOrUpdate(user);
		
		
	}

}


