package com.humanize.server.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.config.Config;
import com.humanize.server.dao.ContentRepositoryService;
import com.humanize.server.data.Content;
import com.humanize.server.data.ContentSearchParams;
import com.humanize.server.data.ContentUpdateParams;
import com.humanize.server.data.Contents;
import com.humanize.server.exception.ContentCreationException;
import com.humanize.server.exception.ContentNotFoundException;
import com.humanize.server.exception.ContentUpdateException;
import com.humanize.server.exception.ErrorCodes;

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
	
	private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);
	private static final String TAG = ContentServiceImpl.class.getSimpleName();

	public Content create(String token, Content content) throws ContentCreationException {
		try {
			content = htmlScraperService.scrapHtml(content);
			content.setType(Config.POSITIVE);
			imageDownloaderService.downloadImage(content);
			amazonS3Service.putImage(content);
			return repositoryService.create(content);
		} catch (ContentCreationException exception) {
			logger.error(TAG, exception);
			throw exception;
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new ContentCreationException(ErrorCodes.CONTENT_CREATION_ERROR);
		}
	}
	
	public Content createManually(String token, Content content) throws ContentCreationException {
		try {
			content = htmlScraperService.scrapHtmlManually(content);
			content.setType(Config.POSITIVE);
			imageDownloaderService.downloadImage(content);
			amazonS3Service.putImage(content);
			return repositoryService.create(content);
		} catch (ContentCreationException exception) {
			logger.error(TAG, exception);
			throw exception;
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new ContentCreationException(ErrorCodes.CONTENT_CREATION_ERROR);
		}
	}
	
	public boolean upload(String token) throws Exception {
		try {
			List<Content> contents = excelToJson.toJson(Config.EXCEL_FILE_PATH);
				
			for (Content content: contents) {
				createInBulk(token, content);
			}
		} catch (Exception exception) {
			logger.error(TAG, exception);
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
	
	public Contents trends() throws ContentNotFoundException {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -3);
		long createdDate = calendar.getTimeInMillis();
		
		return repositoryService.trends(createdDate);
		
	}
	
	public boolean update(ContentUpdateParams contentUpdateParams) throws ContentUpdateException {
		switch (contentUpdateParams.getContentUpdateOperations()) {
			case VIEW:
				return incrViewedCount(contentUpdateParams.getContentId());
			case SHARE:
				return incrSharedCount(contentUpdateParams.getContentId());
			case UPVOTE:
				return incrUpvotedCount(contentUpdateParams.getContentId());
			case DOWNVOTE:
				return decrUpvotedCount(contentUpdateParams.getContentId());
			default: 
				return false;
		}
	}
	
	public Contents findByUrlId(String contentId) throws ContentNotFoundException {
		Content content = repositoryService.findByUrlId(contentId);
		List<Content> contents = new ArrayList<>();
		contents.add(content);
		return new Contents(contents);
	}
	
	private boolean incrViewedCount(String contentId) throws ContentUpdateException {
		try {
			Content content = repositoryService.findOne(contentId);
			content.setViewedCount(content.getViewedCount() + 1);
			content.setContentWeight(content.getContentWeight() + Config.VIEW_WEIGHT);
			repositoryService.update(content);
			return true;
		} catch (ContentNotFoundException exception) {
			throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
		} catch (Exception exception) {
			throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
		}
	}
	
	private boolean incrUpvotedCount(String contentId) throws ContentUpdateException {
		try {
			Content content = repositoryService.findOne(contentId);
			content.setUpvotedCount(content.getUpvotedCount() + 1);
			content.setViewedCount(content.getViewedCount() + 1);
			content.setContentWeight(content.getContentWeight() + Config.UPVOTE_WEIGHT + Config.VIEW_WEIGHT);
			repositoryService.update(content);
			return true;
		} catch (ContentNotFoundException exception) {
			throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
		} catch (Exception exception) {
			throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
		}
	}
	
	private boolean decrUpvotedCount(String contentId) throws ContentUpdateException {
		try {
			Content content = repositoryService.findOne(contentId);
			content.setUpvotedCount(content.getUpvotedCount() - 1);
			if ((content.getContentWeight() - Config.UPVOTE_WEIGHT) > 0) {
				content.setContentWeight(content.getContentWeight() - Config.UPVOTE_WEIGHT);
			}
			repositoryService.update(content);
			return true;
		} catch (ContentNotFoundException exception) {
			throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
		} catch (Exception exception) {
			throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
		}
	}
	
	private boolean incrSharedCount(String contentId) throws ContentUpdateException {
		try {
			Content content = repositoryService.findOne(contentId);
			content.setSharedCount(content.getSharedCount() + 1);
			content.setViewedCount(content.getViewedCount() + 1);
			content.setContentWeight(content.getContentWeight() + Config.SHARE_WEIGHT + Config.VIEW_WEIGHT);
			repositoryService.update(content);
			return true;
		} catch (ContentNotFoundException exception) {
			throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
		} catch (Exception exception) {
			throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
		}
	}
	
	private void createInBulk(String token, Content content) {
		try {
			content = htmlScraperService.scrapHtml(content);
			content.setType(Config.POSITIVE);
			imageDownloaderService.downloadImage(content);
			amazonS3Service.putImage(content);
			repositoryService.create(content);
		} catch (Exception exception) {
			logger.error(TAG, exception);
		}
	}
}
