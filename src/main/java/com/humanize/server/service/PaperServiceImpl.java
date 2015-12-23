package com.humanize.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.PaperContentNotFoundException;
import com.humanize.server.data.Paper;
import com.humanize.server.exception.PaperCreationException;
import com.humanize.server.exception.PaperNotFoundException;
import com.humanize.server.exception.PaperUpdationException;

@Service
public class PaperServiceImpl implements PaperService {

	@Autowired
	PaperRepositoryService repositoryService;
	
	@Autowired
	ContentService contentService;

	public Paper create(Paper paper) throws PaperCreationException {
		return repositoryService.create(paper);
	}
	
	public Paper update(Paper paper) throws PaperUpdationException {
		return repositoryService.update(paper);
	}
	
	public Contents findByDate(String paperDate) throws PaperNotFoundException, PaperContentNotFoundException {
		try {
			Paper paper = repositoryService.findByDate(paperDate);
			
			return contentService.findByIds(paper.getContentIds());
		} catch (PaperNotFoundException exception) {
			throw exception;
		} catch (ContentNotFoundException exception) {
			throw new PaperContentNotFoundException(0, null);
		}
		
	}
}
