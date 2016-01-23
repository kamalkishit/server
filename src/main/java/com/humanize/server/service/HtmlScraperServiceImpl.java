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

import com.humanize.server.data.Content;
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
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Content scrapHtml(Content content) throws HtmlScrapException {
		try {
			createConnection(content.getOriginalUrl());
			content.setContentId(createUniqueId());
			content.setTitle(scrapTitle());
			content.setUrlId(createUrl(content.getTitle(), content.getContentId()));
			content.setUrl("http://humannize.com/content/" + content.getUrlId());
			content.setShortUrl(urlShortner.getShortUrl(content.getOriginalUrl()));
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
	
	private long createUniqueId() {
		long uniqueId = new Timestamp(new Date().getTime()).getTime();
		System.out.println(uniqueId);
		int randomNumber = randomNumberGenerator.randInt(0, 9)%10;
		System.out.println(randomNumber);
		int ipDigits = internetHelper.getIpAddressIntValue()%100;
		
		if (ipDigits < 10) {
			ipDigits = ipDigits*10;
		}
		
		System.out.println(ipDigits);
		uniqueId = uniqueId*1000 + randomNumber*100 + ipDigits;
		
		System.out.println(uniqueId);
		
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
				throw new HtmlPropertyContentNotFoundException(0, null);
			} else {
				throw new HtmlPropertyNotFoundException(0, null);
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
