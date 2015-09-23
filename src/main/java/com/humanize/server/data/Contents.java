package com.humanize.server.data;

import java.util.ArrayList;

public class Contents {
	private ArrayList<Content> contents;

	public Contents() {
		this.contents = new ArrayList<Content>();
	}

	public Contents(ArrayList<Content> contents) {
		this.contents = contents;
	}

	public Contents(Content content) {
		this.contents = new ArrayList<Content>();
		this.contents.add(content);
	}

	public ArrayList<Content> getContents() {
		return contents;
	}

	public void setContents(ArrayList<Content> contents) {
		this.contents = contents;
	}
}
