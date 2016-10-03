package com.niit.collabration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.collabration.dao.BlogDAOImpl;
import com.niit.collabration.dao.ForumDAOImpl;
import com.niit.collabration.dao.JobDAOImpl;
import com.niit.collabration.dao.NewsFeedsDAOImpl;
import com.niit.collabration.dao.UserDAOImpl;
import com.niit.collabration.model.Blog;
import com.niit.collabration.model.Forum;
import com.niit.collabration.model.Job;
import com.niit.collabration.model.NewsFeeds;
import com.niit.collabration.model.User;

@Configuration
@ComponentScan("com.niit.collabration")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/chatappdb");
		Properties connProperties = new Properties();
		connProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		connProperties.setProperty("hibernate.show_sql", "true");
		connProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		dataSource.setUsername("sa");
		dataSource.setPassword("");

		return dataSource;
	}

	@Autowired
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactroy(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());

		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Forum.class);
		sessionBuilder.addAnnotatedClass(NewsFeeds.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		
		return sessionBuilder.buildSessionFactory();

	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDAO")
	public UserDAOImpl getUserDAO(SessionFactory sessionFactory) {

		return new UserDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "blogDAO")
	public BlogDAOImpl getBlogDAO(SessionFactory sessionFactory) {

		return new BlogDAOImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "forumDAO")
	public ForumDAOImpl getForumDAO(SessionFactory sessionFactory) {

		return new ForumDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "newsFeedsDAO")
	public NewsFeedsDAOImpl getNewsFeedsDAO(SessionFactory sessionFactory) {

		return new NewsFeedsDAOImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name = "jobDAO")
	public JobDAOImpl getJobDAO(SessionFactory sessionFactory) {

		return new JobDAOImpl(sessionFactory);
	}
}