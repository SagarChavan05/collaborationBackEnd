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

import com.niit.collabration.dao.UserDAO;
import com.niit.collabration.model.User;

import ch.qos.logback.classic.Logger;

@RestController
public class UserController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserDAO UserDAO;

	@Autowired
	User user;
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getUser() {
		logger.debug("Calling method getUser");
		List<User> list = UserDAO.list();
		if (list.isEmpty() == true) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(HttpStatus.OK);

	}

	@GetMapping("/user/{email}")
	public ResponseEntity<User> getUser(@PathVariable("email") String email) {
		logger.debug("Calling method getUser with email id "+email);
		user = UserDAO.get(email);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user){
		logger.debug("Calling method createUser");
		UserDAO.saveOrUpdate(user);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@DeleteMapping("/job/{email}")
	public ResponseEntity<User> deleteUser(@PathVariable("email") String email){
		logger.debug("Calling method deleteUser");
		if(UserDAO.get(email)!=null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);	
		}
		UserDAO.delete(email);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@PutMapping("/user/{email}")
	public ResponseEntity<User> updateUser(@PathVariable String email,@RequestBody User user){
		logger.debug("Calling Method updateUser with emailId "+email);
		if(UserDAO.get(email)==null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		UserDAO.saveOrUpdate(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}