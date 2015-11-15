package com.humanize.server.controller;

import java.util.ArrayList;
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
import com.humanize.server.content.service.ContentService;
import com.humanize.server.content.service.ImageDownloaderService;

@RestController
public class ContentController {
	
	static Logger logger = Logger.getLogger(ContentController.class);

	@Autowired
	private ContentService contentService;
	
	@Autowired
	private ImageDownloaderService imageDownloader;

	@RequestMapping("/contents/create")
	public ResponseEntity<Content> createContent(@RequestBody Content content) {
		return new ResponseEntity<Content>(contentService.createContent(content), HttpStatus.OK);
	}
	
	@RequestMapping("/contents/category")
	public ResponseEntity<?> getByCategory(@RequestParam String category, @RequestParam long time) {
		List<Content> contents = contentService.findByCategoryCreatedDateLessThan(category, time);
		
		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}	
	
	@RequestMapping("/contents/categories")
	public ResponseEntity<?> getByCategories() {
		List<Content> contents = contentService.findByCategories(new ArrayList<String>());
		
		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@RequestMapping("/contents/update")
	public ResponseEntity<Boolean> updateContent() {
		System.out.println("i m here");

		Content content = new Content();
		content.setContentId("e4b371c1-9a7b-4237-84a3-d2f864515fbb");

		ArrayList<Content> contentList = new ArrayList<Content>();
		contentList.add(content);

		Contents contents = new Contents();
		contents.setContents(contentList);
		;

		boolean result = contentService.updateContents(contents);
		if (result) {
			System.out.println("success");
		}
		boolean isSuccess = contentService.updateContents(contents);

		if (isSuccess) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@RequestMapping("/contents/paper")
	public ResponseEntity<?> getPaper(@RequestParam("ids") List<String> ids) {
		List<Content> likes = null;

		if (likes != null) {
			return new ResponseEntity<Contents>(new Contents(likes),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/contents/populate")
	public void populateContent() {
		contentService.populateContent();
	}

	@RequestMapping("/contents/getcontent")
	public ResponseEntity<?> getContent() {
		logger.debug("inside getcontent");
		ArrayList<Content> contents = contentService.getContent();

		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping("/contents/getcontents")
	public ResponseEntity<?> getContents(@RequestParam("categories") List<String> categories) {
		logger.debug("inside getcontent");
		ArrayList<Content> contents = contentService.getContents(categories);

		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/contents/getmorecontent")
	public ResponseEntity<?> getMoreContent(
			@RequestParam("startdate") long startDate) {

		List<Content> contents = contentService.getMoreContent(startDate);

		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@RequestMapping("/contents/getmorecontents")
	public ResponseEntity<?> getMoreContent(@RequestParam("categories") List<String> categories, 
			@RequestParam("startdate") long startDate) {

		List<Content> contents = contentService.getMoreContents(categories, startDate);

		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/contents/getnewcontent")
	public ResponseEntity<?> getNewContent(@RequestParam("enddate") long endDate) {

		List<Content> contents = contentService.getNewContent(endDate);

		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping("/contents/getNewContents")
	public ResponseEntity<Contents> getNewContents(@RequestParam("categories") List<String> categories, @RequestParam("endDate") long endDate) {

		List<Content> contents = contentService.getNewContents(categories, endDate);

		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping("/contents/download")
	public ResponseEntity<Boolean> downloadImage(@RequestParam("url") String url) {
		Content content = new Content();
		content.setOriginalImageURL(url);
		
		return new ResponseEntity<Boolean>(imageDownloader.downloadImage(content), HttpStatus.OK);
	}
}
