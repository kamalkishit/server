package com.humanize.server.service;

import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.PaperContentNotFoundException;
import com.humanize.server.data.Paper;
import com.humanize.server.exception.PaperCreationException;
import com.humanize.server.exception.PaperNotFoundException;
import com.humanize.server.exception.PaperUpdationException;

public interface PaperService {

	public Paper create(Paper paper) throws PaperCreationException;
	public Paper update(Paper paper) throws PaperUpdationException;
	public Contents findByDate(String paperDate) throws PaperNotFoundException, PaperContentNotFoundException;
}
