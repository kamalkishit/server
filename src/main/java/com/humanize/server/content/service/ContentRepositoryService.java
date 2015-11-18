package com.humanize.server.content.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.humanize.server.content.data.Contents;
import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.content.dao.ContentRepository;
import com.humanize.server.content.data.Content;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdationException;

@Service
public class ContentRepositoryService {
	
	@Autowired
	ContentRepository repository;
	
	public Content create(Content content) {
		content = repository.save(content);
		
		if (content == null) {
			throw new ContentCreationException(ExceptionConfig.CONTENT_CREATION_ERROR_CODE, ExceptionConfig.CONTENT_CREATION_EXCEPTION);
		}
		
		return content;
	}
	
	public List<Content> create(List<Content> contents) {
		contents = repository.save(contents);
		
		if (contents == null) {
			throw new ContentCreationException(ExceptionConfig.CONTENT_CREATION_ERROR_CODE, ExceptionConfig.CONTENT_CREATION_EXCEPTION);
		}
		
		return contents;
	}
	
	public Content update(Content content) {
		content = repository.save(content);
		
		if (content == null) {
			throw new ContentUpdationException(ExceptionConfig.CONTENT_UPDATION_ERROR_CODE, ExceptionConfig.CONTENT_UPDATION_EXCEPTION);
		}
		
		return content;
	}
	
	public Contents findByCategories(List<String> categories) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		List<Content> contents = repository.findByCategories(categories, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(ExceptionConfig.CONTENT_NOT_FOUND_ERROR_CODE, ExceptionConfig.CONTENT_NOT_FOUND_EXCEPTION);
	}
	
	public Contents findNewByCategories(List<String> categories, long createdDate) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		List<Content> contents = repository.findNewByCategories(categories, createdDate, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(ExceptionConfig.CONTENT_NOT_FOUND_ERROR_CODE, ExceptionConfig.CONTENT_NOT_FOUND_EXCEPTION);
	}
	
	public Contents findMoreByCategories(List<String> categories, long createdDate) {
		Pageable pageRequest = createPagination(Direction.DESC, "createdDate");
		
		List<Content> contents = repository.findMoreByCategories(categories, createdDate, pageRequest);
		
		if (contents != null && contents.size() > 0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(ExceptionConfig.CONTENT_NOT_FOUND_ERROR_CODE, ExceptionConfig.CONTENT_NOT_FOUND_EXCEPTION);
	}
	
	public Contents findByIds(List<String> ids) {
		List<Content> contents = repository.findAll(ids);
		
		if (contents != null && contents.size() >0) {
			return new Contents(contents);
		}
		
		throw new ContentNotFoundException(ExceptionConfig.CONTENT_NOT_FOUND_ERROR_CODE, ExceptionConfig.CONTENT_NOT_FOUND_EXCEPTION);
	}
	
	private Pageable createPagination(Direction direction, String field) {
		Pageable pageRequest = new PageRequest(0, 20, new Sort(direction,
				field));
		
		return pageRequest;
	}
}
