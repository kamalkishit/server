package com.humanize.server.data;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	@Id
	private String id;

	@Indexed(unique = true)
	private String userId;

	@Indexed(unique = true)
	private String emailId;

	private String password;

	private Set<String> likes;

	private Set<String> bookmarks;

	private Set<String> contentsCreated;

	@CreatedDate
	private long createdDate;

	@LastModifiedDate
	private long lastModifiedDate;

	public User() {
		this.likes = new LinkedHashSet<String>();
		this.bookmarks = new LinkedHashSet<String>();
		this.contentsCreated = new LinkedHashSet<String>();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<String> getLikes() {
		return likes;
	}

	public void setLikes(Set<String> likes) {
		this.likes = likes;
	}

	public Set<String> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(Set<String> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public Set<String> getContentsCreated() {
		return contentsCreated;
	}

	public void setContentsCreated(Set<String> contentsCreated) {
		this.contentsCreated = contentsCreated;
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
