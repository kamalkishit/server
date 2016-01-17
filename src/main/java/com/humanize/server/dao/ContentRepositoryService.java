package com.humanize.server.dao;

import java.util.List;

import com.humanize.server.data.Content;
import com.humanize.server.data.Contents;
import com.humanize.server.exception.ContentCreationException;
import com.humanize.server.exception.ContentNotFoundException;
import com.humanize.server.exception.ContentUpdateException;


public interface ContentRepositoryService {
	
	Content create(Content content) throws ContentCreationException;
	List<Content> create(List<Content> contents) throws ContentCreationException;
	Content update(Content content) throws ContentUpdateException;
	Content findOne(String contentId) throws ContentNotFoundException;
	Contents findByCategories(List<String> categories) throws ContentNotFoundException;
	Contents findNewByCategories(List<String> categories, long createdDate) throws ContentNotFoundException;
	Contents findMoreByCategories(List<String> categories, long createdDate) throws ContentNotFoundException;
	Contents findByIds(List<String> ids) throws ContentNotFoundException;
	Content findByUrlId(String urlId) throws ContentNotFoundException;
}
