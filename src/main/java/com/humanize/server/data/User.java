package com.humanize.server.data;

import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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
	
	private String tempPassword;
	
	private String invitationCode;
	
	private List<String> typeOfArticles;
	
	private List<String> categories;

	private Set<String> likes;

	private Set<String> bookmarks;

	private Set<String> contentsCreated;
	
	private long paperConfigurationTime; 
	
	private boolean isVerified;
	
	private boolean isConfigured;
	
	private boolean isDeleted;
	
	@CreatedDate
	private long createdDate;

	@LastModifiedDate
	private long lastModifiedDate;

	public User() {
		this.likes = new LinkedHashSet<String>();
		this.bookmarks = new LinkedHashSet<String>();
		this.contentsCreated = new LinkedHashSet<String>();
		//this.paperReminderTime = new Time(8, 0, 0);
		this.typeOfArticles = new ArrayList<String>();
		this.categories = new ArrayList<String>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTempPassword() {
		return tempPassword;
	}

	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	
	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public List<String> getTypeOfArticles() {
		return typeOfArticles;
	}

	public void setTypeOfArticles(List<String> typeOfArticles) {
		this.typeOfArticles = typeOfArticles;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
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

	public long getPaperConfigurationTime() {
		return paperConfigurationTime;
	}

	public void setPaperConfigurationTime(long paperConfigurationTime) {
		this.paperConfigurationTime = paperConfigurationTime;
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

	public boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public boolean getIsConfigured() {
		return isConfigured;
	}

	public void setIsConfigured(boolean isConfigured) {
		this.isConfigured = isConfigured;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
