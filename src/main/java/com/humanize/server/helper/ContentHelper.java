package com.humanize.server.helper;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.stereotype.Component;

import com.humanize.server.data.Content;
import com.humanize.server.data.Contents;

@Component
public class ContentHelper {

	public ArrayList<String> getIds(Contents contents) {

		Iterator<Content> iterator = contents.getContents().iterator();
		ArrayList<String> ids = new ArrayList<String>();

		while (iterator.hasNext()) {
			Content content = iterator.next();
			ids.add(content.getContentId());
		}

		return ids;
	}
}
