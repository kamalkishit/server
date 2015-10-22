package com.humanize.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.dao.PaperRepository;
import com.humanize.server.data.Paper;

@Service
public class PaperService {

	@Autowired
	private PaperRepository repository;

	public List<String> findContentIdsByPaperDate(String paperDate) {
		
		Paper paper = repository.findByPaperDate(paperDate);
		
		if (paper != null) {
			return paper.getContentIds();
		}
		
		return null;
	}
	
	public Paper createPaper(Paper paper) {
		return repository.save(paper);
	}
}
