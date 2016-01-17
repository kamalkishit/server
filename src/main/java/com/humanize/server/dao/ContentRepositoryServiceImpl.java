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

@Service
public class ContentRepositoryServiceImpl implements ContentRepositoryService {

	@Autowired
	private ContentRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Content create(Content content) throws ContentCreationException {
		try {
			content = repository.save(content);
			
			if (content == null) {
				logger.error("content is null");
				throw new ContentCreationException(0, null);
			}
			
			return content;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ContentCreationException(0, null);
		}
	}
	
	public List<Content> create(List<Content> contents) throws ContentCreationException {
		contents = repository.save(contents);
		
		if (contents == null) {
			throw new ContentCreationException(0, null);
		}
		
		return contents;
	}
	
	public Content update(Content content) throws ContentUpdateException {
		try {
			repository.findOne(content.getId());
			
			content = repository.save(content);
			
			if (content == null) {
				throw new ContentUpdateException(0, null);
			}
			
			return content;
		} catch (Exception exception) {
			throw new ContentUpdateException(0, null);
		}
	}
	
	public Content findOne(String contentId) throws ContentNotFoundException {
		try {
			return repository.findOne(contentId);
		} catch (Exception exception) {
			throw new ContentNotFoundException(0, null);
		}
	}
	
	public Contents findByCategories(List<String> categories) throws ContentNotFoundException {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		List<Content> contents = repository.findByCategories(categories, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(0, null);
	}
	
	public Contents findNewByCategories(List<String> categories, long createdDate) throws ContentNotFoundException {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		List<Content> contents = repository.findNewByCategories(categories, createdDate, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(0, null);
	}
	
	public Contents findMoreByCategories(List<String> categories, long createdDate) throws ContentNotFoundException {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		List<Content> contents = repository.findMoreByCategories(categories, createdDate, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(0, null);
	}
	
	public Contents findByIds(List<String> ids) throws ContentNotFoundException {
		List<Content> contents = repository.findAll(ids);
		
		if (contents != null && contents.size() >0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(0, null);
	}
	
	public Content findByUrlId(String urlId) throws ContentNotFoundException {
		return repository.findByUrlId(urlId);
	}
	
	private Pageable createPagination(Direction direction, String field) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(direction,
				field));
		
		return pageRequest;
	}
}
