
package com.humanize.server.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;
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
	
	@RequestMapping("/content/upload")
	public void upload() throws ContentCreationException {
		contentService.upload();
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
	
	@RequestMapping("/content/recommendArticle")
	public ResponseEntity<Boolean> recommendArticle(@RequestParam("contentUrl") @NotEmpty String contentUrl) throws Exception {
		return new ResponseEntity<Boolean>(contentService.recommendArticle(contentUrl), HttpStatus.OK);
	}
	
	@RequestMapping("/content/recommend")
	public ResponseEntity<Boolean> recommend(@RequestParam("contentId") @NotEmpty String contentId, @RequestParam("flag") boolean flag) throws Exception {
		return new ResponseEntity<Boolean>(contentService.updateRecommendationCount(contentId, flag), HttpStatus.OK);
	}
	
	@RequestMapping("/content/viewed")
	public ResponseEntity<Boolean> viewed(@RequestParam("contentId") @NotEmpty String contentId) throws Exception {
		return new ResponseEntity<Boolean>(contentService.incrViewedCount(contentId), HttpStatus.OK);
	}
	
	@RequestMapping("/content/shared")
	public ResponseEntity<Boolean> shared(@RequestParam("contentId") @NotEmpty String contentId) throws Exception {
		return new ResponseEntity<Boolean>(contentService.incrSharedCount(contentId), HttpStatus.OK);
	}
}
