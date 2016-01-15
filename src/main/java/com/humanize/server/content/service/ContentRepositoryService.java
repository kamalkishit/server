package com.humanize.server.content.service;

import java.util.List;

import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdateException;


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
