package com.humanize.server.data;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
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
	private long userId;

	@Indexed(unique = true)
	private String emailId;

	@NotEmpty
	private String password;
	
	@NotEmpty
	private String invitedBy;
	
	private List<String> typeOfArticles;
	
	private List<String> categories;

	private List<String> recommended;

	private List<String> bookmarked;

	private List<String> created; 
	
	private boolean isVerified;
	
	private boolean isConfigured;
	
	private boolean isDeleted;
	
	@CreatedDate
	private long createdDate;

	@LastModifiedDate
	private long lastModifiedDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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

	public String getInvitedBy() {
		return invitedBy;
	}

	public void setInvitedBy(String invitedBy) {
		this.invitedBy = invitedBy;
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

	public List<String> getRecommended() {
		return recommended;
	}

	public void setRecommended(List<String> recommended) {
		this.recommended = recommended;
	}

	public List<String> getBookmarked() {
		return bookmarked;
	}

	public void setBookmarked(List<String> bookmarked) {
		this.bookmarked = bookmarked;
	}

	public List<String> getCreated() {
		return created;
	}

	public void setCreated(List<String> created) {
		this.created = created;
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
