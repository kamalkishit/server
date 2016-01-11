package com.humanize.server.content.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.content.data.Content;
import com.humanize.server.exception.HtmlPropertyContentNotFoundException;
import com.humanize.server.exception.HtmlPropertyNotFoundException;
import com.humanize.server.exception.HtmlScrapException;
import com.humanize.server.service.UrlShortner;

@Service
public class HtmlScraperServiceImpl implements HtmlScraperService{

	private Document document;
	
	@Autowired
	UrlShortner urlShortner;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Content scrapHtml(Content content) throws HtmlScrapException {
		try {
			createConnection(content.getOriginalUrl());
			content.setContentId(new Timestamp(new Date().getTime()).getTime());
			content.setTitle(scrapTitle());
			//content.setUrl("http://humannize.com/content/" + createUrl(content.getTitle(), content.getContentId()));
			content.setUrl(content.getOriginalUrl());
			content.setShortUrl(urlShortner.getShortUrl(content.getUrl()));
			content.setDescription(scrapDescription());
			content.setSource(scrapSource());
			content.setOriginalImageUrl(scrapImageURL());
			return content;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new HtmlScrapException(0, null);
		}
	}
	
	public List<Content> scrapHtml(List<Content> contents) throws HtmlScrapException {
		List<Content> contentList = new ArrayList<Content>();
		
		for (Content content : contents) {
			contentList.add(scrapHtml(content));
		}
		
		return contentList;
	}
	
	private String createUrl(String str, long contentId) {
		String delims = " ";
		StringTokenizer stringTokenizer = new StringTokenizer(str, delims);
		String urlString = "";
		while(stringTokenizer.hasMoreElements()) {
			urlString += stringTokenizer.nextElement() + "-";			
		}
		
		return urlString + contentId;
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
