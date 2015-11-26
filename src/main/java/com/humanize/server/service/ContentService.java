package com.humanize.server.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.content.dao.ContentRepository;
import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.data.TempContent;
import com.humanize.server.content.data.TempContents;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdationException;
import com.humanize.server.content.service.ContentRepositoryService;
import com.humanize.server.content.service.HtmlScraperService;
import com.humanize.server.content.service.ImageDownloaderService;

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
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Content create(Content content) throws ContentCreationException {
		try {
			content = htmlScraperService.scrapHtml(content);
			imageDownloaderService.downloadImage(content);
			return repositoryService.create(content);
		} catch (Exception exception) {
			logger.error("", exception);
			throw new ContentCreationException(0, null);
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
