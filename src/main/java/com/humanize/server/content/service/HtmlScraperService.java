package com.humanize.server.content.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector.SelectorParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.content.data.Content;
import com.humanize.server.exception.HtmlParseException;
import com.humanize.server.exception.HtmlPropertyContentNotFoundException;
import com.humanize.server.exception.HtmlPropertyNotFoundException;
import com.humanize.server.exception.HtmlPropertyParseException;
import com.humanize.server.exception.UrlConnectionException;

@Service
public class HtmlScraperService {
	
	private Document document;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Content scrapHtml(Content content) throws HtmlParseException {
		try {
			createConnection(content.getContentURL());

			content.setContentId(UUID.randomUUID().toString());
			content.setTitle(scrapTitle());
			content.setDescription(scrapDescription());
			content.setSource(scrapSource());
			content.setOriginalImageURL(scrapImageURL());
		} catch (UrlConnectionException exception) {
			lo
			throw new HtmlParseException(exception.getErrorCode(), exception.getErrorMsg());
		} catch (HtmlPropertyParseException exception) {
			
		} catch (HtmlPropertyNotFoundException exception) {
			
		} catch (HtmlPropertyContentNotFoundException exception) {
			
		}

		
		return content;
	}
	
	public List<Content> scrapHtml(List<Content> contents) throws HtmlParseException {
		List<Content> contentList = new ArrayList<Content>();
		
		for (Content content : contents) {
			contentList.add(scrapHtml(content));
		}
		
		return contentList;
	}
	
	private void createConnection(String url) throws UrlConnectionException {
		try {
			document = Jsoup.connect(url).userAgent("Mozilla").get();
		} catch (IOException exception) {
			logger.error(exception.toString());
			throw new UrlConnectionException(ExceptionConfig.URL_CONNECTION_ERROR_CODE, ExceptionConfig.URL_CONNECTION_EXCEPTION);
		}
	}
	
	private String scrapProperty(Document document, String property) 
			throws HtmlPropertyContentNotFoundException, HtmlPropertyNotFoundException, HtmlPropertyParseException {
		try {
			Elements element = document.select(property);
			
			if (element != null && element.first() != null) {
				return element.first().attr("content");
			} else if (element != null){
				throw new HtmlPropertyContentNotFoundException(ExceptionConfig.HTML_PROPERTY_CONTENT_NOT_FOND_ERROR_CODE, ExceptionConfig.HTML_PROPERTY_CONTENT_NOT_FOUND_EXCEPTION);
			} else {
				throw new HtmlPropertyNotFoundException(ExceptionConfig.HTML_PROPERTY_NOT_FOUND_ERROR_CODE, ExceptionConfig.HTML_PROPERTY_NOT_FOUND_EXCEPTION);
			}
		} catch (SelectorParseException exception) {
			logger.error(exception.toString());
			throw new HtmlPropertyParseException(ExceptionConfig.HTML_PROPERTY_PARSE_ERROR_CODE, ExceptionConfig.HTML_PROPERTY_PARSE_EXCEPTION);
			
		}
	}
	
	private String scrapTitle() 
			throws HtmlPropertyNotFoundException, HtmlPropertyContentNotFoundException, HtmlPropertyParseException {
		return scrapProperty(document, "meta[property=og:title]");
	}
	
	private String scrapDescription() 
			throws HtmlPropertyNotFoundException, HtmlPropertyContentNotFoundException, HtmlPropertyParseException {
		return scrapProperty(document, "meta[property=og:description]");
	}
	
	private String scrapSource() 
			throws HtmlPropertyNotFoundException, HtmlPropertyContentNotFoundException, HtmlPropertyParseException {
		return scrapProperty(document, "meta[property=og:site_name]");
	}
	
	private String scrapImageURL() 
			throws HtmlPropertyNotFoundException, HtmlPropertyContentNotFoundException, HtmlPropertyParseException {
		return scrapProperty(document, "meta[property=og:image]");
	}
}
