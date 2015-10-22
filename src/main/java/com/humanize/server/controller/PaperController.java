package com.humanize.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.server.data.Content;
import com.humanize.server.data.Contents;
import com.humanize.server.data.Paper;
import com.humanize.server.service.ContentService;
import com.humanize.server.service.PaperService;

@RestController
public class PaperController {
	
	@Autowired
	private PaperService paperService;
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/paper/{paperDate}")
	public ResponseEntity<?> getPaper(@PathVariable String paperDate) {
		List<String> contentIds = paperService.findContentIdsByPaperDate(paperDate);
		ArrayList<Content> contents = contentService.getPaper(contentIds);
		
		if (contents != null) {
			System.out.println("succes");
			return new ResponseEntity<Contents>(new Contents(contents),
					HttpStatus.OK);
		}

		System.out.println("failure");
		return new ResponseEntity<Contents>(new Contents(contents),
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@RequestMapping("/paper")
	public ResponseEntity<?> createPaper(@RequestBody Paper paper) {
		paper = paperService.createPaper(paper);

		if (paper != null) {
			return new ResponseEntity<Paper>(paper,
					HttpStatus.OK);
		}

		return new ResponseEntity<Paper>(paper,
				HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	public void updatePaper(@RequestBody Paper paper) {
		
	}
}
