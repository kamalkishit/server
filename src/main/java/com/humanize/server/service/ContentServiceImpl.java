package com.humanize.server.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.config.Config;
import com.humanize.server.dao.ContentRepositoryService;
import com.humanize.server.data.Content;
import com.humanize.server.data.ContentParams;
import com.humanize.server.data.ContentSearchParams;
import com.humanize.server.data.Contents;
import com.humanize.server.exception.ContentCreationException;
import com.humanize.server.exception.ContentNotFoundException;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentRepositoryService repositoryService;
	
	@Autowired
	private HtmlScraperService htmlScraperService;
	
	@Autowired
	private ImageDownloaderService imageDownloaderService;
	
	@Autowired
	private AmazonS3Service amazonS3Service;
	
	@Autowired
	ExcelToJsonService excelToJson;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Content create(String token, Content content) throws ContentCreationException {
		try {
			content = htmlScraperService.scrapHtml(content);
			content.setType(Config.POSITIVE);
			imageDownloaderService.downloadImage(content);
			amazonS3Service.putImage(content);
			return repositoryService.create(content);
		} catch (ContentCreationException exception) {
			throw exception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ContentCreationException(0, null);
		}
	}
	
	public boolean upload(String token) throws Exception {
		try {
			List<Content> contents = excelToJson.toJson(Config.EXCEL_FILE_PATH);
				
			for (Content content: contents) {
				createInBulk(token, content);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Contents find(ContentSearchParams contentSearchParams) throws ContentNotFoundException {
		if (contentSearchParams.getCreatedDate() != 0 && contentSearchParams.getRefresh()) {
			return repositoryService.findNewByCategories(contentSearchParams.getCategories(), contentSearchParams.getCreatedDate());
		} else if (contentSearchParams.getCreatedDate() != 0) {
			return repositoryService.findMoreByCategories(contentSearchParams.getCategories(), contentSearchParams.getCreatedDate());
		} else {
			return repositoryService.findByCategories(contentSearchParams.getCategories());
		}
	}
	
	public Contents findByUrlId(String contentId) throws ContentNotFoundException {
		Content content = repositoryService.findByUrlId(contentId);
		List<Content> contents = new ArrayList<>();
		contents.add(content);
		return new Contents(contents);
	}
	
	private void createInBulk(String token, Content content) {
		try {
			content = htmlScraperService.scrapHtml(content);
			content.setType(Config.POSITIVE);
			imageDownloaderService.downloadImage(content);
			amazonS3Service.putImage(content);
			repositoryService.create(content);
		} catch (Exception exception) {
			
		}
	}
}
