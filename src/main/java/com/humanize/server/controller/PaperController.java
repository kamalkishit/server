package com.humanize.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.humanize.dbserver.data.Contents;
import com.humanize.dbserver.data.Paper;
import com.humanize.dbserver.exception.PaperContentNotFoundException;
import com.humanize.dbserver.exception.PaperCreationException;
import com.humanize.dbserver.exception.PaperNotFoundException;
import com.humanize.dbserver.exception.PaperUpdationException;
import com.humanize.dbserver.service.PaperService;

@RestController
public class PaperController {
	
	@Autowired
	PaperService paperService;

	@RequestMapping("/paper/create")
	public ResponseEntity<Paper> create(@RequestBody Paper paper) throws PaperCreationException {
		return new ResponseEntity<Paper>(paperService.create(paper), HttpStatus.OK);
	}
	
	@RequestMapping("/paper/update")
	public ResponseEntity<Paper> update(@RequestBody Paper paper) throws PaperUpdationException {
		return new ResponseEntity<Paper>(paperService.update(paper), HttpStatus.OK);
	}
	
	@RequestMapping("/paper/find")
	public ResponseEntity<Contents> findByDate(@RequestParam("paperDate") String paperDate) throws PaperNotFoundException, PaperContentNotFoundException {
		return new ResponseEntity<Contents>(paperService.findByDate(paperDate), HttpStatus.OK);
	}
}
