package com.humanize.server.service;

import com.humanize.server.data.Content;
import com.humanize.server.data.ContentSearchParams;
import com.humanize.server.data.ContentUpdateParams;
import com.humanize.server.data.Contents;
import com.humanize.server.exception.ContentCreationException;
import com.humanize.server.exception.ContentNotFoundException;
import com.humanize.server.exception.ContentUpdateException;

public interface ContentService {

	Content create(String token, Content content) throws ContentCreationException;
	Content createManually(String token, Content content) throws ContentCreationException;
	boolean upload(String token) throws Exception;
	Contents trends() throws ContentNotFoundException;
 	Contents find(ContentSearchParams contentSearchParams) throws ContentNotFoundException;
	boolean update(ContentUpdateParams contentUpdateParams) throws ContentUpdateException;
	Contents findByUrlId(String contentId) throws ContentNotFoundException;
}
