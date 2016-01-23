
package com.humanize.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.data.Content;
import com.humanize.server.data.ContentSearchParams;
import com.humanize.server.data.Contents;
import com.humanize.server.exception.ContentCreationException;
import com.humanize.server.exception.ContentNotFoundException;
import com.humanize.server.service.ContentService;

@RestController
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/content/create")
	public ResponseEntity<Content> create(@RequestHeader(value="token") String token, @RequestBody Content content) throws ContentCreationException {
		return new ResponseEntity<Content>(contentService.create(token, content), HttpStatus.OK);
	}
	
	@RequestMapping("/content/upload")
	public ResponseEntity<Boolean> upload(@RequestHeader(value="token") String token) throws Exception {
		return new ResponseEntity<Boolean>(contentService.upload(token), HttpStatus.OK);
	}
	
	@RequestMapping("/content/find")
	public ResponseEntity<Contents> find(@RequestBody ContentSearchParams contentSearchParams) throws ContentNotFoundException {
		return new ResponseEntity<Contents>(contentService.find(contentSearchParams), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping("/content")
	public ResponseEntity<Contents> findByUrlId(@RequestParam("urlId") String urlId) throws ContentNotFoundException {
		return new ResponseEntity<Contents>(contentService.findByUrlId(urlId), HttpStatus.OK);
	}
}
