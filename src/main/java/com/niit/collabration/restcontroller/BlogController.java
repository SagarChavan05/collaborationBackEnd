package com.niit.collabration.restcontroller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collabration.dao.BlogDAO;
import com.niit.collabration.model.Blog;

import ch.qos.logback.classic.Logger;

@RestController
public class BlogController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(BlogController.class);

	@Autowired
	BlogDAO blogDAO;

	@Autowired
	Blog blog;

	@GetMapping("/blog")
	public ResponseEntity<List<Blog>> getBlogs() {
		logger.debug("Calling method getBlogs");
		List<Blog> list = blogDAO.list();
		if (list.isEmpty() == true) {
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Blog>>(HttpStatus.OK);
	}

	@GetMapping("/blog/{id}")
	public ResponseEntity<Blog> getBlog(@PathVariable("id") int id) {
		logger.debug("Calling method getBlog with blog id "+id);
		blog = blogDAO.get(id);
		if (blog == null) {
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(HttpStatus.OK);
	}

	@PostMapping("/blog")
	public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
		logger.debug("Calling method createBlog");
		blogDAO.saveOrUpdate(blog);
		return new ResponseEntity<Blog>(HttpStatus.OK);
		
	}
	
	@DeleteMapping("/blog/{id}")
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") int id){
		logger.debug("Calling method deleteBlog");
		if(blogDAO.get(id)!=null){
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);	
		}
		blogDAO.delete(id);
		return new ResponseEntity<Blog>(HttpStatus.OK);
	}
	
	@PutMapping("/blog/{id}")
	public ResponseEntity<Blog> updateBlog(@PathVariable int id,@RequestBody Blog blog){
		logger.debug("Calling Method updateBlog with id"+id);
		if(blogDAO.get(id)==null){
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		blogDAO.saveOrUpdate(blog);
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
}
