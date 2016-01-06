package com.humanize.server.service;

import java.util.List;

import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdationException;

public interface ContentService {

	Content create(Content content) throws ContentCreationException;
	void upload() throws Exception;
	Content update(Content content) throws ContentUpdationException;
	Contents findByCategories(List<String> categories, Long createdDate, boolean refresh) throws ContentNotFoundException;
	Contents findByIds(List<String> ids) throws ContentNotFoundException;
	Contents findBookmarks(List<String> bookmarkIds) throws ContentNotFoundException;
	Contents findRecommendations(List<String> recommendations) throws ContentNotFoundException;
	boolean recommendArticle(String contentUrl) throws Exception;
	boolean updateRecommendationCount(String contentId, boolean flag) throws ContentUpdationException;
	boolean incrViewedCount(String contentId) throws ContentUpdationException;
	boolean incrSharedCount(String contentId) throws ContentUpdationException;
}
