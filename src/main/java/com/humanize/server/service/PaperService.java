package com.humanize.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.service.InputValidationService;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.service.ContentService;
import com.humanize.server.data.Paper;

@Service
public class PaperService {

	@Autowired
	PaperRepositoryService repositoryService;
	
	@Autowired
	ContentService contentService;
	
	@Autowired
	InputValidationService inputValidator;

	public Paper create(Paper paper) {
		return repositoryService.create(paper);
	}
	
	public Paper update(Paper paper) {
		return repositoryService.update(paper);
	}
	
	public Contents findByDate(String paperDate) {
		Paper paper = repositoryService.findByDate(paperDate);
		
		return contentService.findByIds(paper.getContentIds());
	}
}
