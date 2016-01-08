package com.humanize.server.service;

import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.PaperContentNotFoundException;
import com.humanize.server.data.Paper;
import com.humanize.server.exception.PaperCreationException;
import com.humanize.server.exception.PaperNotFoundException;
import com.humanize.server.exception.PaperUpdateException;

public interface PaperService {

	Paper create(Paper paper) throws PaperCreationException;
	Paper update(Paper paper) throws PaperUpdateException;
	Contents findByDate(String paperDate) throws PaperNotFoundException, PaperContentNotFoundException;
}
