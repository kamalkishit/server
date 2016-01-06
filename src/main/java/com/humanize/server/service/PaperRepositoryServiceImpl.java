package com.humanize.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.dao.PaperRepository;
import com.humanize.server.data.Paper;
import com.humanize.server.exception.PaperCreationException;
import com.humanize.server.exception.PaperNotFoundException;
import com.humanize.server.exception.PaperUpdationException;

@Service
public class PaperRepositoryServiceImpl implements PaperRepositoryService {

	@Autowired
	private PaperRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Paper create(Paper paper) throws PaperCreationException {
		paper = repository.save(paper);
		
		if (paper == null) {
			throw new PaperCreationException(ExceptionConfig.PAPER_CREATION_ERROR_CODE, ExceptionConfig.PAPER_CREATION_EXCEPTION);
		}
		
		return paper;
	}
	
	public Paper update(Paper paper) throws PaperUpdationException {
		try {
			Paper tempPaper = repository.findByPaperDate(paper.getPaperDate());
			
			if (tempPaper == null) {
				throw new PaperNotFoundException(ExceptionConfig.PAPER_NOT_FOUND_ERROR_CODE, ExceptionConfig.PAPER_NOT_FOUND_EXCEPTION);
			}
			
			tempPaper.setContentIds(paper.getContentIds());
			
			tempPaper = repository.save(tempPaper);
			
			if (tempPaper == null) {
				throw new PaperUpdationException(ExceptionConfig.PAPER_UPDATION_ERROR_CODE, ExceptionConfig.PAPER_UPDATION_EXCEPTION);
			}
			
			return tempPaper;
		} catch (Exception exception) {
			logger.error("", exception);
			throw new PaperUpdationException(ExceptionConfig.PAPER_UPDATION_ERROR_CODE, ExceptionConfig.PAPER_UPDATION_EXCEPTION);
		}
	}
	
	public Paper findByDate(String paperDate) throws PaperNotFoundException {
		Paper paper = repository.findByPaperDate(paperDate);
		
		if (paper == null) {
			throw new PaperNotFoundException(ExceptionConfig.PAPER_NOT_FOUND_ERROR_CODE, ExceptionConfig.PAPER_NOT_FOUND_EXCEPTION);
		}
		
		return paper;
	}
}
