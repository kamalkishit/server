package com.humanize.server.helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.humanize.server.data.Content;
import com.humanize.server.data.Contents;

@Component
public class ContentHelper {

	public List<Long> getIds(Contents contents) {

		Iterator<Content> iterator = contents.getContents().iterator();
		List<Long> ids = new ArrayList<>();

		while (iterator.hasNext()) {
			Content content = iterator.next();
			ids.add(content.getContentId());
		}

		return ids;
	}
}
