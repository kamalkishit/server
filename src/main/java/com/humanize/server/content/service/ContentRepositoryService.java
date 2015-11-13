package com.humanize.server.content.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humanize.server.common.ExceptionConfig;
import com.humanize.server.content.dao.ContentRepository;
import com.humanize.server.content.data.Content;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdationException;

@Service
public class ContentRepositoryService {
	
	@Autowired
	private ContentRepository repository;
	
	public Content create(Content content) {
		content = repository.save(content);
		
		if (content != null) {
			return content;
		}
		
		throw new ContentCreationException(ExceptionConfig.CONTENT_CREATION_ERROR_CODE, ExceptionConfig.CONTENT_CREATION_EXCEPTION);
	}
	
	public ArrayList<Content> create(ArrayList<Content> contents) {
		contents = repository.save(contents);
		
		if (contents != null) {
			return contents;
		}
		
		throw new ContentCreationException(ExceptionConfig.CONTENT_CREATION_ERROR_CODE, ExceptionConfig.CONTENT_CREATION_EXCEPTION);
	}
	
	public Content update(Content content) {
		content = repository.save(content);
		
		if (content != null) {
			return content;
		}
		
		throw new ContentUpdationException(ExceptionConfig.CONTENT_UPDATION_ERROR_CODE, ExceptionConfig.CONTENT_UPDATION_EXCEPTION);
	}
	
	public Content findByEmailId(String emailId) {
		Content content = repository.findByEmailId(emailId);
		
		if (content != null) {
			return content;
		}
		
		throw new ContentNotFoundException(ExceptionConfig.CONTENT_NOT_FOUND_ERROR_CODE, ExceptionConfig.CONTENT_NOT_FOUND_EXCEPTION);
	}
	
	public ArrayList<Content> findAll(List<String> ids) {
		return null;
	}
	
	public void deleteByEmailId(String emailId) {
		Content content = findByEmailId(emailId);
		repository.delete(content);
	}
	
	public void delete(Content content) {
		repository.delete(content);
	}
}
