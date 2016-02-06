package com.humanize.server.service;

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

import com.humanize.server.config.Config;
import com.humanize.server.data.Content;
import com.humanize.server.exception.ErrorCodes;
import com.humanize.server.exception.HtmlPropertyContentNotFoundException;
import com.humanize.server.exception.HtmlPropertyNotFoundException;
import com.humanize.server.exception.HtmlScrapException;
import com.humanize.server.helper.InternetHelper;
import com.humanize.server.helper.RandomNumberGenerator;

@Service
public class HtmlScraperServiceImpl implements HtmlScraperService{

	private Document document;
	
	@Autowired
	UrlShortnerService urlShortner;
	
	@Autowired
	RandomNumberGenerator randomNumberGenerator;
	
	@Autowired
	InternetHelper internetHelper;
	
	private static final Logger logger = LoggerFactory.getLogger(HtmlScraperServiceImpl.class);
	private static final String TAG = HtmlScraperServiceImpl.class.getSimpleName();
	
	public Content scrapHtml(Content content) throws HtmlScrapException {
		try {
			createConnection(content.getOriginalUrl());
			content.setContentId(createUniqueId());
			content.setTitle(scrapTitle());
			content.setUrlId(createUrl(content.getTitle(), content.getContentId()));
			content.setUrl(Config.URL_CONTENT + content.getUrlId());
			content.setShortUrl(urlShortner.getShortUrl(content.getUrl()));
			content.setDescription(scrapDescription());
			content.setSource(scrapSource());
			content.setOriginalImageUrl(scrapImageURL());
			return content;
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new HtmlScrapException(ErrorCodes.HTML_SCRAP_ERROR);
		}
	}
	
	public List<Content> scrapHtml(List<Content> contents) throws HtmlScrapException {
		List<Content> contentList = new ArrayList<Content>();
		
		for (Content content : contents) {
			contentList.add(scrapHtml(content));
		}
		
		return contentList;
	}
	
	private long createUniqueId() {
		long uniqueId = new Timestamp(new Date().getTime()).getTime();
		int randomNumber = randomNumberGenerator.randInt(0, 9)%10;
		int ipDigits = internetHelper.getIpAddressIntValue()%100;
		
		if (ipDigits < 10) {
			ipDigits = ipDigits*10;
		}
		
		uniqueId = uniqueId*1000 + randomNumber*100 + ipDigits;
		
		return uniqueId;
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
			logger.error(TAG, exception);
			throw exception;
		}
	}
	
	private String scrapProperty(String property) throws Exception {
		try {
			Elements element = document.select(property);
			
			if (element != null && element.first() != null) {
				return element.first().attr("content");
			} else if (element != null){
				throw new HtmlPropertyContentNotFoundException(ErrorCodes.HTML_PROPERTY_CONTENT_NOT_FOUND_ERROR);
			} else {
				throw new HtmlPropertyNotFoundException(ErrorCodes.HTML_PROPERTY_NOT_FOUND_ERROR);
			}
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw exception;
		}
	}
	
	private String scrapPropertyWithoutException(String property) {
		try {
			Elements element = document.select(property);
			
			if (element != null && element.first() != null) {
				return element.first().attr("content");
			} 
			
			return null;
		} catch (Exception exception) {
			logger.error(TAG, exception);
			return null;
		}
	}
	
	private String scrapTitle() throws Exception {
		return scrapProperty("meta[property=og:title]");
	}
	
	private String scrapDescription() throws Exception {
		return scrapProperty("meta[property=og:description]");
	}
	
	private String scrapSource() {
		return scrapPropertyWithoutException("meta[property=og:site_name]");
	}
	
	private String scrapImageURL() throws Exception {
		return scrapProperty("meta[property=og:image]");
	}
}
