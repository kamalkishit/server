package com.humanize.server.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.authentication.service.EmailService;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.config.Config;
import com.humanize.server.content.dao.ContentRepository;
import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdationException;
import com.humanize.server.content.service.ContentRepositoryService;
import com.humanize.server.content.service.HtmlScraperService;
import com.humanize.server.content.service.ImageDownloaderService;
import com.humanize.server.util.ExcelToJson;

@Service
public class ContentService {
	
	@Autowired
	private ContentRepositoryService repositoryService;
	
	@Autowired
	private ContentRepository repository;
	
	@Autowired
	private HtmlScraperService htmlScraperService;
	
	@Autowired
	private ImageDownloaderService imageDownloaderService;
	
	@Autowired
	private EmailService emailService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Content create(Content content) throws ContentCreationException {
		try {
			content = htmlScraperService.scrapHtml(content);
			imageDownloaderService.downloadImage(content);
			return repositoryService.create(content);
		} catch (ContentCreationException exception) {
			throw exception;
		} catch (Exception exception) {
			logger.error("", exception);
			exception.printStackTrace();
			 new ContentCreationException(ExceptionConfig.CONTENT_CREATION_ERROR_CODE, ExceptionConfig.CONTENT_CREATION_EXCEPTION);
		}
		
		return null;
	}
	
	public void upload() {
		ExcelToJson excelToJson = new ExcelToJson(Config.EXCEL_FILE_PATH);
		List<Content> contents = excelToJson.toJson();
		try {
			for (Content content: contents) {
				create(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Content update(Content content) throws ContentUpdationException {
		return repositoryService.update(content);
	}
	
	public Contents findByCategories(List<String> categories, Long createdDate, boolean refresh) throws ContentNotFoundException {
		if (createdDate == null) {
			return repositoryService.findByCategories(categories);
		} else if (refresh) {
			return repositoryService.findNewByCategories(categories, createdDate);
		} else {
			return repositoryService.findMoreByCategories(categories, createdDate);
		}
	}
	
	public Contents findByIds(List<String> ids) throws ContentNotFoundException {
			return repositoryService.findByIds(ids);
	}
	
	public boolean recommendArticle(String contentUrl) throws Exception {
		try {
			emailService.sendEmail("pandey.kishore@gmail.com", contentUrl);
			return true;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}
	
	public boolean updateRecommendationCount(String contentId, boolean flag) throws Exception {
		Content content = repositoryService.findOne(contentId);
		
		if (flag) {
			content.setRecommendationCount(content.getRecommendationCount() + 1);
		} else {
			content.setRecommendationCount(content.getRecommendationCount() - 1);
		}
		
		repositoryService.update(content);
		return true;
	}
	
	public boolean incrViewedCount(String contentId) throws Exception {
		Content content = repositoryService.findOne(contentId);
		content.setViewsCount(content.getViewsCount() + 1);
		repositoryService.update(content);
		return true;
	}
	
	public boolean incrSharedCount(String contentId) throws Exception {
		Content content = repositoryService.findOne(contentId);
		content.setSharedCount(content.getSharedCount() + 1);
		repositoryService.update(content);
		return true;
	}

/*
	
	public List<Content> findByCategory(String category) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		return repository.findAllByCategory(category, pageRequest);
	}
	
	public List<Content> findByCategoryCreatedDateLessThan(String category, long createdDate) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		return repository.findAllByCategoryCreatedDateLessThan(category, createdDate, pageRequest);
	}
	
	public List<Content> findByCategories(ArrayList<String> categories) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		return repository.findAllByCategories(categories, pageRequest);
	}

	public ArrayList<Content> getContent() {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		Page<Content> contents = repository.findAll(pageRequest);

		if (contents != null) {
			return new ArrayList<Content>(contents.getContent());
		}

		return null;
	}
	
	public ArrayList<Content> getContents(List<String> categories) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(new Order(
				Direction.DESC, "createdDate")));
		List<Content> contents = repository.findAllByCategories(categories, pageRequest);

		if (contents != null) {
			return new ArrayList<Content>(contents);
		}

		return null;
	}

	public List<Content> getMoreContent(long startDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		List<Content> contents = repository.findByCreatedDateLessThan(
				startDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}
	
	public List<Content> getMoreContents(List<String> categories, long startDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		List<Content> contents = repository.findAllByCategoriesCreatedDateLessThan(categories, 
				startDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}

	public List<Content> getNewContent(long endDate) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(Direction.DESC,
				"createdDate"));
		List<Content> contents = repository.findByCreatedDateGreaterThan(
				endDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}
	
	public List<Content> getNewContents(List<String> categories, long endDate) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		List<Content> contents = repository.findAllByCategoriesCreatedDateGreaterThan(categories, 
				endDate, pageRequest);

		if (contents != null) {
			return contents;
		}

		return null;
	}
	
	private Pageable createPagination(Direction direction, String field) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(direction,
				field));
		
		return pageRequest;
	}*/
}
