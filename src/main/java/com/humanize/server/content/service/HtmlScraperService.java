package com.humanize.server.content.service;

import java.util.List;

import com.humanize.server.content.data.Content;
import com.humanize.server.exception.HtmlScrapException;

public interface HtmlScraperService {
	
	Content scrapHtml(Content content) throws HtmlScrapException;
	List<Content> scrapHtml(List<Content> contents) throws HtmlScrapException;
}
