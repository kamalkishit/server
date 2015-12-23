package com.humanize.server.service;

import com.humanize.server.data.Paper;
import com.humanize.server.exception.PaperCreationException;
import com.humanize.server.exception.PaperNotFoundException;
import com.humanize.server.exception.PaperUpdationException;

public interface PaperRepositoryService {
	
	public Paper create(Paper paper) throws PaperCreationException;
	public Paper update(Paper paper) throws PaperUpdationException;
	public Paper findByDate(String paperDate) throws PaperNotFoundException;
}
