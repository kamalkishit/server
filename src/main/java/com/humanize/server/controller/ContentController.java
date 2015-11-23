
package com.humanize.server.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdationException;
import com.humanize.server.service.ContentService;

@RestController
public class ContentController {
	
	static Logger logger = Logger.getLogger(ContentController.class);

	@Autowired
	private ContentService contentService;

	@RequestMapping("/content/create")
	public ResponseEntity<Content> create(@RequestBody Content content) throws ContentCreationException {
		return new ResponseEntity<Content>(contentService.create(content), HttpStatus.OK);
	}
	
	@RequestMapping("/content/update")
	public ResponseEntity<Content> update(@RequestBody Content content) throws ContentUpdationException {
		return new ResponseEntity<Content>(contentService.update(content), HttpStatus.OK);
	}

	@RequestMapping("/content/find")
	public ResponseEntity<Contents> findByCategories(@RequestParam List<String> categories, @RequestParam(value= "createdDate", required= false) Long createdDate, @RequestParam(value= "refresh", required= false, defaultValue= "false") Boolean refresh) 
		throws ContentNotFoundException {
		return new ResponseEntity<Contents>(contentService.findByCategories(categories, createdDate, refresh), HttpStatus.OK);
	}
}
