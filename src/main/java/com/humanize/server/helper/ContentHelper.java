package com.humanize.server.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.humanize.server.content.data.Content;
import com.humanize.server.content.data.Contents;

@Component
public class ContentHelper {

	public List<String> getIds(Contents contents) {

		Iterator<Content> iterator = contents.getContents().iterator();
		List<String> ids = new ArrayList<String>();

		while (iterator.hasNext()) {
			Content content = iterator.next();
			ids.add(content.getContentId());
		}

		return ids;
	}
}
