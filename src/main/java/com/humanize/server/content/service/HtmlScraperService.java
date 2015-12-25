package com.humanize.server.content.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.content.data.Content;
import com.humanize.server.exception.HtmlPropertyContentNotFoundException;
import com.humanize.server.exception.HtmlPropertyNotFoundException;


@Service
public class HtmlScraperService {
	
	private Document document;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Content scrapHtml(Content content) throws Exception {
		try {
			createConnection(content.getUrl());
			content.setUserId("pandey.kishore@gmail.com");
			content.setContentId(UUID.randomUUID().toString());
			content.setTitle(scrapTitle());
			content.setDescription(scrapDescription());
			content.setSource(scrapSource());
			content.setOriginalImageURL(scrapImageURL());
			return content;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}
	
	public List<Content> scrapHtml(List<Content> contents) throws Exception {
		List<Content> contentList = new ArrayList<Content>();
		
		for (Content content : contents) {
			contentList.add(scrapHtml(content));
		}
		
		return contentList;
	}
	
	private void createConnection(String url) throws Exception {
		try {
			document = Jsoup.connect(url).userAgent("Mozilla").get();
		} catch (Exception exception) {
			logger.error("", exception);
			throw exception;
		}
	}
	
	private String scrapProperty(Document document, String property) 
			throws Exception {
		try {
			Elements element = document.select(property);
			
			if (element != null && element.first() != null) {
				return element.first().attr("content");
			} else if (element != null){
				throw new HtmlPropertyContentNotFoundException(ExceptionConfig.HTML_PROPERTY_CONTENT_NOT_FOND_ERROR_CODE, ExceptionConfig.HTML_PROPERTY_CONTENT_NOT_FOUND_EXCEPTION);
			} else {
				throw new HtmlPropertyNotFoundException(ExceptionConfig.HTML_PROPERTY_NOT_FOUND_ERROR_CODE, ExceptionConfig.HTML_PROPERTY_NOT_FOUND_EXCEPTION);
			}
		} catch (Exception exception) {
			logger.error("", exception);
			throw exception;
		}
	}
	
	private String scrapTitle() 
			throws Exception {
		return scrapProperty(document, "meta[property=og:title]");
	}
	
	private String scrapDescription() 
			throws Exception {
		return scrapProperty(document, "meta[property=og:description]");
	}
	
	private String scrapSource() 
			throws Exception {
		return scrapProperty(document, "meta[property=og:site_name]");
	}
	
	private String scrapImageURL() 
			throws Exception {
		return scrapProperty(document, "meta[property=og:image]");
	}
}
