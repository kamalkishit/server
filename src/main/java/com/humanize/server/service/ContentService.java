package com.humanize.server.service;

import java.util.List;

import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;
import com.humanize.server.content.exception.ContentCreationException;
import com.humanize.server.content.exception.ContentNotFoundException;
import com.humanize.server.content.exception.ContentUpdationException;

public interface ContentService {

	public Content create(Content content) throws ContentCreationException;
	public void upload() throws Exception;
	public Content update(Content content) throws ContentUpdationException;
	public Contents findByCategories(List<String> categories, Long createdDate, boolean refresh) throws ContentNotFoundException;
	public Contents findByIds(List<String> ids) throws ContentNotFoundException;
	public Contents findBookmarks(List<String> bookmarkIds) throws Exception;
	public boolean recommendArticle(String contentUrl) throws Exception;
	public boolean updateRecommendationCount(String contentId, boolean flag) throws Exception;
	public boolean incrViewedCount(String contentId) throws Exception;
	public boolean incrSharedCount(String contentId) throws Exception;
}
