package com.humanize.server.service;

import com.humanize.server.data.Paper;
import com.humanize.server.exception.PaperCreationException;
import com.humanize.server.exception.PaperNotFoundException;
import com.humanize.server.exception.PaperUpdationException;

public interface PaperRepositoryService {
	
	Paper create(Paper paper) throws PaperCreationException;
	Paper update(Paper paper) throws PaperUpdationException;
	Paper findByDate(String paperDate) throws PaperNotFoundException;
}
