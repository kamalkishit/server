package com.humanize.server.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;

public class Paper {
	
	@Id
	private String id;

	@Indexed(unique = true)
	private String paperId;
	
	private List<String> contentIds;
	
	private String paperDate;
	
	@CreatedDate
	private long createdDate; 

	@LastModifiedDate
	private long lastModifiedDate;
	
	public Paper() {
		contentIds = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public List<String> getContentIds() {
		return contentIds;
	}

	public void setContentIds(List<String> contentIds) {
		this.contentIds = contentIds;
	}

	public String getPaperDate() {
		return paperDate;
	}

	public void setPaperDate(String paperDate) {
		this.paperDate = paperDate;
	}

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public long getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(long lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
