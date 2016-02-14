package com.humanize.server.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.humanize.server.data.Content;
import com.humanize.server.data.Contents;
import com.humanize.server.exception.ContentCreationException;
import com.humanize.server.exception.ContentNotFoundException;
import com.humanize.server.exception.ContentUpdateException;
import com.humanize.server.exception.ErrorCodes;
import com.humanize.server.service.ContentServiceImpl;

@Service
public class ContentRepositoryServiceImpl implements ContentRepositoryService {

	@Autowired
	private ContentRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(ContentRepositoryServiceImpl.class);
	private static final String TAG = ContentRepositoryServiceImpl.class.getSimpleName();
	
	public Content create(Content content) throws ContentCreationException {
		try {
			content = repository.save(content);
			
			if (content == null) {
				throw new ContentCreationException(ErrorCodes.CONTENT_CREATION_ERROR);
			}
			
			return content;
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new ContentCreationException(ErrorCodes.CONTENT_CREATION_ERROR);
		}
	}
	
	public List<Content> create(List<Content> contents) throws ContentCreationException {
		contents = repository.save(contents);
		
		if (contents == null) {
			throw new ContentCreationException(ErrorCodes.CONTENT_CREATION_ERROR);
		}
		
		return contents;
	}
	
	public Content update(Content content) throws ContentUpdateException {
		try {
			repository.findOne(content.getId());
			
			content = repository.save(content);
			
			if (content == null) {
				throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
			}
			
			return content;
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new ContentUpdateException(ErrorCodes.CONTENT_UPDATE_ERROR);
		}
	}
	
	public Contents trends(long createdDate) throws ContentNotFoundException {
		Pageable pageRequest = new PageRequest(0, 5, new Sort(Direction.DESC, "contentWeight"));
		List<Content> contents = repository.trends(createdDate, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(ErrorCodes.CONTENT_NOT_FOUND_ERROR);
	}
	
	public Content findOne(String contentId) throws ContentNotFoundException {
		try {
			return repository.findOne(contentId);
		} catch (Exception exception) {
			logger.error(TAG, exception);
			throw new ContentNotFoundException(ErrorCodes.CONTENT_NOT_FOUND_ERROR);
		}
	}
	
	public Contents findByCategories(List<String> categories) throws ContentNotFoundException {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		List<Content> contents = repository.findByCategories(categories, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(ErrorCodes.CONTENT_NOT_FOUND_ERROR);
	}
	
	public Contents findNewByCategories(List<String> categories, long createdDate) throws ContentNotFoundException {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		List<Content> contents = repository.findNewByCategories(categories, createdDate, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(ErrorCodes.CONTENT_NOT_FOUND_ERROR);
	}
	
	public Contents findMoreByCategories(List<String> categories, long createdDate) throws ContentNotFoundException {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		List<Content> contents = repository.findMoreByCategories(categories, createdDate, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(ErrorCodes.CONTENT_NOT_FOUND_ERROR);
	}
	
	public Contents findByIds(List<String> ids) throws ContentNotFoundException {
		List<Content> contents = repository.findAll(ids);
		
		if (contents != null && contents.size() >0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(ErrorCodes.CONTENT_NOT_FOUND_ERROR);
	}
	
	public Content findByUrlId(String urlId) throws ContentNotFoundException {
		Content content = repository.findByUrlId(urlId);
		
		if (content != null) {
			return content;
		}
		
		throw new ContentNotFoundException(ErrorCodes.CONTENT_NOT_FOUND_ERROR);
	}
	
	private Pageable createPagination(Direction direction, String field) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(direction, field));
		
		return pageRequest;
	}
}
