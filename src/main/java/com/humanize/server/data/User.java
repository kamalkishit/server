package com.humanize.server.data;

import java.util.ArrayList;
import java.util.List;

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

	private String password;
	
	private List<String> typeOfArticles;
	
	private List<String> categories;

	private List<String> recommended;

	private List<String> bookmarked;

	private List<String> created;
	
	private PaperTime paperTime; 
	
	private boolean isVerified;
	
	private boolean isConfigured;
	
	private boolean isDeleted;
	
	@CreatedDate
	private long createdDate;

	@LastModifiedDate
	private long lastModifiedDate;

	public User() {
		this.recommended = new ArrayList<>();
		this.bookmarked = new ArrayList<>();
		this.created = new ArrayList<>();
		this.paperTime = new PaperTime(8, 0);
		this.typeOfArticles = new ArrayList<>();
		this.categories = new ArrayList<>();
		
		this.categories.add("Achievers");
		this.categories.add("Beautiful");
		this.categories.add("Education");
		this.categories.add("Empowerment");
		this.categories.add("Environment");
		this.categories.add("Governance");
		this.categories.add("Health");
		this.categories.add("Humanity");
		this.categories.add("Real Heroes");
		this.categories.add("Science and Tech");
		this.categories.add("Law and Justice");
		this.categories.add("Sports");
	}
	
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

	public PaperTime getPaperTime() {
		return paperTime;
	}

	public void setPaperTime(PaperTime paperTime) {
		this.paperTime = paperTime;
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
