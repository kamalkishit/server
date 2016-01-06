package com.humanize.server.content.data;

import java.util.ArrayList;
import java.util.List;

import com.humanize.server.content.data.Content;

public class Contents {
	
	private List<Content> contents;

	public Contents() {
		this.contents = new ArrayList<Content>();
	}

	public Contents(List<Content> contents) {
		this.contents = contents;
	}

	public Contents(Content content) {
		this.contents = new ArrayList<Content>();
		this.contents.add(content);
	}

	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
}
