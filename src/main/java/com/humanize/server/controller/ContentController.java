
package com.humanize.server.controller;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdateException;
import com.humanize.server.service.ContentService;

@RestController
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	private static Logger logger = Logger.getLogger(ContentController.class);

	@RequestMapping("/content/create")
	public ResponseEntity<Content> create(@RequestHeader(value="token") String token, @RequestBody Content content) throws ContentCreationException {
		return new ResponseEntity<Content>(contentService.create(token, content), HttpStatus.OK);
	}
	
	@RequestMapping("/content/upload")
	public void upload(@RequestHeader(value="token") String token) throws Exception {
		contentService.upload(token);
	}
	
	@RequestMapping("/content/update")
	public ResponseEntity<Content> update(@RequestHeader(value="token") String token, @RequestBody Content content) throws ContentUpdateException {
		return new ResponseEntity<Content>(contentService.update(token, content), HttpStatus.OK);
	}

	@RequestMapping("/content/find")
	public ResponseEntity<Contents> findByCategories(@RequestHeader(value="token") String token, @RequestParam(value = "categories", required = false) List<String> categories, @RequestParam(value = "createdDate", required = false) Long createdDate, @RequestParam(value= "refresh", required= false, defaultValue= "false") Boolean refresh) 
		throws ContentNotFoundException {
		return new ResponseEntity<Contents>(contentService.findByCategories(token, categories, createdDate, refresh), HttpStatus.OK);
	}
	
	@RequestMapping("/content/findById")
	public ResponseEntity<Content> findById(@RequestHeader(value="token") String token, @RequestParam("id") String id) throws ContentNotFoundException {
		return new ResponseEntity<Content>(contentService.findByUrlId(token, id), HttpStatus.OK);
	}
	
	@RequestMapping("/content/bookmarks")
	public ResponseEntity<Contents> findBookmarks(@RequestHeader(value="token") String token, @RequestParam("bookmarkIds") List<String> bookmarkIds) throws ContentNotFoundException {
		return new ResponseEntity<Contents>(contentService.findBookmarks(token, bookmarkIds), HttpStatus.OK);
	}
	
	@RequestMapping("/content/recommendations")
	public ResponseEntity<Contents> findRecommendations(@RequestHeader(value="token") String token, @RequestParam("recommendationIds") List<String> recommendationIds) throws ContentNotFoundException {
		return new ResponseEntity<Contents>(contentService.findRecommendations(token, recommendationIds), HttpStatus.OK);
	}
	
	@RequestMapping("/content/recommendArticle")
	public ResponseEntity<Boolean> recommendArticle(@RequestHeader(value="token") String token, @RequestParam("contentUrl") @NotEmpty String contentUrl) throws Exception {
		return new ResponseEntity<Boolean>(contentService.recommendArticle(token, contentUrl), HttpStatus.OK);
	}
	
	@RequestMapping("/content/recommended")
	public ResponseEntity<Boolean> recommend(@RequestHeader(value="token") String token, @RequestParam("contentId") @NotEmpty String contentId, @RequestParam("flag") boolean flag) throws Exception {
		return new ResponseEntity<Boolean>(contentService.updateRecommendationCount(token, contentId, flag), HttpStatus.OK);
	}
	
	@RequestMapping("/content/viewed")
	public ResponseEntity<Boolean> viewed(@RequestHeader(value="token") String token, @RequestParam("contentId") @NotEmpty String contentId) throws ContentUpdateException {
		return new ResponseEntity<Boolean>(contentService.incrViewedCount(token, contentId), HttpStatus.OK);
	}
	
	@RequestMapping("/content/shared")
	public ResponseEntity<Boolean> shared(@RequestHeader(value="token") String token, @RequestParam("contentId") @NotEmpty String contentId) throws ContentUpdateException {
		return new ResponseEntity<Boolean>(contentService.incrSharedCount(token, contentId), HttpStatus.OK);
	}
	
	@RequestMapping("/content/abc")
	public void abc() throws ContentUpdateException {
		//UrlShortner urlShortner = new GoogleUrlShortnerImpl();
		//urlShortner.getShortUrl("http://inshorts.com");
		String delims = " ";
		StringTokenizer stringTokenizer = new StringTokenizer("My Name is Kamal", delims);
		String str = "";
		while(stringTokenizer.hasMoreElements()) {
			str += stringTokenizer.nextElement() + "-";
			
		}
		System.out.println(str);
	}
}
