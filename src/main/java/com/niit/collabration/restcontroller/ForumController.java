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

import com.niit.collabration.dao.ForumDAO;
import com.niit.collabration.model.Forum;

import ch.qos.logback.classic.Logger;

@RestController
public class ForumController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(ForumController.class);

	@Autowired
	ForumDAO forumDAO;

	@Autowired
	Forum forum;

	@GetMapping("/forum")
	public ResponseEntity<List<Forum>> getForum() {
		logger.debug("Calling method getForum");
		List<Forum> list = forumDAO.list();
		if (list.isEmpty() == true) {
			return new ResponseEntity<List<Forum>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Forum>>(HttpStatus.OK);

	}

	@GetMapping("/forum/{id}")
	public ResponseEntity<Forum> getForum(@PathVariable("id") int id) {
		logger.debug("Calling method getForum with Forum id "+id);
		forum = forumDAO.get(id);
		if (forum == null) {
			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Forum>(HttpStatus.OK);
	}

	@PostMapping("/forum")
	public ResponseEntity<Forum> createForum(@RequestBody Forum forum){
		logger.debug("Calling method createForum");
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@DeleteMapping("/forum/{id}")
	public ResponseEntity<Forum> deleteForum(@PathVariable("id") int id){
		logger.debug("Calling method deleteForum");
		if(forumDAO.get(id)!=null){
			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);	
		}
		forumDAO.delete(id);
		return new ResponseEntity<Forum>(HttpStatus.OK);
	}
	
	@PutMapping("/forum/{id}")
	public ResponseEntity<Forum> updateForum(@PathVariable int id,@RequestBody Forum forum){
		logger.debug("Calling Method updateForum with id"+id);
		if(forumDAO.get(id)==null){
			return new ResponseEntity<Forum>(HttpStatus.NOT_FOUND);
		}
		forumDAO.saveOrUpdate(forum);
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
}
