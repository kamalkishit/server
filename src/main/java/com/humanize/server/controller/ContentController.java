package com.humanize.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.data.Content;
import com.humanize.server.data.Contents;
import com.humanize.server.service.ContentService;

@RestController
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/contents/create")
	public ResponseEntity<?> createContent(@RequestBody Content content) {
		System.out.println("inside create");
		content = contentService.createContent(content);

		if (content != null) {
			return new ResponseEntity<Contents>(new Contents(content),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(content),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/contents/update")
	public ResponseEntity<?> updateContent() {
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

	@RequestMapping("/contents/bookmarks")
	public ResponseEntity<?> getBookmarks(@RequestParam("ids") List<String> ids) {
		ArrayList<Content> bookmarks = contentService.getBookmarks(ids);

		if (bookmarks != null) {
			return new ResponseEntity<Contents>(new Contents(bookmarks),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/contents/likes")
	public ResponseEntity<?> getLikes(@RequestParam("ids") List<String> ids) {
		ArrayList<Content> likes = contentService.getLikes(ids);

		if (likes != null) {
			return new ResponseEntity<Contents>(new Contents(likes),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/contents/paper")
	public ResponseEntity<?> getPaper() {
		return new ResponseEntity<Contents>(new Contents(
				contentService.getPaper()), HttpStatus.OK);
	}

	@RequestMapping("/contents/populate")
	public void populateContent() {
		contentService.populateContent();
	}

	@RequestMapping("/contents/getcontent")
	public ResponseEntity<?> getContent() {
		ArrayList<Content> contents = contentService.getContent();

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

		ArrayList<Content> contents = contentService.getMoreContent(startDate);

		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/contents/getnewcontent")
	public ResponseEntity<?> getNewContent(@RequestParam("enddate") long endDate) {

		ArrayList<Content> contents = contentService.getNewContent(endDate);

		if (contents != null) {
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping("/contents/getpaper")
	public void getPaper(@RequestParam(value = "limit") int limit) {

	}
}
