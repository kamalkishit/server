package com.humanize.server.service;

import com.humanize.server.data.Content;
import com.humanize.server.data.ContentParams;
import com.humanize.server.data.ContentSearchParams;
import com.humanize.server.data.Contents;
import com.humanize.server.exception.ContentCreationException;
import com.humanize.server.exception.ContentNotFoundException;

public interface ContentService {

	Content create(String token, Content content) throws ContentCreationException;
	boolean upload(String token) throws Exception;
	Contents find(ContentSearchParams contentSearchParams) throws ContentNotFoundException;
	Contents findByUrlId(ContentParams contentParams) throws ContentNotFoundException;
}
