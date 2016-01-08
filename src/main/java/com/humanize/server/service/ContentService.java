package com.humanize.server.service;

import java.util.List;

import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdateException;

public interface ContentService {

	Content create(String token, Content content) throws ContentCreationException;
	void upload(String token) throws Exception;
	Content update(String token, Content content) throws ContentUpdateException;
	Contents findByCategories(String token, Long createdDate, boolean refresh) throws ContentNotFoundException;
	Contents findByIds(String token, List<String> ids) throws ContentNotFoundException;
	Contents findBookmarks(String token, List<String> bookmarkIds) throws ContentNotFoundException;
	Contents findRecommendations(String token, List<String> recommendations) throws ContentNotFoundException;
	boolean recommendArticle(String token, String contentUrl) throws Exception;
	boolean updateRecommendationCount(String token, String contentId, boolean flag) throws ContentUpdateException;
	boolean incrViewedCount(String token, String contentId) throws ContentUpdateException;
	boolean incrSharedCount(String token, String contentId) throws ContentUpdateException;
}
