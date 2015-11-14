package com.humanize.server.content.service;

import java.io.IOException;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector.SelectorParseException;

import com.humanize.server.content.data.Content;

public class HtmlScrapperService {
	
	private Document document;
	
	public Content scrapHtml(Content content) {
		createConnection(content.getContentURL());

		content.setTitle(scrapTitle());
		content.setDescription(scrapDescription());
		content.setSource(scrapSource());
		content.setOriginalImageURL(scrapImageURL());
		
		return content;
	}
	
	private void createConnection(String url) {
		try {
			document = Jsoup.connect(url).userAgent("Mozilla").get();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	private String scrapProperty(Document document, String property) {
		try {
			Elements element = document.select(property);
			
			if (element != null && element.first() != null) {
				return element.first().attr("content");
			} 
		} catch (SelectorParseException exception) {
			exception.printStackTrace();
		}
		
		return null;
	}
	
	private String scrapTitle() {
		return scrapProperty(document, "meta[property=og:title]");
	}
	
	private String scrapDescription() {
		return scrapProperty(document, "meta[property=og:description]");
	}
	
	private String scrapSource() {
		return scrapProperty(document, "meta[property=og:site_name]");
	}
	
	private String scrapImageURL() {
		return scrapProperty(document, "meta[property=og:image]");
	}
}
