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

import com.niit.collabration.dao.NewsFeedsDAO;
import com.niit.collabration.model.NewsFeeds;

import ch.qos.logback.classic.Logger;

@RestController
public class NewsFeedsController {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(NewsFeedsController.class);

	@Autowired
	NewsFeedsDAO newsFeedsDAO;

	@Autowired
	NewsFeeds newsFeeds;

	@GetMapping("/newsfeeds")
	public ResponseEntity<List<NewsFeeds>> getNewsFeeds() {
		logger.debug("Calling method getNewsFeeds");
		List<NewsFeeds> list = newsFeedsDAO.list();
		if (list.isEmpty() == true) {
			return new ResponseEntity<List<NewsFeeds>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<NewsFeeds>>(HttpStatus.OK);
	}

	@GetMapping("/newsfeeds/{id}")
	public ResponseEntity<NewsFeeds> getNewsFeeds(@PathVariable("id") int id) {
		logger.debug("Calling method getNewsFeeds with NewsFeed id " + id);
		newsFeeds = newsFeedsDAO.get(id);
		if (newsFeeds == null) {
			return new ResponseEntity<NewsFeeds>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<NewsFeeds>(HttpStatus.OK);
	}

	@PostMapping("/newsfeeds")
	public ResponseEntity<NewsFeeds> createForum(@RequestBody NewsFeeds newsFeeds) {
		logger.debug("Calling method createNewsFeeds");
		newsFeedsDAO.saveOrUpdate(newsFeeds);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@DeleteMapping("/newsfeeds/{id}")
	public ResponseEntity<NewsFeeds> deleteNewsFeeds(@PathVariable("id") int id) {
		logger.debug("Calling method deleteNewsFeeds");
		if (newsFeedsDAO.get(id) != null) {
			return new ResponseEntity<NewsFeeds>(HttpStatus.NOT_FOUND);
		}
		newsFeedsDAO.delete(id);
		return new ResponseEntity<NewsFeeds>(HttpStatus.OK);
	}

	@PutMapping("/newsfeeds/{id}")
	public ResponseEntity<NewsFeeds> updateNewsFeeds(@PathVariable int id, @RequestBody NewsFeeds newsFeeds) {
		logger.debug("Calling Method updateNewsFeeds with id " + id);
		if (newsFeedsDAO.get(id) == null) {
			return new ResponseEntity<NewsFeeds>(HttpStatus.NOT_FOUND);
		}
		newsFeedsDAO.saveOrUpdate(newsFeeds);
		return new ResponseEntity<NewsFeeds>(newsFeeds, HttpStatus.OK);
	}
}